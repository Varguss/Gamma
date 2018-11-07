package ru.gamma_station.dao;

import ru.gamma_station.domain.Ban;

import java.util.List;

public interface BanDAO {
    // SELECT * FROM test WHERE TIMEDIFF(now(), date) >= '01:00:00';
    // Only account bans
    List<Ban> getBansByCkey(String ckey) throws DAOException;
    List<Ban> getBansByAdminCkey(String adminCkey) throws DAOException;

    // Only job bans
    List<Ban> getJobBansByCkey(String ckey) throws DAOException;
    List<Ban> getJobBansByAdminCkey(String adminCkey) throws DAOException;

    // All bans
    List<Ban> getAllBansByCkey(String ckey) throws DAOException;
    List<Ban> getAllBansByAdminCkey(String adminCkey) throws DAOException;

    List<String> getAllUniqueCkeys() throws DAOException;
    List<String> getAllUniqueAdminCkeys() throws DAOException;
}