package Guis;

import Modelo.CuentaUsuario;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCambiarContrasena extends JFrame{

    private JButton confirmarRegistro;
    private JButton cancelarRegistroButton;
    private JPanel cambioContrasena;
    private JPasswordField nuevaContrasena;
    private JPasswordField contrasenaActual;

    private Usuario usuario;

    public GuiCambiarContrasena(Usuario usuario) {

        super("Ventana cambio contraseña");
        setContentPane(cambioContrasena);

        this.usuario = usuario;

        confirmarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarContrasena();
            }
        });
        cancelarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ManejoGuis.cerrarVentana()){
                    dispose();
                }
            }
        });
    }
    private void cambiarContrasena() {
        String contrasenaActualText = new String(contrasenaActual.getPassword());
        String nuevaContrasenaText = new String(nuevaContrasena.getPassword());

        if (CuentaUsuario.cambiarContrasena(usuario, contrasenaActualText, nuevaContrasenaText)) {
            dispose();
        }
    }
}
