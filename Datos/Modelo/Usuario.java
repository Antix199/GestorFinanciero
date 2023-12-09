package Modelo;

import Datos.DatosUsuario;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.Optional;

public class Usuario {

    private String nombre;
    private String correo;
    private String contrasena;
    public static String carpetaUsuarios = "Usuarios";
    private static String rutaUsuarios = System.getProperty("user.dir") + File.separator + carpetaUsuarios + File.separator + "usuarios.csv";


    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }


    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    static {
        File carpeta = new File(carpetaUsuarios);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }
    public static boolean correoExiste(String correo) {
        List<Usuario> usuarios = DatosUsuario.cargarUsuarios(rutaUsuarios);
        return usuarios.stream().anyMatch(usuario -> usuario.correo.equals(correo));
    }

    public static boolean crearCuenta(String nombre, String correo, String contrasena) {
        if (ValidarEntradaUsuario.validarNombre(nombre) && ValidarEntradaUsuario.validarCorreo(correo) && ValidarEntradaUsuario.validarContrasena(contrasena)) {
            DatosUsuario.registrarUsuario(rutaUsuarios, new Usuario(nombre, correo, contrasena));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error: Los datos ingresados no son válidos. \n Verifica la información proporcionada.");
            return false;
        }
    }

    public static Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
        return DatosUsuario.cargarUsuarios(rutaUsuarios).stream()
                .filter(usuario -> usuario.correo.equals(correo))
                .findFirst();
    }

}
