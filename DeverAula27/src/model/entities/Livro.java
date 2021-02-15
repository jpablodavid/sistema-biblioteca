package model.entities;

public class Livro extends Biblioteca{
    
    private String genero;
    private String autor;

    public Livro() {
    }

    public Livro(String nome,String genero, String autor) {
        super(nome);
        this.genero = genero;
        this.autor = autor;
    }

    public Livro(int id, String nome, String genero, String autor) {
        super(id, nome);
        this.genero = genero;
        this.autor = autor;
    } 
    
        
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    @Override
    public String toString() {
        return "Livro - ID: " + this.getId() +" Titulo: " + this.getNome() +  " Genero: " + genero + " Autor: " + autor;
    }

}
