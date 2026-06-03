package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.dtos.CategoriaDto;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.services.CategoriaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Integer id) {
        Categoria cat = categoriaService.buscarPorId(id);
        CategoriaDto categoriaDto = modelMapper.map(cat, CategoriaDto.class);
        return ResponseEntity.ok().body(categoriaDto);
    }

    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<CategoriaDto>> buscarPorNome(@PathVariable String nome) {
        List<Categoria> list = categoriaService.buscarPorNome(nome);
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, CategoriaDto.class)).collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> buscarTodos() {
        List<Categoria> list = categoriaService.buscarTodos();
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, CategoriaDto.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> salvar(@RequestBody @Valid CategoriaDto categoriaDto) {
        Categoria cat = categoriaService.salvar(modelMapper.map(categoriaDto, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Integer id, @RequestBody @Valid CategoriaDto categoriaDto){
        categoriaDto.setId(id);
        Categoria cat = categoriaService.atualizar(modelMapper.map(categoriaDto, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
