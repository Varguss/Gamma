package ru.gamma_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gamma_station.domain.Post;
import ru.gamma_station.service.PostService;
import ru.gamma_station.util.ServerStatusUtil;

import java.io.IOException;
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
        model.addAttribute("posts", service.getAllPosts());
        try {
            model.addAttribute("gamma", ServerStatusUtil.getGammaStationServerStatus());
            model.addAttribute("eris", ServerStatusUtil.getErisStationServerStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home";
    }

    @ModelAttribute(name = "posts")
    public List<Post> addPosts() {
        return service.getAllPosts();
    }
}