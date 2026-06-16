package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.dtos.CategoriaDto;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(
            summary = "Buscar categoria por ID",
            description = "Retorna uma categoria cadastrada a partir do ID informado"
    )

    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoria encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria não encontrada"
            )
    })
    //http://localhost:8082/categoria/id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Integer id) {
        log.info("Buscando categoria por id: {}", id);
        Categoria cat = categoriaService.buscarPorId(id);
        CategoriaDto categoriaDto = modelMapper.map(cat, CategoriaDto.class);
        return ResponseEntity.ok().body(categoriaDto);
    }

    @Operation(
            summary = "Buscar categoria por nome",
            description = "Retorna todas as categorias que contenham o nome informado"
    )
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta realizada com sucesso"
            )
    }
    )


    //http://localhost:8082/categoria/nomes/Informática
    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<CategoriaDto>> buscarPorNome(@PathVariable String nome) {
        List<Categoria> list = categoriaService.buscarPorNome(nome);
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, CategoriaDto.class)).collect(Collectors.toList()));
    }

    @Operation(
            summary = "Lista Categorias",
            description = "Retorna todas as categorias cadastradas"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Lista retornada com sucesso"
    )

    //http://localhost:8082/categoria
    @GetMapping
    public ResponseEntity<List<CategoriaDto>> buscarTodos() {
        List<Categoria> list = categoriaService.buscarTodos();
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, CategoriaDto.class)).collect(Collectors.toList()));
    }

    @Operation(
            summary = "Cadastrar categoria",
            description = "Cria uma nova categoria no sistema"
    )

    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "201",
                    description = "Categoria criada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            )
    }    )
    //http://localhost:8082/categoria
    @PostMapping
    public ResponseEntity<CategoriaDto> salvar(@RequestBody @Valid CategoriaDto categoriaDto) {
        Categoria cat = categoriaService.salvar(modelMapper.map(categoriaDto, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @Operation(
            summary = "Atualizar categoria",
            description = "Atualiza os dados de uma categoria existente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoria atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria não encontrada"
            )
    })
    //http://localhost:8082/categoria/id
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Integer id, @RequestBody @Valid CategoriaDto categoriaDto){
        categoriaDto.setId(id);
        Categoria cat = categoriaService.atualizar(modelMapper.map(categoriaDto, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @Operation(
            summary = "Excluir categoria",
            description = "Remove uma categoria pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Categoria removida com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria não encontrada"
            )
    })

    //http://localhost:8082/categoria/id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
