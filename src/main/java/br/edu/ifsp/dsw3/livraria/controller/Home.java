package br.edu.ifsp.dsw3.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifsp.dsw3.livraria.model.dao.LivroDAO;

@Controller

public class Home {
    @Autowired
    LivroDAO ldao;
    @GetMapping(name = "/.")
    public String principal(ModelMap map){
        map.addAttribute("livros",ldao.findAll());
        return "/home";
    };
}
