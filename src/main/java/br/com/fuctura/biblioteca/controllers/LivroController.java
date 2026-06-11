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
    //http://localhost:8082/livro/titulo/{nome}
    @GetMapping("/titulo/{nome}")
    public ResponseEntity<List<LivroDto>> buscarPorTitulo(@PathVariable String nome) {
        List<Livro> list = livroService.buscarPorTitulo(nome);
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, LivroDto.class)).collect(Collectors.toList()));
    }

    //http://localhost:8082/livro/todos
    @GetMapping("/todos")
    public ResponseEntity<List<LivroDto>> buscarTodos() {
        List<Livro> list = livroService.buscarTodos();
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, LivroDto.class)).collect(Collectors.toList()));
    }

    //POST http://localhost:8082/livro?categoria=id
    @PostMapping
    public  ResponseEntity<LivroDto> salvar(@RequestParam(value = "categoria",defaultValue = "0") Integer categoriaId,
                                            @RequestBody LivroDto livroDto){

        Livro livro = new Livro(livroDto);
        Livro livro1 = livroService.salvar(livro,categoriaId);
        return ResponseEntity.ok().body(new LivroDto(livro1));

    }

//PUT http://localhost:8082/livro/{id}?categoria=id
    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizar(@PathVariable Integer id, @RequestParam(value = "categoria",defaultValue = "0") Integer categoriaId,
                                              @RequestBody LivroDto livroDto){

        livroDto.setId(id);

        Livro livro = new Livro(livroDto);
        Livro livro1 = livroService.atualizar(livro,categoriaId);
        return ResponseEntity.ok().body(new LivroDto(livro1));


    }


    //http://localhost:8082/livro?categoria=id
    @GetMapping
    public ResponseEntity<List<LivroDto>> buscarTodosPorCategoria(@RequestParam(value = "categoria", defaultValue = "0")Integer categoriaId){
        List<Livro> list = livroService.buscarPorCategoria(categoriaId);
        return ResponseEntity.ok().body(list.stream().map(x-> new LivroDto(x)).toList());
    }

    @GetMapping("/categoria/nome/{nome}")
    public ResponseEntity<List<LivroDto>> buscarTodosPorNome(@PathVariable(value = "nome", required = false)String nomeCategoria){
        List<Livro> list = livroService.buscarPorNomeCategoria(nomeCategoria);
        return ResponseEntity.ok().body(list.stream().map(x-> new LivroDto(x)).toList());
    }



    //http://localhost:8082/livro/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }




}
