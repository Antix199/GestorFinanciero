package datos;
import modelo.Usuario;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatosUsuario {

    public static void registrarUsuario(String rutaUsuarios, Usuario usuario) {
        try (FileWriter archivo = new FileWriter(rutaUsuarios, true);
             PrintWriter escribir = new PrintWriter(archivo)) {

            escribir.println(usuario.getNombre() + "," + usuario.getCorreo() + "," + usuario.getContrasena());
            JOptionPane.showMessageDialog(null,"¡Cuenta creada exitosamente!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error al registrar el usuario. Detalles: " + e.getMessage());
        }
    }

    public static List<Usuario> cargarUsuarios(String rutaUsuarios) {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaUsuarios))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Usuario usuario = new Usuario(partes[0], partes[1], partes[2]);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos de usuario, por favor revise la ruta y datos del archivo.");
        }

        return usuarios;
    }

    public static void actualizarInformacionUsuario(Usuario usuario, String rutaUsuarios) {
        List<Usuario> usuarios = DatosUsuario.cargarUsuarios(rutaUsuarios);

        usuarios.removeIf(u -> u.getCorreo().equals(usuario.getCorreo()));
        usuarios.add(usuario);

        guardarUsuarios(usuarios,rutaUsuarios);
    }

    private static void guardarUsuarios(List<Usuario> usuarios, String rutaUsuarios) {
        try (PrintWriter escribir = new PrintWriter(new FileWriter(rutaUsuarios))) {
            for (Usuario usuario : usuarios) {
                escribir.println(usuario.getNombre() + "," + usuario.getCorreo() + "," + usuario.getContrasena());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los usuarios. Detalles: " + e.getMessage());
        }
    }
}