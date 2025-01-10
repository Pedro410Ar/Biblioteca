package itSchool.conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConexion() {
        Connection conexion = null;
        var baseDatos = "libros";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "1234M";

        //Cargamos la clase del driver de mysql en memoria
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            //System.out.println("Ocurrio un error en la conexion: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrio un error en la conexion: " + e.getMessage());
        }
        return conexion;
    }
}
