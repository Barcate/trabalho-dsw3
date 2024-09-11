package br.edu.ifsp.dsw3.livraria.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.dsw3.livraria.model.domain.Livro;

public interface LivroDAO extends JpaRepository <Livro, Long>{
    
}
