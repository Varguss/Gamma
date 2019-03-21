package ru.gamma_station.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Repository;
import ru.gamma_station.domain.Ban;
import ru.gamma_station.domain.GammaBan;
import ru.gamma_station.util.PropertiesUtil;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.List;

@Repository
public class DatabaseGammaBanDAO implements BanDAO, InitializingBean {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DatabaseGammaBanDAO(@Qualifier("gammaDataSource") DataSource dataSource) {
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
        try {
            return jdbcTemplate.query("SELECT bantime, bantype, reason, duration, expiration_time, ckey, a_ckey, adminwho FROM erro_ban WHERE (bantype='TEMPBAN' OR bantype='PERMABAN') AND ckey = ? AND TIMEDIFF(now(), bantime) >= ? ORDER BY bantime DESC",
                    new Object[]{ckey.toLowerCase(), new Time(new java.util.Date().getTime()+PropertiesUtil.getBanDelay())},
                    (resultSet, rowNum) -> {
                        GammaBan gammaBan = new GammaBan();

                        gammaBan.setCkey(ckey.toLowerCase());
                        gammaBan.setAdminCkey(resultSet.getString("a_ckey"));
                        gammaBan.setAdminsWasOnline(resultSet.getString("adminwho"));
                        gammaBan.setTime(resultSet.getTimestamp("bantime"));
                        gammaBan.setDuration(resultSet.getInt("duration"));
                        gammaBan.setExpirationTime(resultSet.getTimestamp("expiration_time"));

                        String reason;
                        try {
                            // Text in database saved in latin1 encoding. It's sad, but here's nothing to do.
                            reason = new String(resultSet.getBytes("reason"), "windows-1251");
                        } catch (UnsupportedEncodingException e) {
                            reason = resultSet.getString("reason");
                            e.printStackTrace();
                        }

                        gammaBan.setReason(fixReason(reason));

                        return gammaBan;
                    });
        } catch (DataAccessException e) {
            throw new DAOException("Query execution is failed.", e);
        }
    }

    @Override
    public List<Ban> getBansByAdminCkey(String adminCkey) throws DAOException {
        try {
            return jdbcTemplate.query("SELECT bantime, bantype, reason, duration, expiration_time, ckey, a_ckey, adminwho FROM erro_ban WHERE (bantype='TEMPBAN' OR bantype='PERMABAN') AND a_ckey = ? AND TIMEDIFF(now(), bantime) >= ? ORDER BY bantime DESC",
                    new Object[]{adminCkey.toLowerCase(), new Time(new java.util.Date().getTime()+PropertiesUtil.getBanDelay())},
                    (resultSet, rowNum) -> {
                        GammaBan gammaBan = new GammaBan();

                        gammaBan.setAdminCkey(adminCkey.toLowerCase());
                        gammaBan.setCkey(resultSet.getString("ckey"));
                        gammaBan.setAdminsWasOnline(resultSet.getString("adminwho"));
                        gammaBan.setTime(resultSet.getTimestamp("bantime"));
                        gammaBan.setDuration(resultSet.getInt("duration"));
                        gammaBan.setExpirationTime(resultSet.getTimestamp("expiration_time"));

                        String reason;
                        try {
                            // Text in database saved in latin1 encoding. It's sad, but here's nothing to do.
                            reason = new String(resultSet.getBytes("reason"), "windows-1251");
                        } catch (UnsupportedEncodingException e) {
                            reason = resultSet.getString("reason");
                            e.printStackTrace();
                        }

                        gammaBan.setReason(fixReason(reason));

                        return gammaBan;
                    });
        } catch (DataAccessException e) {
            throw new DAOException("Query execution is failed.", e);
        }
    }

    @Override
    public List<Ban> getJobBansByCkey(String ckey) throws DAOException {
        try {
            return jdbcTemplate.query("SELECT bantime, bantype, reason, job, duration, expiration_time, ckey, a_ckey, adminwho FROM erro_ban WHERE (bantype='JOB_TEMPBAN' OR bantype='JOB_PERMABAN') AND ckey = ? AND TIMEDIFF(now(), bantime) >= ? ORDER BY bantime DESC",
                    new Object[]{ckey.toLowerCase(), new Time(new java.util.Date().getTime()+PropertiesUtil.getBanDelay())},
                    (resultSet, rowNum) -> {
                        GammaBan gammaBan = new GammaBan();

                        gammaBan.setCkey(ckey.toLowerCase());
                        gammaBan.setAdminCkey(resultSet.getString("a_ckey"));
                        gammaBan.setAdminsWasOnline(resultSet.getString("adminwho"));
                        gammaBan.setTime(resultSet.getTimestamp("bantime"));
                        gammaBan.setDuration(resultSet.getInt("duration"));
                        gammaBan.setExpirationTime(resultSet.getTimestamp("expiration_time"));
                        gammaBan.setJob(resultSet.getString("job"));

                        String reason;
                        try {
                            // Text in database saved in latin1 encoding. It's sad, but here's nothing to do.
                            reason = new String(resultSet.getBytes("reason"), "windows-1251");
                        } catch (UnsupportedEncodingException e) {
                            reason = resultSet.getString("reason");
                            e.printStackTrace();
                        }

                        gammaBan.setReason(fixReason(reason));

                        return gammaBan;
                    });
        } catch (DataAccessException e) {
            throw new DAOException("Query execution is failed.", e);
        }
    }

    @Override
    public List<Ban> getJobBansByAdminCkey(String adminCkey) throws DAOException {
        try {
            return jdbcTemplate.query("SELECT bantime, bantype, reason, job, duration, expiration_time, ckey, a_ckey, adminwho FROM erro_ban WHERE (bantype='JOB_TEMPBAN' OR bantype='JOB_PERMABAN') AND a_ckey = ? AND TIMEDIFF(now(), bantime) >= ? ORDER BY bantime DESC",
                    new Object[]{adminCkey.toLowerCase(), new Time(new java.util.Date().getTime()+PropertiesUtil.getBanDelay())},
                    (resultSet, rowNum) -> {
                        GammaBan gammaBan = new GammaBan();

                        gammaBan.setAdminCkey(adminCkey.toLowerCase());
                        gammaBan.setCkey(resultSet.getString("ckey"));
                        gammaBan.setAdminsWasOnline(resultSet.getString("adminwho"));
                        gammaBan.setTime(resultSet.getTimestamp("bantime"));
                        gammaBan.setDuration(resultSet.getInt("duration"));
                        gammaBan.setExpirationTime(resultSet.getTimestamp("expiration_time"));
                        gammaBan.setJob(resultSet.getString("job"));

                        String reason;
                        try {
                            // Text in database saved in latin1 encoding. It's sad, but here's nothing to do.
                            reason = new String(resultSet.getBytes("reason"), "windows-1251");
                        } catch (UnsupportedEncodingException e) {
                            reason = resultSet.getString("reason");
                            e.printStackTrace();
                        }

                        gammaBan.setReason(fixReason(reason));

                        return gammaBan;
                    });
        } catch (DataAccessException e) {
            throw new DAOException("Query execution is failed.", e);
        }
    }

    @Override
    public List<Ban> getAllBansByCkey(String ckey) throws DAOException {
        try {
            return jdbcTemplate.query("SELECT bantime, bantype, reason, job, duration, expiration_time, ckey, a_ckey, adminwho FROM erro_ban WHERE ckey = ? AND TIMEDIFF(now(), bantime) >= ? ORDER BY bantime DESC",
                    new Object[]{ckey.toLowerCase(), new Time(new java.util.Date().getTime()+PropertiesUtil.getBanDelay())},
                    (resultSet, rowNum) -> {
                        GammaBan gammaBan = new GammaBan();

                        gammaBan.setCkey(ckey.toLowerCase());
                        gammaBan.setAdminCkey(resultSet.getString("a_ckey"));
                        gammaBan.setAdminsWasOnline(resultSet.getString("adminwho"));
                        gammaBan.setTime(resultSet.getTimestamp("bantime"));
                        gammaBan.setDuration(resultSet.getInt("duration"));
                        gammaBan.setExpirationTime(resultSet.getTimestamp("expiration_time"));

                        String job = resultSet.getString("job");
                        if (!job.isEmpty())
                            gammaBan.setJob(job);

                        String reason;
                        try {
                            // Text in database saved in latin1 encoding. It's sad, but here's nothing to do.
                            reason = new String(resultSet.getBytes("reason"), "windows-1251");
                        } catch (UnsupportedEncodingException e) {
                            reason = resultSet.getString("reason");
                            e.printStackTrace();
                        }

                        gammaBan.setReason(fixReason(reason));

                        return gammaBan;
                    });
        } catch (DataAccessException e) {
            throw new DAOException("Query execution is failed.", e);
        }
    }

    @Override
    public List<Ban> getAllBansByAdminCkey(String adminCkey) throws DAOException {
        try {
            return jdbcTemplate.query("SELECT bantime, bantype, reason, job, duration, expiration_time, ckey, a_ckey, adminwho FROM erro_ban WHERE a_ckey = ? AND TIMEDIFF(now(), bantime) >= ? ORDER BY bantime DESC",
                    new Object[]{adminCkey.toLowerCase(), new Time(new java.util.Date().getTime()+PropertiesUtil.getBanDelay())},
                    (resultSet, rowNum) -> {
                        GammaBan gammaBan = new GammaBan();

                        gammaBan.setAdminCkey(adminCkey.toLowerCase());
                        gammaBan.setCkey(resultSet.getString("ckey"));
                        gammaBan.setAdminsWasOnline(resultSet.getString("adminwho"));
                        gammaBan.setTime(resultSet.getTimestamp("bantime"));
                        gammaBan.setDuration(resultSet.getInt("duration"));
                        gammaBan.setExpirationTime(resultSet.getTimestamp("expiration_time"));

                        String job = resultSet.getString("job");
                        if (!job.isEmpty())
                            gammaBan.setJob(job);

                        String reason;
                        try {
                            // Text in database saved in latin1 encoding. It's sad, but here's nothing to do.
                            reason = new String(resultSet.getBytes("reason"), "windows-1251");
                        } catch (UnsupportedEncodingException e) {
                            reason = resultSet.getString("reason");
                            e.printStackTrace();
                        }

                        gammaBan.setReason(fixReason(reason));
                        return gammaBan;
                    });
        } catch (DataAccessException e) {
            throw new DAOException("Query execution is failed.", e);
        }
    }

    @Override
    public List<String> getAllUniqueCkeys() throws DAOException {
        try {
            return jdbcTemplate.query("SELECT DISTINCT ckey FROM erro_ban", (rs, row) -> rs.getString("ckey"));
        } catch (DataAccessException e) {
            throw new DAOException("Query execution is failed.", e);
        }
    }

    @Override
    public List<String> getAllUniqueAdminCkeys() throws DAOException {
        try {
            return jdbcTemplate.query("SELECT DISTINCT a_ckey FROM erro_ban", (rs, row) -> rs.getString("a_ckey"));
        } catch (DataAccessException e) {
            throw new DAOException("Query execution is failed.", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null)
            throw new DAOException("Data Source isn't adjusted.");
    }

    private String fixReason(String reason) {
        return reason.replace("¶", "я").replace("#34", "\"");
    }
}