package Guis;

import Datos.Saldo;
import Modelo.Finanzas;
import Modelo.Usuario;
import Modelo.Categorias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRegistrarGasto extends JFrame {
    private JPanel registrarGasto;
    private JButton confirmarGasto;
    private JButton cancelarRegistroButton;
    private JTextField montoGasto;
    private JTextField categoriaGasto;
    private JTextField nombreGasto;
    private JTextArea categoriasDisponibles;

    public GuiRegistrarGasto(Usuario usuario) {

        super("Registrar gasto");
        setContentPane(registrarGasto);

        categoriasDisponibles.setText("1.Alimentación \n 2.Transporte\n 3.Entretenimiento\n 4.Educación\n 5.Otras");
        confirmarGasto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (registrarGasto(usuario)) {
                    Saldo.guardarSaldoCSV(usuario);
                    Finanzas.setSaldoActual(usuario);
                    ManejoGuis.abrirGuiPrincipal(usuario);
                    dispose();
                }

            }
        });
        cancelarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ManejoGuis.cerrarVentana()){
                    ManejoGuis.abrirGuiPrincipal(usuario);
                    dispose();
                }
            }
        });


    }

    private boolean registrarGasto(Usuario usuario) {
        try {
            double monto = Double.parseDouble(montoGasto.getText());
            String nombre = nombreGasto.getText();
            int categoriaSeleccionada = Integer.parseInt(categoriaGasto.getText());

            if (Finanzas.esCantidadValida(monto) && Finanzas.esCategoriaValida(categoriaSeleccionada)) {
                Finanzas.restarDinero(usuario, monto, categoriaSeleccionada, nombre);
                JOptionPane.showMessageDialog(this, "Gasto registrado con éxito");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Entrada inválida. Verifica el monto y la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Entrada inválida.\n Asegúrate de ingresar números \n en los campos de monto y categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
