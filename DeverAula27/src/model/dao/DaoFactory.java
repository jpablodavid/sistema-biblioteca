package model.dao;

import db.DB;
import model.dao.impl.ClienteDAOJDBC;
import model.dao.impl.FuncionarioDAOJDBC;
import model.dao.impl.LivroDAOJDBC;
import model.dao.impl.AlugaDAOJDBC;


public class DaoFactory {
    
    public static ClienteDAO createClienteDAO(){
        return new ClienteDAOJDBC(DB.getConnection());
    }
    
    public static LivroDAO createLivroDAO(){
        return new LivroDAOJDBC(DB.getConnection());
    }
    
    public static FuncionarioDAO createFuncionarioDAO(){
        return new FuncionarioDAOJDBC(DB.getConnection());
    }
    
    public static AlugaDAO createAlugaDAO(){
        return new AlugaDAOJDBC(DB.getConnection());
    }
}
