package Modelo;

import javax.swing.*;
import java.util.Optional;
import java.util.Scanner;

public class InicioUsuario {

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


}
