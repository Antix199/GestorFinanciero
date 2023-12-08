package Guis;

import Modelo.Usuario;
import Datos.DatosGastos;
import Modelo.Gasto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Principal {
    private static Usuario usuario;
    private JLabel mostrarMonto;
    private JButton agregarGastoButton;
    private JButton verGastosButton;
    private JButton cerrarSesionButton;
    private JTable tablaGastos; // Cambiado de JList a JTable
    JPanel principal;

    public Principal(Usuario usuario) {
        this.usuario = usuario;

        // Crear columnas del modelo de la tabla
        String[] columnas = {"Nombre del Producto", "Precio", "Categoría"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        tablaGastos.setModel(modeloTabla);

        verGastosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cargar datos en la tabla al presionar el botón "Ver Gastos"
                cargarDatosEnTabla();
            }
        });

        agregarGastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear e mostrar la ventana AgregarGasto con el objeto Usuario
                JFrame agregarGastoFrame = new JFrame("Agregar Gasto");
                agregarGastoFrame.setContentPane(new AgregarGasto(usuario).agregarGasto);
                agregarGastoFrame.setSize(400, 700);
                agregarGastoFrame.setVisible(true);
            }
        });
    }

    private void cargarDatosEnTabla() {
        // Limpiar el modelo de la tabla antes de cargar nuevos datos
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaGastos.getModel();
        modeloTabla.setRowCount(0);

        // Obtener la lista de gastos desde el archivo CSV
        List<Gasto> gastos = DatosGastos.cargarGastos(usuario.getCorreo());

        // Agregar cada gasto al modelo de la tabla
        for (Gasto gasto : gastos) {
            Object[] fila = {gasto.getNombre(), gasto.getCantidad(), gasto.getCategoria()};
            modeloTabla.addRow(fila);
        }
    }

    public static void main(String[] args) {
        JFrame principalFrame = new JFrame("Principal");
        principalFrame.setContentPane(new Principal(usuario).principal);
        principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principalFrame.setSize(400, 700);
        principalFrame.setVisible(true);
    }
}
