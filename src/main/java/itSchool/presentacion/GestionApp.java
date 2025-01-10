package itSchool.presentacion;

import itSchool.datos.LibroDAO;
import itSchool.model.Libro;

import javax.swing.*;
import java.awt.*;


//METODO MAIN
public class GestionApp {
    static ImageIcon icono = new ImageIcon("src/main/java/itSchool/img/libros3.png");

    public static void main(String[] args) {
        titulo();
        String seleccion = "0";

        //Se crea una instancia de Servicio
        var libroDao = new LibroDAO();

        do {

            String[] opcion = {"1. Listar Libros", "2. Buscar Libros por ID", "3. Agregar Libro", "4. Modificar Libro", "5. Eliminar Libro", "6. Salir"};
            seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un Ítem", "Menú Principal",
                    JOptionPane.QUESTION_MESSAGE, icono, opcion, opcion[0]);


            // Si el usuario presiona "Cancelar" o cierra el diálogo (seleccion == null), salimos del programa
            if (seleccion == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada. Saliendo del programa...");
                System.exit(0); // Cerrar el programa
            }

            switch (seleccion) {
                case "1. Listar Libros":
                    JOptionPane.showMessageDialog(null, "✨✨ Listado de Libros ✨✨");
                    LibroDAO libroDAO = new LibroDAO();
                    var libros = libroDAO.listarLibros();
                    StringBuilder listado = new StringBuilder();
                    libros.forEach(libro -> listado.append(libro).append("\n"));
                    JOptionPane.showMessageDialog(null, listado.toString());
                    break;
                case "2. Buscar Libros por ID":
                    String idInput = JOptionPane.showInputDialog("Ingrese el ID del libro:");
                    if (idInput != null && !idInput.trim().isEmpty()) { // Validar que no sea nulo ni vacío
                        try {
                            int id = Integer.parseInt(idInput.trim()); // Convertir el ID a entero
                            var libro = new Libro(id); // Crear un objeto Libro con el ID
                            boolean encontrado = libroDao.buscarLibroById(libro); // Buscar en la base de datos
                            JOptionPane.showMessageDialog(
                                    null,
                                    encontrado ? "Libro encontrado: " + libro : "No se encontró el libro con ID: " + id
                            );
                        } catch (NumberFormatException e) { // Capturar errores de conversión
                            JOptionPane.showMessageDialog(null, "ID inválido. Intente nuevamente.");
                        }
                    } else { // Manejar entradas vacías
                        JOptionPane.showMessageDialog(null, "Debe ingresar un ID válido.");
                    }
                    break;
                case "3. Agregar Libro":
                    String titulo = JOptionPane.showInputDialog("Título:");
                    String autor = JOptionPane.showInputDialog("Autor:");
                    String fechaInput = JOptionPane.showInputDialog("Año de Publicación:");
                    if (titulo != null && autor != null && fechaInput != null) {
                        int fecha = Integer.parseInt(fechaInput);
                        var nuevoLibro = new Libro(titulo, autor, fecha);
                        boolean agregado = libroDao.agregarLibro(nuevoLibro);
                        JOptionPane.showMessageDialog(null,
                                agregado ? "Libro agregado correctamente" :
                                        "No se pudo agregar el libro.");
                        break;
                    }

                case "4. Modificar Libro":
                    String idInputM = JOptionPane.showInputDialog("Ingrese el ID del libro a modificar:");
                    if (idInputM != null) {
                        try {
                            int id = Integer.parseInt(idInputM);
                            titulo = JOptionPane.showInputDialog("Nuevo título:");
                            autor = JOptionPane.showInputDialog("Nuevo autor:");
                            fechaInput = JOptionPane.showInputDialog("Nuevo año de publicación:");
                            int fecha = Integer.parseInt(fechaInput);
                            var libro = new Libro(id, titulo, autor, fecha);
                            boolean modificado = libroDao.modificarLibro(libro);
                            JOptionPane.showMessageDialog(null,
                                    modificado ? "Libro modificado correctamente." : "No se pudo modificar el libro.");
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Datos inválidos. Intente nuevamente.");
                        }
                    }

                    break;

                case "5. Eliminar Libro":
                    String idEliminar = JOptionPane.showInputDialog("Ingrese el ID del libro a eliminar:");
                    if (idEliminar != null) {
                        try {
                            int idLibro = Integer.parseInt(idEliminar);
                            var libro = new Libro(idLibro);
                            boolean eliminado = LibroDAO.eliminarLibro(libro);
                            JOptionPane.showMessageDialog(
                                    null,
                                    eliminado ? "Libro eliminado correctamente." : "No se pudo eliminar el libro."
                            );
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ID inválido. Intente nuevamente.");
                        }

                    }
                    break;

                case "6. Salir":
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema de Gestion de Libros...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                    break;
            }
        }
        while (!seleccion.equals("6. Salir")) ;
    }

        //----------------METODOS----------------//
        //METODO QUE LLAMA AL TITULO
        public static void titulo () {
            ImageIcon icono = new ImageIcon("src/main/java/itSchool/img/libros3.png");
            UIManager.put("OptionPane.messageFont", new Font("Segoe UI Emoji", Font.BOLD, 14));
            JOptionPane.showMessageDialog(null,
                    "\n     ✨ ✨"
                            + "\n     "
                            + "\n Sistema Gestion de Libros"
                            + "\n  \n"
                            + "                       ✨ ✨ "
                            + "\n"
                            + "\n",
                    "Bienvenido",
                    JOptionPane.INFORMATION_MESSAGE,
                    icono);
        }

    }

