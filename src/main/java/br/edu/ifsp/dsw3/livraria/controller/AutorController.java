package br.edu.ifsp.dsw3.livraria.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifsp.dsw3.livraria.model.dao.LivroDAO;
import br.edu.ifsp.dsw3.livraria.model.dao.AutorDAO;
import br.edu.ifsp.dsw3.livraria.model.domain.Livro;
import br.edu.ifsp.dsw3.livraria.model.domain.Autor;

@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    AutorDAO fdao;

    @Autowired
    LivroDAO ddao;

    @GetMapping("/cadastrar")
    public String cadastrar(Autor autor){
        return "/autor/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap map){
        map.addAttribute("autor", fdao.findAll());
        return "/autor/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Autor autor){
        fdao.save(autor);
        return ("redirect:/autor/cadastrar");
    }

    @ModelAttribute("livros")
    public List<Livro> getLivros (){
        return ddao.findAll();
    }

    @GetMapping("/buscar/nome")
    public String pesquisarPorNome(@RequestParam(name="nome") String nome, ModelMap map){
        map.addAttribute("autor", fdao.findLikeNome(nome));
        return "/autor/lista";
    }

    @GetMapping("/buscar/livro")
    public String pesquisarPorLivro(@RequestParam(name="livro") Long id, ModelMap map){
        map.addAttribute("autor",fdao.findByLivro(id));
        return "/autor/lista";
    }

    @GetMapping("/buscar/data")
    public String pesquisarPorData(@RequestParam(name="dataInicio") LocalDate inicio, @RequestParam(name="dataFim") LocalDate fim, ModelMap map){
        map.addAttribute("autor",fdao.findByDate(inicio,fim));
        return "/autor/lista";
    }
}
