package br.com.fuctura.biblioteca.dtos;

import br.com.fuctura.biblioteca.enums.Edicao;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.models.Livro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","titulo","autor","texto","edicao","categoria"})

public class LivroDto {


    private Integer id;
    private String titulo;
    private String autor;
    private String texto;
    private Edicao edicao;
    private Categoria categoria;




    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.texto = livro.getTexto();
        this.edicao = livro.getEdicao();
        this.categoria = livro.getCategoria();


    }



}




