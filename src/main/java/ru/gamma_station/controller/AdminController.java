package ru.gamma_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gamma_station.dao.DAOException;
import ru.gamma_station.dao.VisitorDAO;
import ru.gamma_station.domain.CrewMember;
import ru.gamma_station.domain.CrewMemberRole;
import ru.gamma_station.domain.Visitor;
import ru.gamma_station.domain.website.Admin;
import ru.gamma_station.domain.website.AdminAuthority;
import ru.gamma_station.domain.website.Authority;
import ru.gamma_station.service.AdminService;
import ru.gamma_station.service.CrewMemberService;
import ru.gamma_station.service.PostService;
import ru.gamma_station.service.RuleService;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    private RuleService ruleService;
    private PostService postService;
    private CrewMemberService crewMemberService;
    private VisitorDAO visitorDAO;
    private InMemoryUserDetailsManager userDetailsManager;

    @Autowired
    public AdminController(AdminService adminService, RuleService ruleService, PostService postService, CrewMemberService crewMemberService, VisitorDAO visitorDAO, InMemoryUserDetailsManager userDetailsManager) {
        this.adminService = adminService;
        this.ruleService = ruleService;
        this.postService = postService;
        this.crewMemberService = crewMemberService;
        this.visitorDAO = visitorDAO;
        this.userDetailsManager = userDetailsManager;
    }

    @ModelAttribute(name = "crewMembers")
    public List<CrewMember> addCrewMembers() {
        return crewMemberService.getMembers();
    }

    @ModelAttribute(name = "admins")
    public List<Admin> addAdmins() {
        return adminService.getAdmins();
    }

    @ModelAttribute(name = "visitsCount")
    public Long addVisitsCount() {
        try {
            return visitorDAO.visitsCount();
        } catch (DAOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @ModelAttribute(name = "visitors")
    public List<Visitor> addVisitors() {
        try {
            return visitorDAO.getAllVisitors();
        } catch (DAOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @ModelAttribute(name = "principal")
    public void addAuthorizationInfo(Model model, Principal principal) {
        model.addAttribute("auth", principal);
    }

    @GetMapping
    public String adminPanel() {
        return "admin";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(@RequestParam(name = "name") String adminName,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "staff_management", required = false) String staffManagement,
                           @RequestParam(name = "posts_management", required = false) String postsManagement,
                           @RequestParam(name = "rules_management", required = false) String rulesManagement,
                           @RequestParam(name = "crew_management", required = false) String crewManagement,
                           @RequestParam(name = "ban_management", required = false) String banManagement) {

        if (adminName.equals("root"))
            return "redirect:/admin";

        if (adminService.getAdmin(adminName) == null) {
            Set<AdminAuthority> adminAuthorities = new HashSet<>();

            if (staffManagement != null)
                adminAuthorities.add(new AdminAuthority(adminName, Authority.STAFF_MANAGEMENT.name()));
            if (postsManagement != null)
                adminAuthorities.add(new AdminAuthority(adminName, Authority.POSTS_MANAGEMENT.name()));
            if (rulesManagement != null)
                adminAuthorities.add(new AdminAuthority(adminName, Authority.RULES_MANAGEMENT.name()));
            if (crewManagement != null)
                adminAuthorities.add(new AdminAuthority(adminName, Authority.CREW_MANAGEMENT.name()));
            if (banManagement != null)
                adminAuthorities.add(new AdminAuthority(adminName, Authority.BAN_MANAGEMENT.name()));

            adminService.addAdmin(adminName, password, adminAuthorities);

            return "redirect:/admin?addAdmin=successful";
        }

        return "redirect:/admin?addAdmin=alreadyExists";
    }

    @PostMapping("/removeAdmin")
    public String removeAdmin(@RequestParam(name = "name") String adminName) {
        if (adminName.equals("root"))
            userDetailsManager.deleteUser(adminName);
        else
            adminService.removeAdmin(adminName);

        return "redirect:/admin?removeAdmin=successful";
    }

    @PostMapping("/addAuthority")
    public String addAuthority(@RequestParam(name = "name") String adminName,
            @RequestParam(name = "authority") String authority) {

        switch (authority) {
            case "staff_management": {
                adminService.addAuthority(adminName, Authority.STAFF_MANAGEMENT);
            } break;
            case "posts_management": {
                adminService.addAuthority(adminName, Authority.POSTS_MANAGEMENT);
            } break;
            case "rules_management": {
                adminService.addAuthority(adminName, Authority.RULES_MANAGEMENT);
            } break;
            case "crew_management": {
                adminService.addAuthority(adminName, Authority.CREW_MANAGEMENT);
            } break;
            case "ban_management": {
                adminService.addAuthority(adminName, Authority.BAN_MANAGEMENT);
            } break;
        }

        return "redirect:/admin?addAuthority=successful";
    }

    @PostMapping("/removeAuthority")
    public String removeAuthority(@RequestParam(name = "name") String adminName,
                                  @RequestParam(name = "authority") String authority) {

        switch (authority) {
            case "staff_management": {
                adminService.removeAuthority(adminName, Authority.STAFF_MANAGEMENT);
            } break;
            case "posts_management": {
                adminService.removeAuthority(adminName, Authority.POSTS_MANAGEMENT);
            } break;
            case "rules_management": {
                adminService.removeAuthority(adminName, Authority.RULES_MANAGEMENT);
            } break;
            case "crew_management": {
                adminService.removeAuthority(adminName, Authority.CREW_MANAGEMENT);
            } break;
            case "ban_management": {
                adminService.removeAuthority(adminName, Authority.BAN_MANAGEMENT);
            } break;
        }

        return "redirect:/admin?removeAuthority=successful";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam(name = "content") String content, @RequestParam(name = "author") String author) {
        postService.save(author, content);

        return "redirect:/admin?save=successful";
    }

    @PostMapping("/removePost")
    public String removePost(@RequestParam(name = "postId") Long postId) {
        postService.delete(postId);

        return "redirect:/admin?removePost=successful";
    }

    @PostMapping("/editPost")
    public String editPost(@RequestParam(name = "postId") Long postId,
                           @RequestParam(name = "content") String content) {
        postService.edit(postId, content);

        return "redirect:/admin?edit=successful";
    }

    @PostMapping("/addRule")
    public String addRule(@RequestParam(name = "name") String name,
                          @RequestParam(name = "description") String description) {
        ruleService.addRule(name, description);

        return "redirect:/admin?addRule=successful";
    }

    @PostMapping("/removeRule")
    public String removeRule(@RequestParam(name = "name") String name) {
        ruleService.deleteRule(name);

        return "redirect:/admin?removeRule=successful";
    }

    @PostMapping("/editRule")
    public String editRule(@RequestParam(name = "name") String name,
                           @RequestParam(name = "description") String description) {
        ruleService.editRule(name, description);

        return "redirect:/admin?editRule=successful";
    }

    @PostMapping("/addCrew")
    public String addCrew(@RequestParam(name = "name") String crewMemberName,
                          @RequestParam(name = "gm", required = false) String gm,
                          @RequestParam(name = "ga", required = false) String ga,
                          @RequestParam(name = "admin", required = false) String admin,
                          @RequestParam(name = "admin_candidate", required = false) String adminCandidate,
                          @RequestParam(name = "wiki", required = false) String wiki,
                          @RequestParam(name = "coder", required = false) String coder,
                          @RequestParam(name = "mapper", required = false) String mapper,
                          @RequestParam(name = "mentor", required = false) String mentor,
                          @RequestParam(name = "spriter", required = false) String spriter,
                          @RequestParam(name = "xenovisor", required = false) String xenovisor,
                          @RequestParam(name = "director", required = false) String director) {
        CrewMember member = crewMemberService.getMember(crewMemberName);

        if (member == null) {
            Set<CrewMemberRole> roles = new HashSet<>();

            if (gm != null)
                roles.add(CrewMemberRole.GM);
            if (ga != null)
                roles.add(CrewMemberRole.GA);
            if (director != null)
                roles.add(CrewMemberRole.DIRECTOR);
            if (admin != null)
                roles.add(CrewMemberRole.ADMIN);
            if (adminCandidate != null)
                roles.add(CrewMemberRole.ADMIN_CANDIDATE);
            if (wiki != null)
                roles.add(CrewMemberRole.WIKI);
            if (coder != null)
                roles.add(CrewMemberRole.CODER);
            if (mapper != null)
                roles.add(CrewMemberRole.MAPPER);
            if (mentor != null)
                roles.add(CrewMemberRole.MENTOR);
            if (spriter != null)
                roles.add(CrewMemberRole.SPRITER);
            if (xenovisor != null)
                roles.add(CrewMemberRole.XENO_VISOR);

            crewMemberService.addMember(crewMemberName, roles);
            return "redirect:/admin?addCrew=successful";
        }
        return "redirect:/admin?addCrew=alreadyExists";
    }

    @PostMapping("/removeCrew")
    public String removeCrew(@RequestParam(name = "name") String crewMemberName) {
        crewMemberService.removeMember(crewMemberName);

        return "redirect:/admin?removeCrew=successful";
    }

    @PostMapping("/addCrewRole")
    public String addCrewRole(@RequestParam(name = "name") String crewMemberName,
                              @RequestParam(name = "role") String role) {

        switch(role) {
            case "director": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.DIRECTOR);
            } break;
            case "gm": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.GM);
            } break;
            case "ga": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.GA);
            } break;
            case "admin": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.ADMIN);
            } break;
            case "admin_candidate": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.ADMIN_CANDIDATE);
            } break;
            case "wiki": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.WIKI);
            } break;
            case "coder": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.CODER);
            } break;
            case "spriter": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.SPRITER);
            } break;
            case "mapper": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.MAPPER);
            } break;
            case "mentor": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.MENTOR);
            } break;
            case "xenovisor": {
                crewMemberService.addRole(crewMemberName, CrewMemberRole.XENO_VISOR);
            } break;
        }

        return "redirect:/admin?addCrewRole=successful";
    }

    @PostMapping("/removeCrewRole")
    public String removeCrewRole(@RequestParam(name = "name") String crewMemberName,
                                 @RequestParam(name = "role") String role) {
        switch(role) {
            case "director": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.DIRECTOR);
            } break;
            case "gm": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.GM);
            } break;
            case "ga": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.GA);
            } break;
            case "admin": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.ADMIN);
            } break;
            case "admin_candidate": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.ADMIN_CANDIDATE);
            } break;
            case "wiki": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.WIKI);
            } break;
            case "coder": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.CODER);
            } break;
            case "spriter": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.SPRITER);
            } break;
            case "mapper": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.MAPPER);
            } break;
            case "mentor": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.MENTOR);
            } break;
            case "xenovisor": {
                crewMemberService.removeRole(crewMemberName, CrewMemberRole.XENO_VISOR);
            } break;
        }

        return "redirect:/admin?removeCrewRole=successful";
    }
}