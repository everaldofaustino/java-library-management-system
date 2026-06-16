package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.dtos.CategoriaDto;
import br.com.fuctura.biblioteca.dtos.LivroDto;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.services.CategoriaService;
import br.com.fuctura.biblioteca.services.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livro")
@CrossOrigin("*")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;


    @Operation(
            summary = "Buscar livro por ID",
            description = "Retorna um livro cadastrado a partir do ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    //http://localhost:8082/livro/{id}
    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable Integer id) {
        Livro cat = livroService.buscarPorId(id);
        LivroDto livroDto = modelMapper.map(cat, LivroDto.class);
        return ResponseEntity.ok().body(livroDto);
    }

    @Operation(
            summary = "Buscar livros por título",
            description = "Retorna todos os livros que contenham o título informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    })

    //http://localhost:8082/livro/titulo/{nome}
    @GetMapping("/titulo/{nome}")
    public ResponseEntity<List<LivroDto>> buscarPorTitulo(@PathVariable String nome) {
        List<Livro> list = livroService.buscarPorTitulo(nome);
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, LivroDto.class)).collect(Collectors.toList()));
    }

    @Operation(
            summary = "Listar todos os livros",
            description = "Retorna todos os livros cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })

    //http://localhost:8082/livro/todos
    @GetMapping("/todos")
    public ResponseEntity<List<LivroDto>> buscarTodos() {
        List<Livro> list = livroService.buscarTodos();
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, LivroDto.class)).collect(Collectors.toList()));
    }

    @Operation(
            summary = "Cadastrar livro",
            description = "Cria um novo livro associado a uma categoria"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    //POST http://localhost:8082/livro?categoria=id
    @PostMapping
    public  ResponseEntity<LivroDto> salvar(@RequestParam(value = "categoria",defaultValue = "0") Integer categoriaId,
                                            @RequestBody LivroDto livroDto){

        Livro livro = new Livro(livroDto);
        Livro livro1 = livroService.salvar(livro,categoriaId);
        return ResponseEntity.ok().body(new LivroDto(livro1));

    }

    @Operation(
            summary = "Atualizar livro",
            description = "Atualiza um livro existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })


//PUT http://localhost:8082/livro/{id}?categoria=id
    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizar(@PathVariable Integer id, @RequestParam(value = "categoria",defaultValue = "0") Integer categoriaId,
                                              @RequestBody LivroDto livroDto){

        livroDto.setId(id);

        Livro livro = new Livro(livroDto);
        Livro livro1 = livroService.atualizar(livro,categoriaId);
        return ResponseEntity.ok().body(new LivroDto(livro1));


    }

    @Operation(
            summary = "Buscar livros por categoria",
            description = "Retorna todos os livros de uma categoria específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    //http://localhost:8082/livro?categoria=id
    @GetMapping
    public ResponseEntity<List<LivroDto>> buscarTodosPorCategoria(@RequestParam(value = "categoria", defaultValue = "0")Integer categoriaId){
        List<Livro> list = livroService.buscarPorCategoria(categoriaId);
        return ResponseEntity.ok().body(list.stream().map(x-> new LivroDto(x)).toList());
    }

    @Operation(
            summary = "Buscar livros pelo nome da categoria",
            description = "Retorna todos os livros pertencentes à categoria informada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    })

    @GetMapping("/categoria/nome/{nome}")
    public ResponseEntity<List<LivroDto>> buscarTodosPorNome(@PathVariable(value = "nome", required = false)String nomeCategoria){
        List<Livro> list = livroService.buscarPorNomeCategoria(nomeCategoria);
        return ResponseEntity.ok().body(list.stream().map(x-> new LivroDto(x)).toList());
    }

    @Operation(
            summary = "Excluir livro",
            description = "Remove um livro do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })

    //http://localhost:8082/livro/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }




}
