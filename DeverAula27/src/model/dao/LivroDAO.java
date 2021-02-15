package model.dao;

import java.util.List;
import model.entities.Livro;

public interface LivroDAO {
    
    void insert(Livro obj);
    
    void update(Livro obj);
    
    void deleteById(int id);
    
    Livro findById(int id);
    
    Livro findByNome(String nome);
    
    List<Livro> findAll();
    
}
