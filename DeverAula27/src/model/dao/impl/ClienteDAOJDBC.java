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
import model.dao.ClienteDAO;
import model.entities.Cliente;


public class ClienteDAOJDBC implements ClienteDAO{
    
    private Connection conn;
    
    public ClienteDAOJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Cliente cliente) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("INSERT INTO cliente " 
                                     + "(nome, telefone, email) "
                                     + "VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS );
            
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getTelefone());
            st.setString(3, cliente.getEmail());
            
            int linhainserida = st.executeUpdate();
            
            if(linhainserida > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    cliente.setId(id);
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
    public void update(Cliente cliente) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("UPDATE cliente " 
                                     + "SET nome = ?, telefone = ?, email = ? "
                                     + "WHERE id = ?;");
            
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getTelefone());
            st.setString(3, cliente.getEmail());
            // o id do where
            st.setInt(4, cliente.getId());
            
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
            st = conn.prepareStatement("DELETE FROM cliente " 
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
    public Cliente findById(int id) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT cliente.* FROM cliente "
                                     + "WHERE cliente.id = ?;");
            
            
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Cliente cliente = instanciarCliente(rs);
                return cliente;
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
    public Cliente findByNome(String nome) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT cliente.* FROM cliente "
                                     + "WHERE cliente.nome = ?;");
            
            
            st.setString(1, nome);
            rs = st.executeQuery();
            if(rs.next()){
                Cliente cliente = instanciarCliente(rs);
                return cliente;
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
    public List<Cliente> findAll() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        List<Cliente> lista = new ArrayList<>();
        
        try{
            st = conn.prepareStatement("SELECT cliente.* FROM cliente;");
            
           
            rs = st.executeQuery();
           while(rs.next()){
                Cliente cliente = instanciarCliente(rs);
                lista.add(cliente);
            }
            return lista;
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    protected static Cliente instanciarCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        
        return cliente;
    }
}
