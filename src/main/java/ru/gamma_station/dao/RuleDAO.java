package ru.gamma_station.dao;

import ru.gamma_station.domain.Rule;

import java.util.List;

public interface RuleDAO {
    Rule find(String name);
    void save(Rule rule);
    void delete(Rule rule);

    List<Rule> getAll();
}