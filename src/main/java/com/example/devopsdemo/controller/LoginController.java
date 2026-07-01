package com.example.devopsdemo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // NOTE FOR LEARNING: credentials are hardcoded on purpose to keep this
    // project focused on DevOps, not application security. A real app would
    // use Spring Security + a users table + hashed passwords (e.g. BCrypt).
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin123";

    @GetMapping("/")
    public String loginPage() {
        return "login"; // renders templates/login.html
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                           @RequestParam String password,
                           HttpSession session,
                           Model model) {
        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            session.setAttribute("loggedIn", true);
            return "redirect:/form";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
