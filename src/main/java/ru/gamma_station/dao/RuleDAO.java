package ru.gamma_station.dao;

import ru.gamma_station.domain.Rule;

import java.util.List;

public interface RuleDAO {
    Rule findRule(String name);
    void saveRule(Rule rule);
    void deleteRule(Rule rule);

    List<Rule> getAllRules();
}