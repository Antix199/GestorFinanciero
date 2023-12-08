package Guis;

import Modelo.InicioUsuario;
import Modelo.Usuario;
import Guis.CrearCuenta;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import static Modelo.Usuario.*;

public class InicioSesion extends JFrame{
    private JPanel inicioSesion;
    private JTextField usuario;
    private JPasswordField password;
    private JButton iniciarSesionButton;
    private JButton crearCuentaButton;
    private JButton salirButton;

    public InicioSesion() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = usuario.getText().trim().toLowerCase();
                String contrasena = new String(password.getPassword());

                Optional<Usuario> usuarioOptional = buscarUsuarioPorCorreo(correo);

                if (usuarioOptional.isPresent()) {
                    Usuario usuario = usuarioOptional.get();
                    if (verificarContrasena(usuario, contrasena)) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombre() + "!");

                        // Modifica esta línea para pasar el objeto Usuario al constructor de Principal
                        JFrame principalFrame = new JFrame("Principal");
                        principalFrame.setContentPane(new Principal(usuario).principal);
                        principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        principalFrame.setSize(400, 700);
                        principalFrame.setVisible(true);
                        // Aquí puedes realizar acciones adicionales después del inicio de sesión, si es necesario.
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Vuelve a intentarlo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El correo electrónico no está registrado. Crea una cuenta.");
                }
            }
        });


        crearCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la clase CrearCuenta
                CrearCuenta ventanaCrearCuenta = new CrearCuenta();

                // Configurar la operación de cierre al cerrar la ventana de CrearCuenta
                ventanaCrearCuenta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Hacer visible la ventana de CrearCuenta
                ventanaCrearCuenta.setVisible(true);
            }
        });


        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
        return cargarUsuarios().stream()
                .filter(usuario -> usuario.getCorreo().equals(correo))
                .findFirst();
    }
    private boolean verificarContrasena(Usuario usuario, String contrasena) {
        return usuario.contrasena.equals(contrasena);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio Sesion");
        frame.setContentPane(new InicioSesion().inicioSesion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setVisible(true);
    }
}


