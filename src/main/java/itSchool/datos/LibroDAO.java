package itSchool.datos;

import itSchool.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static itSchool.conexion.Conexion.getConexion;

//DAO - Data Access Object
public class LibroDAO {

    //LISTAR LIBROS
    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM libros ORDER BY id";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var libro = new Libro();
                libro.setIdLibro(rs.getInt("id"));
                libro.setTitulo(rs.getString("Titulo"));
                libro.setAutor(rs.getString("Autor"));
                libro.setFechaPublicacion(rs.getInt("FechaPublicacion"));

                libros.add(libro);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
            }
        }
        return libros;
    }


    //FIND BY ID
    public boolean buscarLibroById(Libro libro) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM libros WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getIdLibro());
            rs = ps.executeQuery();

            if (rs.next()) {
                libro.setTitulo(rs.getString("Titulo"));
                libro.setAutor(rs.getString("Autor"));
                libro.setFechaPublicacion(rs.getInt("FechaPublicacion"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar el Libro " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    //AGREGAR LIBRO
    public boolean agregarLibro(Libro libro){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO libros(Titulo, Autor, FechaPublicacion) " + " VALUES(?, ?, ?)";

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getFechaPublicacion());
            ps.execute();
            return true;

        } catch(Exception e){
            System.out.println("Error al agregar Libro " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    //MODIFICAR REGISTRO
    public boolean modificarLibro(Libro libro){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE libros SET Titulo=?, Autor=?, FechaPublicacion=? " +
                " WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getFechaPublicacion());
            ps.setInt(4, libro.getIdLibro());
            ps.execute();
            return true;

        } catch(Exception e){
            System.out.println("Error al modificar registro " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    //ELIMINAR LIBRO
    public boolean eliminarLibro(Libro libro){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM libros WHERE id=?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getIdLibro());
            ps.execute();
            return true;

        } catch(Exception e){
            System.out.println("Error al eliminar el Libro " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }



/*
    public static void main(String[] args) {
        var libroDao = new LibroDAO();

        //LISTAR LIBROS
        System.out.println("");
        System.out.println("*** Listado de Libros ***");
        System.out.println("");
        List<Libro> libros = libroDao.listarLibros();
        libros.forEach(System.out::println);


        //BUSCAR POR ID
        var libro1 = new Libro(8);
        var encontrado = libroDao.buscarLibroById(libro1);
        if (encontrado)
            System.out.println("Libro encontrado" + libro1);
        else
            System.out.println("No se encontro el libro");

        //AGREGAR LIBRO
        var nuevoLibro = new Libro( "Los 3 Cerditos", "Grimm", 1990);
        var agregado = libroDao.agregarLibro(nuevoLibro);
        if(agregado)
            System.out.println("Libro agregado correctamente");
        else
            System.out.println("No se pudo agregar el Libro indicado");


        //MODIFICAR REGISTRO
        var libroModificar = new Libro(11,"Blancanieves", "Anonimo", 1833);
        var modificado = libroDao.modificarLibro(libroModificar);
        if(modificado)
            System.out.println("Libro modificado correctamente" + libroModificar);
        else
            System.out.println("No se pudo modificar el registro");



        //ELIMINAR ESTUDIANTE
        var libroEliminar = new Libro(8);
        var eliminado = libroDao.eliminarLibro(libroEliminar);
        if(eliminado)
            System.out.println("El Libro fue eliminado correctamente");
        else
            System.out.println("No se pudo eliminar el registro");



    }

 */
}
