package com.example.devopsdemo.controller;

import com.example.devopsdemo.model.Person;
import com.example.devopsdemo.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/form")
    public String formPage(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/"; // not logged in -> bounce to login
        }
        List<Person> people = personRepository.findAll();
        model.addAttribute("people", people);
        return "form"; // renders templates/form.html
    }

    @PostMapping("/save")
    public String savePerson(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String phone,
                              HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }
        personRepository.save(new Person(name, email, phone));
        return "redirect:/form";
    }
}
