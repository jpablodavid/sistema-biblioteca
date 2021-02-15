package model.entities;

public abstract class Biblioteca {
    
    private int id;
    private String nome;

    public Biblioteca() {}

    public Biblioteca(String nome) {
        this.nome = nome;
    }

    public Biblioteca(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
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
        final Biblioteca other = (Biblioteca) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Biblioteca{" + "id=" + id + ", nome=" + nome + '}';
    }
}
