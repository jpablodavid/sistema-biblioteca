package model.entities;

public class Aluga {
    
    private int id;
    private int cliente;
    private int livro;
    private int funcionario;

    public Aluga() {
    }

    public Aluga(int cliente, int livro, int funcionario) {
        this.cliente = cliente;
        this.livro = livro;
        this.funcionario = funcionario;
    }

    public Aluga(int id, int cliente, int livro, int funcionario) {
        this.id = id;
        this.cliente = cliente;
        this.livro = livro;
        this.funcionario = funcionario;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getLivro() {
        return livro;
    }

    public void setLivro(int livro) {
        this.livro = livro;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + this.cliente;
        hash = 29 * hash + this.livro;
        hash = 29 * hash + this.funcionario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluga other = (Aluga) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.cliente != other.cliente) {
            return false;
        }
        if (this.livro != other.livro) {
            return false;
        }
        return true;
    }
    
    
    

    @Override
    public String toString() {
        return "Alugado - ID: " + id + "cliente:" + cliente + ", livro: " + livro + ", funcionario: " + funcionario;
    }
    
}
