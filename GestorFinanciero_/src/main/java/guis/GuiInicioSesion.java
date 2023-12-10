package guis;

import modelo.CuentaUsuario;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiInicioSesion extends JFrame {
    private JPanel inicioSesion;
    private JTextField correoText;
    private JPasswordField contrasenaText;
    private JButton iniciarSesionButton;
    private JButton crearCuentaButton;
    private JButton finalizarProgramaButton;
    private JLabel icono;

    private String rutaImagen = "src/main/java/Guis/imagenes/icono.png";

    public GuiInicioSesion() {

        super("Menu inicio de sesión");
        setContentPane(inicioSesion);

        ManejoGuis.mostrarIcono(rutaImagen,icono,100);
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = correoText.getText().trim().toLowerCase();
                String contrasena= new String(contrasenaText.getPassword());

                modelo.Usuario usuario = CuentaUsuario.iniciarSesion(correo, contrasena);

                if (usuario != null) {
                    // Inicio de sesión exitoso
                    ManejoGuis.abrirGuiPrincipal(usuario);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. ¡Bienvenid@, " + usuario.getNombre() + "!");
                } else {
                    // Inicio de sesión fallido
                    JOptionPane.showMessageDialog(null,"Inicio de sesión fallido. Verifica tus credenciales.");
                }
            }
        });
        crearCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManejoGuis.abrirGuiCrearCuenta();
            }
        });
        finalizarProgramaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManejoGuis.finalizarPrograma();
            }
        });
    }
}
