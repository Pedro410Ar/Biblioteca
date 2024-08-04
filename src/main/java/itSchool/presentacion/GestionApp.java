package itSchool.presentacion;

import itSchool.datos.LibroDAO;
import itSchool.model.Libro;

import java.util.Scanner;
import java.sql.SQLOutput;


public class GestionApp {
    public static void main(String[] args) {
        var salir = false;
        var sc = new Scanner(System.in);

        //Se crea una instancia de Servicio
        var libroDao = new LibroDAO();
        while(!salir){
            try {
                mostrarMenu();
                salir = ejecutarOpciones(sc, libroDao);
            }catch(Exception e){
                System.out.println("Ocurrio un error al ejecutar operacion");
            }
            System.out.println();
        }//FIN WHILE
    }

    private static void mostrarMenu(){
        System.out.print("""
                **************************
                *** GESTION BIBLIOTECA ***
                **************************
                
                1. Listar Libros
                2. Buscar Libro por ID
                3. Agregar Libro
                4. Modificar Libro
                5. Eliminar Libro
                6. Salir
                
                Elige una opción:
                """);
    }

    private static boolean ejecutarOpciones(Scanner sc, LibroDAO libroDAO){
        var opcion = Integer.parseInt(sc.nextLine());
        var salir = false;
        switch(opcion){
            case 1 -> { //LISTAR LIBROS
                System.out.println("** LISTADO DE LIBROS **");
                var libros = libroDAO.listarLibros();
                libros.forEach(System.out::println);
            }
            case 2 -> { //BUSCAR LIBRO POR ID
                System.out.println("INGRESE EL ID DEL LIBRO QUE QUIERE BUSCAR: ");
                var idLibro = Integer.parseInt(sc.nextLine());
                var libro = new Libro(idLibro);
                var encontrado = libroDAO.buscarLibroById(libro);
                if(encontrado)
                    System.out.println("El libro encontrado es: " + libro);
                else
                    System.out.println("No se encontro el Libro indicado");
            }

            case 3 -> { //AGREGAR LIBRO
                System.out.println("** AGREGAR LIBRO AL SISTEMA **");
                System.out.print("Titulo: ");
                var titulo = sc.nextLine();
                System.out.print("Autor: ");
                var autor = sc.nextLine();
                System.out.print("Año de Publicacion: ");
                var fecha = sc.nextInt();


                //crear el objeto libro
                var nuevoLibro = new Libro(titulo, autor, fecha);
                var agregado = libroDAO.agregarLibro(nuevoLibro);
                if(agregado)
                    System.out.println("Libro agregado correctamente");
                else
                    System.out.println("No se pudo agregar el registro");
            }

            case 4 -> { //MODIFICAR LIBRO
                System.out.println("MODIFICAR LIBRO");
                System.out.println("ID Libro: ");
                var idLibro = Integer.parseInt(sc.nextLine());

                System.out.print("Titulo: ");
                var titulo = sc.nextLine();
                System.out.print("Autor: ");
                var autor = sc.nextLine();
                System.out.print("Año de Publicacion: ");
                var fecha = sc.nextInt();


                //Crear el Objeto Libro a modificar
                var nuevoLibro = new Libro(idLibro, titulo, autor, fecha);
                var modificado = libroDAO.modificarLibro(nuevoLibro);
                if(modificado)
                    System.out.println("Libro modificado correctamente");
                else
                    System.out.println("No se pudo modificar el registro");
            }

            case 5 -> { //ELIMINAR LIBRO
                System.out.println("** ELIMINAR REGISTRO **");
                System.out.print("ID del Libro: ");
                var idLibro = Integer.parseInt(sc.nextLine());
                var libro = new Libro(idLibro);
                var eliminado = libroDAO.eliminarLibro(libro);
                if(eliminado)
                    System.out.println("Registro del Libro eliminado correctamente");
                else
                    System.out.println("No se pudo eliminar el Libro");
            }
            case 6 -> { //Salir
                System.out.println("Ha salido del sistema. Hasta pronto....!!");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida");
        }
        return salir;

    }
}