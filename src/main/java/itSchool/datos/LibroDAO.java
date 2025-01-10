package itSchool.datos;

import itSchool.model.Libro;

import javax.swing.*;
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
            //System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrio un error al seleccionar datos: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                //System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cerrar la conexion: " + e.getMessage());
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
            //System.out.println("Ocurrio un error al buscar el Libro " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrio un error al buscar el Libro " + e.getMessage());

        } finally {
            try {
                con.close();
            } catch (Exception e) {
                //System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cerrar la conexion: " + e.getMessage());
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
            //System.out.println("Error al agregar Libro " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar Libro" + e.getMessage());

        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                //System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cerrar la conexion: " + e.getMessage());
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
           // System.out.println("Error al modificar registro " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrio un error al modificar el registro " + e.getMessage());

        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                //System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    //ELIMINAR LIBRO
    public static boolean eliminarLibro(Libro libro){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM libros WHERE id=?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getIdLibro());
            ps.execute();
            return true;

        } catch(Exception e){
            //System.out.println("Error al eliminar el Libro " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el Libro " + e.getMessage());

        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                //System.out.println("Ocurrio un error al cerrar la conexion" + e.getMessage());
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }
}
