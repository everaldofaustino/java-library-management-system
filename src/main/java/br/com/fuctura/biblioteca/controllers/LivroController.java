package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.dtos.CategoriaDto;
import br.com.fuctura.biblioteca.dtos.LivroDto;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.services.LivroService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable Integer id) {
        Livro cat = livroService.buscarPorId(id);
        LivroDto livroDto = modelMapper.map(cat, LivroDto.class);
        return ResponseEntity.ok().body(livroDto);
    }

    @GetMapping("/titulo/{nome}")
    public ResponseEntity<List<LivroDto>> buscarPorTitulo(@PathVariable String nome) {
        List<Livro> list = livroService.buscarPorTitulo(nome);
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, LivroDto.class)).collect(Collectors.toList()));
    }


    @GetMapping("/todos")
    public ResponseEntity<List<LivroDto>> buscarTodos() {
        List<Livro> list = livroService.buscarTodos();
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, LivroDto.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public  ResponseEntity<LivroDto> salvar(@RequestBody @Valid LivroDto livroDto){
        Livro livro1 = livroService.salvar(modelMapper.map(livroDto,Livro.class));
        return ResponseEntity.ok().body(modelMapper.map(livro1,LivroDto.class));


    }







    @GetMapping
    public ResponseEntity<List<LivroDto>> buscarTodosPorCategoria(@RequestParam(value = "categoria", defaultValue = "0")Integer categoriaId){
        List<Livro> list = livroService.buscarPorCategoria(categoriaId);
        return ResponseEntity.ok().body(list.stream().map(x-> new LivroDto(x)).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }




}
