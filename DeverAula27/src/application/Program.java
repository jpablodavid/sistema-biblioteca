package application;

import java.util.List;
import java.util.Scanner;
import model.dao.AlugaDAO;
import model.dao.ClienteDAO;
import model.dao.DaoFactory;
import model.entities.Aluga;
import model.entities.Cliente;
import model.entities.Livro;


public class Program {
    
    
    public static void main(String[] args) {
       
        /*ClienteDAO clienteDao = DaoFactory.createClienteDAO();
        
        System.out.println("Teste find id");
        Cliente cliente = clienteDao.findById(3);
        System.out.println(cliente);*/
        
        /*System.out.println("Teste find nome");
        cliente = clienteDao.findByNome("Tony stark");
        System.out.println(cliente);*/
        
        /*System.out.println("Teste Find All");
        List<Cliente> clientes = clienteDao.findAll();
        for(Cliente c : clientes){
            System.out.println(c);
        }*/
        
        /*System.out.println("Teste insert");
        Cliente novo = new Cliente("Ronildo lopes", "65 9874-235690", "xup@gmail.com");
        clienteDao.insert(novo);
        System.out.println("Novo Cliente: ID: " + novo.getId() + ", nome : " + novo.getNome());*/
        
        /*System.out.println("teste UPDATE");
        cliente = clienteDao.findById(8);
        cliente.setNome("Tony stark");
        cliente.setTelefone("25 8963-45621");
        cliente.setEmail("ironMan@gmail.com");
        clienteDao.update(cliente);
        System.out.println("UPdate");*/
        
        /*Scanner sc = new Scanner(System.in);
        System.out.println("Teste DELETE");
        System.out.println("Digite o id para deletar:");
        int id = sc.nextInt();
        clienteDao.deleteById(id);
        System.out.println("Deletado");
        sc.close();*/
        
        AlugaDAO alugaDao = DaoFactory.createAlugaDAO();
        
        
        /*System.out.println("Teste insert Aluga");
        Aluga aluga = new Aluga(1, 8, 2);
        alugaDao.insert(aluga);
        System.out.println("Novo Aluga: ID: " + aluga.getId() + ", Cliente : " + aluga.getCliente()
                           + ", Livro : " + aluga.getLivro() + ", Funcionario : " + aluga.getFuncionario());*/
        
        /*System.out.println("teste UPDATE");
        Aluga aluga = alugaDao.findById(2);
        aluga.setCliente(7);
        aluga.setLivro(6);
        aluga.setFuncionario(3);
        alugaDao.update(aluga);
        System.out.println("UPdate");
        System.out.println(aluga);*/
        
        
        /*System.out.println("Teste DELETE");
        int id = 1;
        alugaDao.deleteById(id);
        System.out.println("Deletado");*/
        
        System.out.println("Test find Cliente");
        Cliente cliente = new Cliente(3, "Maria Grenn", "21 8888-89636", "maria@gmail.com");
        List<Livro> listaLivros = alugaDao.findByCliente(cliente);
        for (Livro livro : listaLivros) {
            System.out.println(livro);
        }
        
        System.out.println(listaLivros.size());
    }

}
