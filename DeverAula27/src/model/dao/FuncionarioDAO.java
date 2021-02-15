package model.dao;

import java.util.List;
import model.entities.Funcionario;

public interface FuncionarioDAO {
    
    void insert(Funcionario obj);
    
    void update(Funcionario obj);
    
    void deleteById(int id);
    
    Funcionario findById(int id);
    
    Funcionario findByNome(String nome);
    
    List<Funcionario> findAll();
    
}
