package br.com.fuctura.biblioteca.services;

import br.com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.repositories.CategoriaRepository;
import br.com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro buscarPorId(Integer id) {
        Optional<Livro> livro = livroRepository.findById(id);
        //return cat.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada com este id: " + id));
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new ObjectNotFoundException("Livro não encontrado com este id: " + id);

    }

    public List<Livro> buscarPorCategoria(Integer categoriaId){
        categoriaService.buscarPorId(categoriaId); // Verifica se a categoria existe
        List<Livro> list = livroRepository.findAllByCategoriaId(categoriaId);
        return list;

    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> list = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if (!list.isEmpty()) {
            return list;
        }
        throw new ObjectNotFoundException("Nenhum livro encontrado com este título: " + titulo);
    }




    public Livro salvar(Livro livro) {
        Livro livro1 = livroRepository.save(livro);
        return livro1;
    }



    public void deletar(Integer id) {
        buscarPorId(id);
        livroRepository.deleteById(id);
    }






    public List<Livro> buscarTodos() {
        List<Livro> list = livroRepository.findAll();
        if (!list.isEmpty()) {
            return list;
        }
        throw new ObjectNotFoundException("Não existem livros cadastrados");
    }




}
