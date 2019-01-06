package ru.gamma_station.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Repository;
import ru.gamma_station.domain.Visitor;
import ru.gamma_station.util.PropertiesUtil;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class DatabaseVisitorDAO implements VisitorDAO, InitializingBean {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DatabaseVisitorDAO(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        SQLErrorCodeSQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator();
        translator.setDataSource(dataSource);

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null)
            throw new DAOException("Data Source isn't adjusted.");
    }

    @Override
    public void putVisitor(String ipAddress, String lastHost, Timestamp lastVisit) throws DAOException {
        try {
            Visitor visitor = findVisitor(ipAddress);

            if (visitor != null) {
                if (visitor.getLastVisit().before(new Date(new Date().getTime() - PropertiesUtil.getVisitDelay()))) {
                    jdbcTemplate.update("UPDATE website_visitors SET LAST_HOST = ?, LAST_VISIT = ?, VISITS = VISITS + 1 WHERE IP_ADDRESS = ?", lastHost, lastVisit, ipAddress);
                } else {
                    jdbcTemplate.update("UPDATE website_visitors SET LAST_HOST = ? WHERE IP_ADDRESS = ?", lastHost, ipAddress);
                }
            } else {
                jdbcTemplate.update("INSERT INTO website_visitors(IP_ADDRESS, LAST_HOST, LAST_VISIT, VISITS) VALUES (?, ?, ?, ?)", ipAddress, lastHost, lastVisit, 1L);
            }
        } catch (DataAccessException e) {
            throw new DAOException("Query is failed.", e);
        }
    }

    @Override
    public Visitor findVisitor(String ipAddress) throws DAOException {
        try {
            return jdbcTemplate.queryForObject("SELECT IP_ADDRESS, LAST_HOST, LAST_VISIT, VISITS FROM website_visitors WHERE IP_ADDRESS = ?", rowMapper, ipAddress);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            throw new DAOException("Query is failed.", e);
        }
    }

    @Override
    public void incrementVisits(String ipAddress) throws DAOException {
        try {
            jdbcTemplate.update("UPDATE website_visitors SET VISITS = VISITS + 1 WHERE IP_ADDRESS = ?", ipAddress);
        } catch (DataAccessException e) {
            throw new DAOException("Query is failed.", e);
        }
    }

    @Override
    public void decrementVisits(String ipAddress) throws DAOException {
        try {
            jdbcTemplate.update("UPDATE website_visitors SET VISITS = VISITS - 1 WHERE IP_ADDRESS = ?", ipAddress);
        } catch (DataAccessException e) {
            throw new DAOException("Query is failed.", e);
        }
    }

    @Override
    public void deleteVisitor(String ipAddress) throws DAOException {
        try {
            jdbcTemplate.update("DELETE FROM website_visitors WHERE IP_ADDRESS = ?", ipAddress);
        } catch (DataAccessException e) {
            throw new DAOException("Query is failed.", e);
        }
    }

    @Override
    public List<Visitor> getAllVisitors() throws DAOException {
        try {
            return jdbcTemplate.query("SELECT IP_ADDRESS, LAST_HOST, LAST_VISIT, VISITS FROM website_visitors ORDER BY LAST_VISIT DESC", rowMapper);
        } catch (DataAccessException e) {
            throw new DAOException("Query is failed.", e);
        }
    }

    @Override
    public Long visitsCount() throws DAOException {
        try {
            return jdbcTemplate.queryForObject("SELECT SUM(VISITS) AS COUNT FROM website_visitors", (rs, row) -> rs.getLong("COUNT"));
        } catch (DataAccessException e) {
            throw new DAOException("Query is failed.", e);
        }
    }

    private static final RowMapper<Visitor> rowMapper = (rs, row) -> {
        String ip = rs.getString("IP_ADDRESS");
        String lastHost = rs.getString("LAST_HOST");
        Timestamp lastVisit = rs.getTimestamp("LAST_VISIT");
        long visits = rs.getLong("VISITS");

        return new Visitor(ip, lastHost, lastVisit, visits);
    };
}