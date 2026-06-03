package br.com.fuctura.biblioteca.services;

import br.com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> cat = categoriaRepository.findById(id);
        //return cat.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada com este id: " + id));
        if (cat.isPresent()) {
            return cat.get();
        }
        throw new ObjectNotFoundException("Categoria não encontrada com este id: " + id);

    }

    public List<Categoria> buscarPorNome(String nome) {
        List<Categoria> list = categoriaRepository.findByNomeContainingIgnoreCase(nome);
        if (!list.isEmpty()) {
            return list;
        }
        throw new ObjectNotFoundException("Nenhuma categoria encontrada com este nome: " + nome);
    }

    public List<Categoria> buscarTodos() {
        List<Categoria> list = categoriaRepository.findAll();
        if (!list.isEmpty()) {
            return list;
        }
        throw new ObjectNotFoundException("Não existem categorias cadastradas");
    }

    public Categoria salvar(Categoria categoria) {
        procurarPorNome(categoria);
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }

    public Categoria atualizar(Categoria categoria) {
        buscarPorId(categoria.getId());
        procurarPorNome(categoria);
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }

    public void deletar(Integer id) {
        Categoria cat = buscarPorId(id);
        if(!cat.getLivros().isEmpty()) {
            throw new DataIntegrityViolationException("Não é possível excluir a categoria id=" + id +
                                                        " pois existem livros associados a ela.");
        }
        categoriaRepository.deleteById(id);
    }

    private void procurarPorNome(Categoria categoria) {
        Optional<Categoria> catEntity = categoriaRepository.findByNomeIgnoreCase(categoria.getNome());
        if (catEntity.isPresent()) {
            if (!Objects.equals(catEntity.get().getId(), categoria.getId())) {
                throw new IllegalArgumentException("Categoria já cadastrada com este nome: " + categoria.getNome());

            }
        }
    }
}