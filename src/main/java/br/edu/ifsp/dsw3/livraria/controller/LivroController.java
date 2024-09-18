package br.edu.ifsp.dsw3.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.livraria.model.dao.LivroDAO;
import br.edu.ifsp.dsw3.livraria.model.domain.Livro;

@Controller
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    LivroDAO ldao;

    @GetMapping("/cadastrar")
    public String cadastrar(Livro livro){
        return "livro/cadastro"; // Corrigido para remover a barra inicial
    }

    @GetMapping("/listar")
    public String listar(ModelMap map){
        map.addAttribute("livros",ldao.findAll());
        return ("livro/lista");
    }

    @PostMapping("/salvar")
    public String salvar(Livro livro){
        
        ldao.save(livro);
        return ("redirect:/livro/cadastrar");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id){
        map.addAttribute("livro",ldao.getReferenceById(id));
        return ("livro/cadastro");
    }

    @PostMapping("/editar")
    public String alterar (Livro livro, RedirectAttributes attr){
        ldao.save(livro);
        attr.addFlashAttribute("success", "livro editado com sucesso!");
        return ("redirect:/livro/listar");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap map){
        Livro livro = ldao.findById(id).orElse(null);
        if (livro != null) {
            if (livro.getAutores().isEmpty()) {
                ldao.deleteById(id);
                map.addAttribute("success", "Livro excluído com sucesso!");
            } else {
                map.addAttribute("fail", "Livro não removido. Possui Autores!");
            }
        } else {
            map.addAttribute("fail", "Livro não encontrado!");
        }
    
        return listar(map); // Redireciona para a lista de livros
    }
}
