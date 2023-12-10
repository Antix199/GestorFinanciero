package guis;

import modelo.CuentaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCrearCuenta extends JFrame {
    private JTextField nombreUsuario;
    private JTextField correoUsuario;
    private JPasswordField contrasenaUsuario;
    private JButton confirmarRegistro;
    private JPanel crearUsuario;
    private JButton cancelarRegistroButton;

    public GuiCrearCuenta() {

        super("Crear cuenta de usuario");
        setContentPane(crearUsuario);

        confirmarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreUsuario.getText().trim();
                String correo = correoUsuario.getText().trim().toLowerCase();
                String contrasena = new String(contrasenaUsuario.getPassword());

                if (CuentaUsuario.crearCuenta(nombre, correo, contrasena)) {
                    dispose();

                }
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
}
