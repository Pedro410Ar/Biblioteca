package itSchool.model;

//CLASE LIBRO CON ATRIBUTOS
public class Libro {

    //ATRIBUTOS
     private int idLibro;
     private String titulo;
     private String autor;
     private int fechaPublicacion;

     //CONSTRUCTORES
    public Libro() {
    }

    public Libro(int idLibro) {
        this.idLibro = idLibro;
    }

    public Libro(String titulo, String autor, int fechaPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Libro(int idLibro, String titulo, String autor, int fechaPublicacion) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    //GETTER Y SETTER
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(int fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    //METODO TO STRING
    @Override
    public String toString() {
        return "Libro {" +
                idLibro +
                ", '" + titulo + '\'' +
                ", Autor = '" + autor + '\'' +
                ", Año de Publicacion = " + fechaPublicacion +
                '}';
    }
}
