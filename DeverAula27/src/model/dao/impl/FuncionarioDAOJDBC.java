package model.dao.impl;

import db.DB;
import db.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.FuncionarioDAO;
import static model.dao.impl.ClienteDAOJDBC.instanciarCliente;
import model.entities.Cliente;
import model.entities.Funcionario;


public class FuncionarioDAOJDBC implements FuncionarioDAO{
    
    private Connection conn;
    
    public FuncionarioDAOJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Funcionario func) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("INSERT INTO funcionario " 
                                     + "(nome) "
                                     + "VALUES (?);", Statement.RETURN_GENERATED_KEYS );
            
            st.setString(1, func.getNome());
            
            int linhainserida = st.executeUpdate();
            
            if(linhainserida > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    func.setId(id);
                }
            }else{
                throw new DBException("Erro! nenhuma linha foi inserida");
            }
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Funcionario func) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("UPDATE funcionario " 
                                     + "SET nome = ? "
                                     + "WHERE id = ?;");
            
            st.setString(1, func.getNome());
        
            // o id do where
            st.setInt(4, func.getId());
            
            st.executeUpdate();
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    
    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;
        
        try{
            st = conn.prepareStatement("DELETE FROM funcionario " 
                                     + "WHERE id = ?;");
            
            st.setInt(1, id);
            
            int linhas = st.executeUpdate();
            
            if(linhas == 0){
                throw new DBException("Cliente ID não existe!");
            }
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Funcionario findById(int id) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT funcionario.* FROM funcionario "
                                     + "WHERE funcionario.id = ?;");
            
            
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Funcionario funcionario = instanciarFuncionario(rs);
                return funcionario;
            }
            return null;
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }   
    
    @Override
    public Funcionario findByNome(String nome) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT funcionario.* FROM funcionario "
                                     + "WHERE funcionario.nome = ?;");
            
            
            st.setString(1, nome);
            rs = st.executeQuery();
            if(rs.next()){
                Funcionario funcionario = instanciarFuncionario(rs);
                return funcionario;
            }
            return null;
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
    @Override
    public List<Funcionario> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        List<Funcionario> lista = new ArrayList<>();
        
        try{
            st = conn.prepareStatement("SELECT func.* FROM funcionario;");
            
           
            rs = st.executeQuery();
           while(rs.next()){
                Funcionario func = instanciarFuncionario(rs);
                lista.add(func);
            }
            return lista;
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    protected static Funcionario instanciarFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(rs.getInt("id"));
        funcionario.setNome(rs.getString("nomeFunc"));
        
        return funcionario;
    }
    
    

}
