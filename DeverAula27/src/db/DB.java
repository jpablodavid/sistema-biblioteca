package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DB {
	
	private static Connection conexao = null;
	
	//metodo para conectar ao banco de dados
	public static Connection getConnection() {
		if (conexao == null) {
			try {
                            /*Properties props = loadProperties();//pegamos as informações
                            String url = props.getProperty("dburl");//pegamos a url no arquivo
                            conexao = DriverManager.getConnection(url, props);//conectamos com o banco de dados*/
                            
                           conexao = DriverManager.getConnection("jdbc:postgresql:Biblioteca",//driver:Servidor:banco
                                                                 "postgres", // usuário
                                                                 "16101981jp"); //senha
                        }catch(SQLException e) {
                            throw new DBException(e.getMessage());
			}
		}
		
		return conexao;
	}
	
	//metodo para fechar a conexao com banco de dados
	public static void closeConnection() {
		if(conexao != null) {
			try {
				conexao.close();
			}catch(SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	
	//metodo para carregar as informações, senha, usuario e o endereço do banco de dados, usando arquivo externo
	private static Properties loadProperties() {
		
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);//le o arquivo e guarda as informaçoes dentro do objeto props
		    
			return props;
		}catch(IOException e) {
			throw new DBException(e.getMessage());
		}
	}
        
        public void query(String comando) {
            
            Statement st = null;
            
            try {
                st = conexao.createStatement();
                st.executeQuery(comando);
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
            
            DB.closeStatement(st);
        }
	
	//metodo para fechar o statement, já com otratamento de erro
	public static void closeStatement(Statement st) {
		
                if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	
	//metodo para fechar o ResultSet, já com otratamento de erro
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}

}
