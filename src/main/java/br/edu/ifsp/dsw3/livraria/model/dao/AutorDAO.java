package br.edu.ifsp.dsw3.livraria.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.livraria.model.domain.Autor;

public interface AutorDAO extends JpaRepository <Autor, Long>{
    @Query("select f from Autor f where f.nome like %?1%")
    public List<Autor> findLikeNome (String nome);

    @Query("select f from Autor f where f.livro.id = ?1")
    public List<Autor> findByLivro(Long id);

    @Query("select f from Autor f where f.dataNascimento >= ?1 and f.dataNascimento <= ?2")
    public List<Autor> findByDate(LocalDate inicio, LocalDate fim);
}
