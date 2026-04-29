package org.everaldo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String descricao;

    private List<Livro> livro = new ArrayList<>();


    public Categoria(String nome, String descricao, Integer id) {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
    }
}
