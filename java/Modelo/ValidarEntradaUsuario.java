package Modelo;

import javax.swing.*;
import java.util.Scanner;

public class ValidarEntradaUsuario {

    public static boolean validarDouble(JTextField valor) {
        try {
            Double.parseDouble(valor.getText());
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean validarInt(JTextField valor) {
        try {
            Integer.parseInt(valor.getText());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Por favor, ingresa un número entero.");
            return false;
        }
    }

    public static boolean validarFormatoCorreo(String correo) {
        String regex = "^[\\w.-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        return correo.matches(regex);
    }

    public static boolean validarContrasena(String contrasena) {
        return contrasena.length() >= 5;
    }

    public static boolean validarCorreo(String correo) {
        return validarFormatoCorreo(correo) && !CuentaUsuario.correoExiste(correo);
    }
    public static boolean validarNombre(String nombre) {
        return !nombre.trim().isEmpty();
    }




    public static boolean esCantidadValida(JTextField valor) {
        double cantidad  = Double.parseDouble(valor.getText());
        if (cantidad > 0) {
            return true;
        } else {
            System.out.println("La cantidad debe ser mayor que cero.");
            return false;
        }
    }
}