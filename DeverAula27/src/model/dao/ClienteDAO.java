package model.dao;

import java.util.List;
import model.entities.Cliente;

public interface ClienteDAO {
    
    void insert(Cliente obj);
    
    void update(Cliente obj);
    
    void deleteById(int id);
    
    Cliente findById(int id);
    
    Cliente findByNome(String nome);
    
    List<Cliente> findAll();
    
}
