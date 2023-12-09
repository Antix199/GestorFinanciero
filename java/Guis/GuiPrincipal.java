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
    private JLabel grafico;
    private JButton registrarIngresoButton;
    private JLabel nombreUsuario;
    private JLabel categoria1;
    private JLabel categoria2;
    private JLabel categoria3;
    private JLabel categoria4;
    private JLabel categoria5;


    private String rutaImagen = "src/main/java/Guis/grafico.png";

    public GuiPrincipal(Usuario usuario) {

        super("Ventana principal");
        setContentPane(principal);

        this.usuario = usuario;

        Categorias.inicializarCategorias();
        ManejoGuis.mostrarIcono(rutaImagen,grafico,100);
        ManejoGuis.nombrarUsuario(usuario,nombreUsuario);
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
}


