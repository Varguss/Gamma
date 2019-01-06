package ru.gamma_station.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Repository;
import ru.gamma_station.domain.Ban;
import ru.gamma_station.domain.ErisBan;
import ru.gamma_station.util.PropertiesUtil;

import javax.sql.DataSource;
import java.sql.Time;
import java.util.List;

@Repository
public class DatabaseErisBanDAO implements BanDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DatabaseErisBanDAO(DataSource dataSource) {
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
    public List<Ban> getBansByCkey(String ckey) throws DAOException {
        return jdbcTemplate.query("SELECT bans.time, bans.job, bans.duration, bans.reason, bans.expiration_time, bans.type, players.ckey FROM bans INNER JOIN players ON bans.target_id = players.id WHERE TIMEDIFF(now(), bantime) >= ? AND players.ckey = ? AND (bans.type = 'TEMPBAN' OR bans.type = 'PERMABAN') ORDER BY bans.time DESC",
                new Object[] { new Time(new java.util.Date().getTime()+PropertiesUtil.getBanDelay()), ckey.toLowerCase()}, (rs, rowNum) -> {
                    ErisBan erisBan = new ErisBan();

                    return erisBan;
                });
    }

    @Override
    public List<Ban> getBansByAdminCkey(String adminCkey) throws DAOException {
        return null;
    }

    @Override
    public List<Ban> getJobBansByCkey(String ckey) throws DAOException {
        return null;
    }

    @Override
    public List<Ban> getJobBansByAdminCkey(String adminCkey) throws DAOException {
        return null;
    }

    @Override
    public List<Ban> getAllBansByCkey(String ckey) throws DAOException {
        return null;
    }

    @Override
    public List<Ban> getAllBansByAdminCkey(String adminCkey) throws DAOException {
        return null;
    }

    @Override
    public List<String> getAllUniqueCkeys() {
        return jdbcTemplate.queryForList("SELECT DISTINCT ckey FROM players WHERE rank = 'Player'", String.class);
    }

    @Override
    public List<String> getAllUniqueAdminCkeys() {
        return jdbcTemplate.queryForList("SELECT DISTINCT ckey FROM players WHERE rank != 'Player'", String.class);
    }
}
