package ru.gamma_station.dao;

import ru.gamma_station.domain.Visitor;

import java.sql.Timestamp;
import java.util.List;

public interface VisitorDAO {
    Visitor findVisitor(String ipAddress)  throws DAOException;

    void deleteVisitor(String ipAddress)  throws DAOException;
    void putVisitor(String ipAddress, String lastHost, Timestamp lastVisit)  throws DAOException;

    Long visitsCount()  throws DAOException;

    void incrementVisits(String ipAddress) throws DAOException;
    void decrementVisits(String ipAddress) throws DAOException;

    List<Visitor> getAllVisitors() throws DAOException;
}