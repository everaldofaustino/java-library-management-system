package br.com.fuctura.biblioteca.dtos;

import br.com.fuctura.biblioteca.enums.Edicao;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.models.Livro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","titulo","autor","texto","edicao","categoria"})

public class LivroDto {

    @Schema(description = "Identificador do livro", example = "1")
    private Integer id;

    @Schema(description = "Título do livro", example = "Clean Code")
    private String titulo;

    @Schema(description = "Autor do livro", example = "Robert C. Martin")
    private String autor;

    @Schema(
            description = "Descrição ou conteúdo do livro",
            example = "Livro sobre boas práticas de programação"
    )
    private String texto;

    @Schema(
            description = "Edição do livro",
            example = "PRIMEIRA",
            allowableValues = {"PRIMEIRA", "SEGUNDA", "TERCEIRA"}
    )
    private Edicao edicao;

    @Schema(description = "Categoria associada ao livro")
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




