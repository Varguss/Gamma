package ru.gamma_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gamma_station.dao.DAOException;
import ru.gamma_station.dao.PostDAO;
import ru.gamma_station.domain.Post;
import ru.gamma_station.domain.website.AdminAuthority;
import ru.gamma_station.domain.website.Authority;
import ru.gamma_station.service.AdminService;
import ru.gamma_station.service.PostService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private PostService service;

    @Autowired
    public HomeController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    @ModelAttribute(name = "posts")
    public List<Post> addPosts() {
        return service.getAllPosts();
    }
}