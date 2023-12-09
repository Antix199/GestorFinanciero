package Guis;

import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class ManejoGuis {

    public static void abrirVentanaInicioSesion() {
        GuiInicioSesion inicioSesion = new GuiInicioSesion();
        inicioSesion.setSize(400, 700);
        inicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicioSesion.setLocationRelativeTo(null);
        inicioSesion.setVisible(true);
    }

    public static void abrirGuiPrincipal(Usuario usuario) {
        GuiPrincipal ventanaPrincipal = new GuiPrincipal(usuario);
        ventanaPrincipal.setSize(400, 700);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);
    }

    public static void abrirGuiCrearCuenta() {
        GuiCrearCuenta ventanaCrearCuenta = new GuiCrearCuenta();
        ventanaCrearCuenta.setSize(400, 700);
        ventanaCrearCuenta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaCrearCuenta.setLocationRelativeTo(null);
        ventanaCrearCuenta.setVisible(true);
    }

    public static void abrirGuiRegistrarGasto(Usuario usuario) {
        GuiRegistrarGasto guiRegistrarIngreso = new GuiRegistrarGasto(usuario);
        guiRegistrarIngreso.setSize(400, 700);
        guiRegistrarIngreso.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        guiRegistrarIngreso.setLocationRelativeTo(null);
        guiRegistrarIngreso.setVisible(true);
    }

    public static void abrirGuiVerGasto() {
        GuiVerGastos guiVerGastos = new GuiVerGastos();
        guiVerGastos.setSize(400, 700);
        guiVerGastos.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        guiVerGastos.setLocationRelativeTo(null);
        guiVerGastos.setVisible(true);
    }
    public static void abrirGuiRegistrarIngreso(Usuario usuario) {
        GuiRegistrarIngreso guiRegistrarIngreso = new GuiRegistrarIngreso(usuario);
        guiRegistrarIngreso.setSize(400, 700);
        guiRegistrarIngreso.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        guiRegistrarIngreso.setLocationRelativeTo(null);
        guiRegistrarIngreso.setVisible(true);
    }
    public static void mostrarIcono(String rutaImagen, JLabel icon,int a) {
        ImageIcon imagen = new ImageIcon(new ImageIcon(rutaImagen).getImage().getScaledInstance(a, a, Image.SCALE_DEFAULT));
        icon.setIcon(imagen);
    }

    public static void nombrarUsuario(Usuario usuario, JLabel nombre){
        nombre.setText("Gestor de finanzas de " + usuario.getNombre());
    }

    public static void mostrarSaldo(double saldoActual,JLabel mostrarSaldo){
        mostrarSaldo.setText("Fondos totales: $" + saldoActual);
    }

    public static void finalizarPrograma() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    public static boolean cerrarVentana() {

        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            return true;
        }
        else return false;
    }

}

