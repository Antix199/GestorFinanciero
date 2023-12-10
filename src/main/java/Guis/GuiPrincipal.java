package Guis;
import Datos.DatosGastos;
import Modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class GuiPrincipal extends JFrame {
    private static Usuario usuario;
    private JPanel principal;
    private JLabel mostrarSaldo;
    private JButton registrarGastoButton;
    private JButton verGastosButton;
    private JButton cerrarSesionButton;
    private JButton registrarIngresoButton;
    private JLabel nombreUsuario;
    private JLabel categoria1;
    private JLabel categoria2;
    private JLabel categoria3;
    private JLabel categoria4;
    private JLabel categoria5;
    private JButton cambiarContrasena;
    private JLabel iconoC1;
    private JLabel iconoC2;
    private JLabel iconoC3;
    private JLabel iconoC4;
    private JLabel iconoC5;


    public GuiPrincipal(Usuario usuario) {

        super("Ventana principal");
        setContentPane(principal);

        this.usuario = usuario;

        List<Gasto> gastos = DatosGastos.cargarGastos(usuario.getCorreo());
        Categorias.inicializarCategorias();
        ManejoGuis.nombrarUsuario(usuario,nombreUsuario);
        mostrarIconos();
        mostrarPorcentajeGastos();
        Finanzas.setSaldoActual(usuario);
        ManejoGuis.mostrarSaldo(Finanzas.getSaldoActual(),mostrarSaldo);

        registrarGastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManejoGuis.abrirGuiRegistrarGasto(usuario);
                dispose();

            }
        });
        verGastosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManejoGuis.abrirGuiVerGasto(usuario);
            }
        });
        registrarIngresoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManejoGuis.abrirGuiRegistrarIngreso(usuario);
                dispose();
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ManejoGuis.cerrarVentana()){
                    JOptionPane.showMessageDialog(null,"¡Hasta pronto!");
                    dispose();
                    ManejoGuis.abrirVentanaInicioSesion();
                }

            }
        });
        cambiarContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManejoGuis.abrirGuiCambioContrasena(usuario);
            }
        });
    }


    public static void mostrarPorcentajeGastoEnLabel(List<Gasto> gastos, String categoria, JLabel label) {
        double totalGastado = CalculadoraPorcentajeGastos.calcularTotalGastado(gastos);

        if (totalGastado > 0) {
            double totalGastadoPorCategoria = CalculadoraPorcentajeGastos.calcularTotalGastadoPorCategoria(gastos, categoria);
            double porcentaje = (totalGastadoPorCategoria / totalGastado) * 100;

            String texto = String.format(" %s: %.2f%%", categoria, porcentaje);
            label.setText(texto);
        }
    }
    private void mostrarPorcentajeGastos() {

        List<Gasto> gastos = DatosGastos.cargarGastos(usuario.getCorreo());
        mostrarPorcentajeGastoEnLabel( gastos,"Alimentación", categoria1);
        mostrarPorcentajeGastoEnLabel( gastos,"Transporte", categoria2);
        mostrarPorcentajeGastoEnLabel( gastos,"Entretenimiento", categoria3);
        mostrarPorcentajeGastoEnLabel( gastos,"Educación", categoria4);
        mostrarPorcentajeGastoEnLabel( gastos,"Otras", categoria5);
    }

    private void mostrarIconos(){
        ManejoGuis.mostrarIcono(rutaImagen("Alimentación"),iconoC1,40);
        ManejoGuis.mostrarIcono(rutaImagen("Transporte"),iconoC2,40);
        ManejoGuis.mostrarIcono(rutaImagen("Entretenimiento"),iconoC3,40);
        ManejoGuis.mostrarIcono(rutaImagen("Educación"),iconoC4,40);
        ManejoGuis.mostrarIcono(rutaImagen("Otras"),iconoC5,40);
    }

    private String rutaImagen(String categoria){

        String nombreArchivo = categoria + ".png";
        return "src/main/java/Guis/imagenes/"+ nombreArchivo;
    }
}


