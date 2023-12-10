package Modelo;

import javax.swing.*;


public class ValidarEntradaUsuario {


    public static boolean validarDouble(JTextField valor) {
        try {
            Double.parseDouble(valor.getText());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean validarInt(JTextField valor) {
        try {
            Integer.parseInt(valor.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarFormatoCorreo(String correo) {
        String regex = "^[a-zA-Z0-9]+([\\w.-]*[a-zA-Z0-9])?@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        return correo.matches(regex);
    }

    public static boolean validarContrasena(String contrasena) {
        return contrasena.length() >= 5 && !contrasena.contains(" ");
    }


    public static boolean validarCorreo(String correo) {
        return validarFormatoCorreo(correo) && !CuentaUsuario.correoExiste(correo);
    }
    public static boolean validarNombre(String nombre) {

        return !nombre.trim().isEmpty();
    }


    public static boolean validarDoublePositivo(JTextField valor) {
        double cantidad  = Double.parseDouble(valor.getText());
        if (cantidad > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,"Debe ingresar un valor positivo");
            return false;
        }
    }
}