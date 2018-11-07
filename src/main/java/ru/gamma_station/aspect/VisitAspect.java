package ru.gamma_station.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.gamma_station.dao.DAOException;
import ru.gamma_station.dao.VisitorDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Aspect
@Component
public class VisitAspect {
    private VisitorDAO dao;

    @Autowired
    public VisitAspect(VisitorDAO dao) {
        this.dao = dao;
    }

    @Pointcut("execution(public * ru.gamma_station.controller.*.*(..))")
    private void visitPointcut() { }

    @Before("visitPointcut()")
    public void visit() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ipAddress = request.getRemoteAddr();
        String lastHost = request.getRemoteHost();
        Timestamp lastVisit = new Timestamp(new Date().getTime());

        try {
            dao.putVisitor(ipAddress, lastHost, lastVisit);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
