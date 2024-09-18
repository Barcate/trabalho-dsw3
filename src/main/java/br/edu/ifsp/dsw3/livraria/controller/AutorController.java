package br.edu.ifsp.dsw3.livraria.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.livraria.model.dao.LivroDAO;
import br.edu.ifsp.dsw3.livraria.model.dao.AutorDAO;
import br.edu.ifsp.dsw3.livraria.model.domain.Autor;

@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    AutorDAO adao;

    @Autowired
    LivroDAO ldao;

    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap map, Autor autor){
        map.addAttribute("livros", ldao.findAll());
        return ("/autor/cadastro");
    }

    @GetMapping("/listar")
    public String listar(ModelMap map){
        map.addAttribute("autor", adao.findAll());
        map.addAttribute("livro", ldao.findAll());
        return "/autor/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Autor autor){
        adao.save(autor);
        return ("redirect:/autor/cadastrar");
    }

    // @ModelAttribute("livros")
    // public List<Livro> getLivros (){
    //     return ldao.findAll();
    // }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id){
        map.addAttribute("autor", adao.getReferenceById(id));
        map.addAttribute("livros", ldao.findAll());
        return ("autor/cadastro");
    }

    @PostMapping("/editar")
    public String alterar (Autor autor, RedirectAttributes attr){
        adao.save(autor);
        attr.addFlashAttribute("success", "autor editado com sucesso!");
        return ("redirect:/autor/listar");
    }

    @GetMapping("/buscar/nome")
    public String pesquisarPorNome(@RequestParam(name="nome") String nome, ModelMap map){
        map.addAttribute("autor", adao.findLikeNome(nome));
        return "/autor/lista";
    }

    @GetMapping("/buscar/livro")
    public String pesquisarPorLivro(@RequestParam(name="livro") Long id, ModelMap map){
        map.addAttribute("autor",adao.findByLivro(id));
        return "/autor/lista";
    }

    @GetMapping("/buscar/data")
    public String pesquisarPorData(@RequestParam(name="dataInicio") LocalDate inicio, @RequestParam(name="dataFim") LocalDate fim, ModelMap map){
        map.addAttribute("autor",adao.findByDate(inicio,fim));
        return "/autor/lista";
    }
}
