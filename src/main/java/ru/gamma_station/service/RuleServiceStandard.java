package ru.gamma_station.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.dao.DatabaseRuleDAO;
import ru.gamma_station.dao.RuleDAO;
import ru.gamma_station.domain.Rule;

import java.util.List;

@Service
@Transactional
public class RuleServiceStandard implements RuleService {
    private RuleDAO dao;

    @Autowired
    public void setDao(RuleDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public Rule findRule(String ruleName) {
        return dao.findRule(ruleName);
    }

    @Override
    public void addRule(String name, String description) {
        dao.saveRule(new Rule(name, validateDescription(description)));
    }

    @Override
    public void deleteRule(String name) {
        Rule rule = dao.findRule(name);

        if (rule != null)
            dao.deleteRule(rule);
    }

    @Override
    public void editRule(String name, String description) {
        dao.findRule(name).setDescription(validateDescription(description));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rule> getAllRules() {
        return dao.getAllRules();
    }

    private String validateDescription(String description) {
        return description.replace("\n", "<br/>");
    }
}
