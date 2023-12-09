package Modelo;

import Datos.DatosUsuario;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CuentaUsuario {

    private static String rutaUsuarios = System.getProperty("user.dir") + File.separator + "usuarios.csv";

    private static Scanner scanner = new Scanner(System.in);


    public static Usuario iniciarSesion(String correo, String contrasena) {
        Optional<Usuario> usuarioOptional = buscarUsuarioPorCorreo(correo);

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

    private static int obtenerOpcionUsuario() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un número válido.");
            return obtenerOpcionUsuario();
        }
    }


    public static boolean correoExiste(String correo) {
        List<Usuario> usuarios = DatosUsuario.cargarUsuarios(rutaUsuarios);
        return usuarios.stream().anyMatch(usuario -> usuario.getCorreo().equals(correo));
    }


    public static boolean crearCuenta(String nombre, String correo, String contrasena) {
        if (ValidarEntradaUsuario.validarNombre(nombre) && ValidarEntradaUsuario.validarCorreo(correo) && ValidarEntradaUsuario.validarContrasena(contrasena)) {
            DatosUsuario.registrarUsuario(rutaUsuarios,new Usuario(nombre, correo, contrasena));
            return true;
        } else {
            JOptionPane.showMessageDialog(null,"Error: Los datos ingresados no son válidos. \n Verifica la información proporcionada.");
            return false;
        }
    }

    public static Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
        return DatosUsuario.cargarUsuarios(rutaUsuarios).stream()
                .filter(usuario -> usuario.getCorreo().equals(correo))
                .findFirst();
    }
}
