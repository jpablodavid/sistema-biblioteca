package model.dao.impl;

import db.DB;
import db.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.AlugaDAO;
import static model.dao.impl.ClienteDAOJDBC.instanciarCliente;
import static model.dao.impl.FuncionarioDAOJDBC.instanciarFuncionario;
import static model.dao.impl.LivroDAOJDBC.instanciarLivro;
import model.entities.Aluga;
import model.entities.Cliente;
import model.entities.Funcionario;
import model.entities.Livro;


public class AlugaDAOJDBC implements AlugaDAO{

    private Connection conn;
    
    public AlugaDAOJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void insert(Aluga aluga) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("INSERT INTO aluga "
                                     + "(cliente_id, livro_id, funcionario_id) "
                                     + "VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            
            st.setInt(1, aluga.getCliente());
            st.setInt(2, aluga.getLivro());
            st.setInt(3, aluga.getFuncionario());
            
            int linhainserida = st.executeUpdate();
            
            if(linhainserida > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    aluga.setId(id);
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
    public void update(Aluga aluga) {
       PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("UPDATE aluga " 
                                     + "SET cliente_id = ?, livro_id = ?, funcionario_id = ? "
                                     + "WHERE id = ?;");
            
            st.setInt(1, aluga.getCliente());
            st.setInt(2, aluga.getLivro());
            st.setInt(3, aluga.getFuncionario());
            // o id do where
            st.setInt(4, aluga.getId());
            
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
            st = conn.prepareStatement("DELETE FROM aluga " 
                                     + "WHERE id = ?;");
            
            st.setInt(1, id);
            
            int linhas = st.executeUpdate();
            
            if(linhas == 0){
                throw new DBException("Aluguel de  ID não existe!");
            }
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Aluga findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT aluga.*, cliente.*, livros.*, funcionario.* "
                                     + "FROM aluga INNER JOIN cliente ON aluga.cliente_id = cliente.id "
                                     + "INNER JOIN livros ON aluga.livro_id = livros.id "
                                     + "INNER JOIN funcionario ON aluga.funcionario_id = funcionario.id "
                                     + "WHERE aluga.id = ?;");
            
            st.setInt(1, id);
            
            rs = st.executeQuery();
            
            if(rs.next()){
                Cliente cliente = instanciarCliente(rs);
                Livro livro = instanciarLivro(rs);
                Funcionario func = instanciarFuncionario(rs);
                Aluga aluga = instanciarAluga(rs);
                System.out.println("Aluga ID:" + aluga.getId() + " / Cliente: " + cliente.getNome()
                                   + " / Titulo: " + livro.getNome() + " / Funcionario: " + func.getNome());
                return aluga;
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
    public List<Livro> findByCliente(Cliente cliente) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("SELECT livros.*, cliente.* , aluga.*"
                                     + "FROM aluga INNER JOIN cliente ON aluga.cliente_id = cliente.id "
                                     + "INNER JOIN livros ON aluga.livro_id = livros.id "
                                     + "WHERE cliente.id = ? "
                                     + "ORDER BY nome;");
            
            st.setInt(1, cliente.getId());
            rs = st.executeQuery();
            
            List<Livro> listaLivros = new ArrayList<>();
            Map<Integer, Cliente> map = new HashMap<>();
            
            if(rs.next()){
                
                Cliente c = map.get(rs.getInt("cliente_id"));
                
                if(c == null){
                    c = instanciarCliente(rs);
                    map.put(rs.getInt("cliente_id"), c);
                }
               
                Livro livro = instanciarLivro(rs);
                listaLivros.add(livro);
                
            }
            return listaLivros;
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Aluga findByFuncionario(String nome) {
        return null;
    }

    @Override
    public List<Aluga> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        List<Aluga> lista = new ArrayList<>();
        
        try{
            st = conn.prepareStatement("SELECT aluga.*, cliente.*, livros.*, funcionario.* "
                                     + "FROM aluga INNER JOIN cliente ON aluga.cliente_id = cliente.id "
                                     + "INNER JOIN livros ON aluga.livro_id = livros.id "
                                     + "INNER JOIN funcionario ON aluga.funcionario_id = funcionario.id ");
            
           
            rs = st.executeQuery();
            while(rs.next()){
                Cliente cliente = instanciarCliente(rs);
                Livro livro = instanciarLivro(rs);
                Funcionario func = instanciarFuncionario(rs);
                Aluga aluga = instanciarAluga(rs);
                System.out.println("Aluga ID:" + aluga.getId() + " / Cliente: " + cliente.getNome()
                                   + " / Titulo: " + livro.getNome() + " / Funcionario: " + func.getNome());
                lista.add(aluga);
            }
            return lista;
            
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Aluga instanciarAluga(ResultSet rs) throws SQLException {
        Aluga aluga = new Aluga();
        aluga.setId(rs.getInt("id"));
        aluga.setCliente(rs.getInt("cliente_id"));
        aluga.setLivro(rs.getInt("livro_id"));
        aluga.setFuncionario(rs.getInt("funcionario_id"));
        
        return aluga;
    }
    
   
}
