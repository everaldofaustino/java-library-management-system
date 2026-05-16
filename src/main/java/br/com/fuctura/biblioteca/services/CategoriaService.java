package br.com.fuctura.biblioteca.services;

import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> cat = categoriaRepository.findById(id);
        return cat.get();
    }

    public List<Categoria> buscarPorNome(String nome) {
        List<Categoria> list = categoriaRepository.findByNomeContainingIgnoreCase(nome);
        return list;
    }

    public List<Categoria> buscarTodos() {
        List<Categoria> list = categoriaRepository.findAll();
        return list;
    }

    public Categoria salvar(Categoria categoria) {
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }

    public Categoria atualizar(Categoria categoria) {
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        categoriaRepository.findById(id);
    }
}
