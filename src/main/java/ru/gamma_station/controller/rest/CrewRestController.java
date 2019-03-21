package ru.gamma_station.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gamma_station.domain.CrewMember;
import ru.gamma_station.service.CrewMemberService;

import java.util.List;

@Slf4j
@RestController
public class CrewRestController {
    private CrewMemberService crewMemberService;

    @Autowired
    public CrewRestController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @GetMapping("crew")
    @ResponseStatus(HttpStatus.OK)
    public List<CrewMember> getCrewMembers() {
        return crewMemberService.getMembers();
    }

    @GetMapping("crew/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CrewMember getCrewMember(@PathVariable("name") String name) {
        return crewMemberService.getMember(name);
    }
}
