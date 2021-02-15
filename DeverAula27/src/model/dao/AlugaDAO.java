package model.dao;

import java.util.List;
import model.entities.Aluga;
import model.entities.Cliente;
import model.entities.Livro;

public interface AlugaDAO {
    
    void insert(Aluga aluga);
    
    void update(Aluga aluga);
    
    void deleteById(int id);
    
    Aluga findById(int id);
    
    List<Livro> findByCliente(Cliente cliente);
    
    Aluga findByFuncionario(String nome);
    
    List<Aluga> findAll();
    
}
