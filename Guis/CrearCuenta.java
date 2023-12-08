package Guis;

import Modelo.Usuario;
import Modelo.ValidarEntradaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class CrearCuenta extends JFrame {
    private JTextField ingresarNombre;
    private JTextField ingresarCorreo;
    private JPasswordField ingresarContraseña;
    private JButton aceptarButton;
    private JButton atrasButton;
    private JPanel crearCuenta;

    public CrearCuenta() {
        // Llamar al constructor de la superclase
        super("Crear Cuenta");
        // Establecer el tamaño de la ventana
        setSize(400, 700);
        // Establecer el panel principal
        setContentPane(crearCuenta);
        // Establecer la operación de cierre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Establecer la ubicación relativa a la ventana padre
        setLocationRelativeTo(null);



        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenemos los valores de los campos de texto
                String nombre = ingresarNombre.getText().trim();
                String correo = ingresarCorreo.getText().trim().toLowerCase();
                String contrasena = new String(ingresarContraseña.getPassword());

                // Validamos los datos ingresados
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                    return;
                }
                if (!ValidarEntradaUsuario.validarFormatoCorreo(correo)) {
                    JOptionPane.showMessageDialog(null, "El formato del correo electrónico no es válido.");
                    return;
                }
                if (Usuario.correoExiste(correo)) {
                    JOptionPane.showMessageDialog(null, "Correo ya en uso. Usa otro.");
                    return;
                }

                // Creamos el nuevo usuario y lo guardamos
                Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena);
                Usuario.guardarUsuario(nuevoUsuario);

                // Mostramos un mensaje de éxito y cerramos la ventana
                JOptionPane.showMessageDialog(null, "Cuenta creada con éxito.");
                dispose();
            }
        });
    }

}
