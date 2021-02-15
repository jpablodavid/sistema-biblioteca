package model.entities;


public class Cliente extends Biblioteca{
    
    private String telefone;
    private String email;

    public Cliente() {
    }
    
    public Cliente(String nome, String telefone, String email) {
        super(nome);
        this.telefone = telefone;
        this.email = email;
    }

    public Cliente( int id, String nome, String telefone, String email) {
        super(id, nome);
        this.telefone = telefone;
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente - ID: " + this.getId() +" Nome: " + this.getNome() +  " telefone: " + telefone + " email: " + email;
    }

    
}
