package ru.gamma_station.service;

import ru.gamma_station.domain.Rule;

import java.util.List;

public interface RuleService {
    Rule findRule(String ruleName);

    void addRule(String name, String description);
    void deleteRule(String name);
    void editRule(String name, String description);

    List<Rule> getAllRules();
}
