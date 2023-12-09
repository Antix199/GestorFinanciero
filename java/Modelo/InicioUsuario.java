package Modelo;

import javax.swing.*;
import java.util.Optional;
import java.util.Scanner;

public class InicioUsuario {

    private static Scanner scanner = new Scanner(System.in);

    public static void iniciarSistemaDeUsuarios() {
        while (true) {
            Menu.mostrarMenuPrincipal();
            int opcion = obtenerOpcionUsuario();

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    Menu.cerrarSistema();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }

    public static Usuario iniciarSesion(String correo, String contrasena) {
        Optional<Usuario> usuarioOptional = Usuario.buscarUsuarioPorCorreo(correo);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (verificarContrasena(usuario, contrasena)) {
                return usuario;
            }
        }

        return null;
    }

    private static boolean verificarContrasena(Usuario usuario, String contrasena) {
        return usuario.getContrasena().equals(contrasena);
    }

    private static void inicializarSistemaUsuario(Usuario usuario) {
        Categorias.inicializarCategorias();
        Menu menu = new Menu(Categorias.getCategorias(), Categorias.getProductosPorCategoria());//borrar
        Finanzas finanzas = new Finanzas();
        Finanzas.setSaldoActual(usuario);
        Menu.ejecutarMenuUsuario(usuario, menu, finanzas);//borrar
    }

    private static int obtenerOpcionUsuario() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un número válido.");
            return obtenerOpcionUsuario();
        }
    }




}
