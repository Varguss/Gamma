package ru.gamma_station.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gamma_station.domain.Rule;
import ru.gamma_station.service.RuleService;

import java.util.List;

@Slf4j
@RestController
public class RulesRestController {
    private RuleService ruleService;

    @Autowired
    public RulesRestController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("rule")
    @ResponseStatus(HttpStatus.OK)
    public List<Rule> getRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("rule/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Rule getRule(@PathVariable("name") String name) {
        return ruleService.findRule(name);
    }
}
