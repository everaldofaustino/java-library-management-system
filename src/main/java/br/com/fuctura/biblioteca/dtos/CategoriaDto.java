package br.com.fuctura.biblioteca.dtos;

import br.com.fuctura.biblioteca.models.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CategoriaDto {

    @Schema(
            description = "Identificador da categoria",
            example = "1"
    )
    private Integer id;

    @Schema(
            description = "Nome da categoria",
            example = "Informática"
    )
    @NotNull(message = "O nome da categoria não pode ser nulo.")
    @Length(min = 3, max = 15, message = "O nome da categoria deve conter entre 3 e 15 caracteres.")
    private String nome;

    @Schema(
            description = "Descrição da categoria",
            example = "Livros da área de tecnologia"
    )
    @NotNull(message = "O nome descrição não pode ser nulo.")
    @Length(min = 10, max = 50, message = "O campo descrição deve conter no máximo 200 caracteres.")
    private String descricao;

    public CategoriaDto() {
    }

    public CategoriaDto(Integer id, String nome, String descricao) {


        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }


    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
