package br.com.fuctura.biblioteca.services;

import br.com.fuctura.biblioteca.dtos.LivroDto;
import br.com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.repositories.CategoriaRepository;
import br.com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public List<Livro> buscarPorNome(String nome){
        categoriaService.buscarPorId(Integer.valueOf(nome));
        List<Livro> list = livroRepository.findAllByCategoriaNome(nome);
        return list;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> list = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if (!list.isEmpty()) {
            return list;
        }
        throw new ObjectNotFoundException("Nenhum livro encontrado com este título: " + titulo);
    }


    public Livro salvar(Livro livro,Integer categoriaId) {

        // Verifica se já existe livro com este título
        List<Livro> livrosComMesmoTitulo = livroRepository.findByTituloContainingIgnoreCase(livro.getTitulo());
        if(!livrosComMesmoTitulo.isEmpty()){
            throw new IllegalArgumentException("Livro já cadastrado com este nome: " + livro.getTitulo());
        }
        livro.setId(null);
        Categoria categoria = categoriaService.buscarPorId(categoriaId);
        livro.setCategoria(categoria);

        return livroRepository.save(livro);
    }



    public void deletar(Integer id) {
        buscarPorId(id);
        livroRepository.deleteById(id);
    }

    /*public Livro atualizar(Livro livro) {
        buscarPorId(livro.getId());
        procurarPorTitulo(livro);
        Livro livro1 = livroRepository.save(livro);
        return livro1;
    }*/

    public Livro atualizar(Livro livro, Integer categoriaId){
        buscarPorId(livro.getId());
        Categoria categoria = categoriaService.buscarPorId(categoriaId);
        livro.setCategoria((categoria));
        return livroRepository.save(livro);
    }




    public List<Livro> buscarTodos() {
        List<Livro> list = livroRepository.findAll();
        if (!list.isEmpty()) {
            return list;
        }
        throw new ObjectNotFoundException("Não existem livros cadastrados");
    }

    private void procurarPorTitulo(Livro livro) {
        List<Livro> livroEntity = livroRepository.findByTituloContainingIgnoreCase(livro.getTitulo());
        if (!livroEntity.isEmpty()) {
            if (!Objects.equals(livroEntity.get(0).getTitulo(), livro.getId())) {
                throw new IllegalArgumentException("Livro já cadastrado com este nome: " + livro.getTitulo());

            }
        }
    }


    public List<Livro> buscarPorNomeCategoria(String nomeCategoria) {
        return livroRepository.findAllByCategoriaNome(nomeCategoria);


    }
}
