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
import model.dao.LivroDAO;
import static model.dao.impl.LivroDAOJDBC.instanciarLivro;
import model.entities.Livro;


public class LivroDAOJDBC implements LivroDAO{
    
    private Connection conn;
    
    public LivroDAOJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Livro livro) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("INSERT INTO livros " 
                                     + "(nome, genero, autor) "
                                     + "VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS );
            
            st.setString(1, livro.getNome());
            st.setString(2, livro.getGenero());
            st.setString(3, livro.getAutor());
            
            int linhainserida = st.executeUpdate();
            
            if(linhainserida > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    livro.setId(id);
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
    public void update(Livro livro) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("UPDATE livro " 
                                     + "SET nome = ?, genero = ?, autor = ? "
                                     + "WHERE id = ?;");
            
            st.setString(1, livro.getNome());
            st.setString(2, livro.getGenero());
            st.setString(3, livro.getAutor());
            // o id do where
            st.setInt(4, livro.getId());
            
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
            st = conn.prepareStatement("DELETE FROM livros " 
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
    public Livro findById(int id) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT livro.* FROM livro "
                                     + "WHERE livro.id = ?;");
            
            
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Livro livro = instanciarLivro(rs);
                return livro;
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
    public Livro findByNome(String nome) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT livro.* FROM livro "
                                     + "WHERE livro.nome = ?;");
            
            
            st.setString(1, nome);
            rs = st.executeQuery();
            if(rs.next()){
                Livro livro = instanciarLivro(rs);
                return livro;
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
    public List<Livro> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        List<Livro> lista = new ArrayList<>();
        
        try{
            st = conn.prepareStatement("SELECT livro.* FROM livro;");
            
           
            rs = st.executeQuery();
           while(rs.next()){
                Livro livro = instanciarLivro(rs);
                lista.add(livro);
            }
            return lista;
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    protected static Livro instanciarLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setId(rs.getInt("id"));
        livro.setNome(rs.getString("titulo"));
        livro.setGenero(rs.getString("genero"));
        livro.setAutor(rs.getString("autor"));
        return livro;
    }
}
