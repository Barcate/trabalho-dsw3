package br.edu.ifsp.dsw3.livraria.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.livraria.model.domain.Autor;

public interface AutorDAO extends JpaRepository <Autor, Long>{
    @Query("SELECT a FROM Autor a WHERE a.nome LIKE %?1%")
    public List<Autor> findLikeNome (String nome);

    @Query("SELECT a FROM Autor a JOIN a.livros l WHERE l.id = ?1")
    public List<Autor> findByLivro(Long id);

    @Query("SELECT a FROM Autor a WHERE a.dataNascimento >= ?1 AND a.dataNascimento <= ?2")
    public List<Autor> findByDate(LocalDate inicio, LocalDate fim);
}
