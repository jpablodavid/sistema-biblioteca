package model.entities;


public class Funcionario extends Biblioteca{

    public Funcionario() {
    }
    
    
    public Funcionario(String nome) {
        super(nome);
    }

    public Funcionario(int id, String nome) {
        super(id, nome);
    }
    
    @Override
    public String toString() {
        return "Funcionario - ID: " + this.getId() +" Nome: " + this.getNome();
    }
}
