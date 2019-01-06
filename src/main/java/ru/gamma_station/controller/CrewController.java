package ru.gamma_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gamma_station.domain.CrewMember;
import ru.gamma_station.domain.CrewMemberRole;
import ru.gamma_station.domain.ServerStatus;
import ru.gamma_station.service.CrewMemberService;
import ru.gamma_station.util.ServerStatusUtil;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/crew")
public class CrewController {
    private CrewMemberService crewMemberService;

    @Autowired
    public CrewController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @GetMapping
    public String crew(Model model) {
        model.addAttribute("Director", CrewMemberRole.DIRECTOR);
        model.addAttribute("GM", CrewMemberRole.GM);
        model.addAttribute("GA", CrewMemberRole.GA);
        model.addAttribute("Admin", CrewMemberRole.ADMIN);
        model.addAttribute("Admin_Candidate", CrewMemberRole.ADMIN_CANDIDATE);
        model.addAttribute("Wiki", CrewMemberRole.WIKI);
        model.addAttribute("Coder", CrewMemberRole.CODER);
        model.addAttribute("Mapper", CrewMemberRole.MAPPER);
        model.addAttribute("Spriter", CrewMemberRole.SPRITER);
        model.addAttribute("Mentor", CrewMemberRole.MENTOR);
        model.addAttribute("Xenovisor", CrewMemberRole.XENO_VISOR);

        return "monitoring";
    }

    @ModelAttribute(name = "crewMembers")
    public List<CrewMember> addCrewMembers() {
        return crewMemberService.getMembers();
    }

    @ModelAttribute(name = "gamma")
    public ServerStatus addGammaServerStatus() throws IOException {
        return ServerStatusUtil.getGammaStationServerStatus();
    }

    @ModelAttribute(name = "eris")
    public ServerStatus addErisServerStatus() throws IOException {
        return ServerStatusUtil.getErisStationServerStatus();
    }
}
