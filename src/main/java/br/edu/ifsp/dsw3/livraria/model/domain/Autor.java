package br.edu.ifsp.dsw3.livraria.model.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Column(name = "quantidade_livros_publicados", nullable = false)
    private int quantidadeLivrosPublicados; // Substituindo "salario" por "quantidade de livros publicados"

    @ManyToOne
    @JoinColumn(name = "livro_id_fk")
    private Livro livro;

    public Autor() {
    }

    public Autor(String nome, String cpf, LocalDate dataNascimento, int quantidadeLivrosPublicados, Livro livro) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.quantidadeLivrosPublicados = quantidadeLivrosPublicados;
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getQuantidadeLivrosPublicados() {
        return quantidadeLivrosPublicados;
    }

    public void setQuantidadeLivrosPublicados(int quantidadeLivrosPublicados) {
        this.quantidadeLivrosPublicados = quantidadeLivrosPublicados;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
