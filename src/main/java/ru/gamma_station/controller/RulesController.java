package ru.gamma_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gamma_station.dao.DAOException;
import ru.gamma_station.dao.RuleDAO;
import ru.gamma_station.domain.Rule;
import ru.gamma_station.service.RuleService;

import java.util.List;

@Controller
@RequestMapping("/rules")
public class RulesController {
    private RuleService ruleService;

    @Autowired
    public void setRuleService(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping
    public String rules() {
        return "rules";
    }

    @ModelAttribute(name = "rules")
    public List<Rule> addRules() {
        return ruleService.getAllRules();
    }
}
