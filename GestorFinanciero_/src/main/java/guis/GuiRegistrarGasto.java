package guis;

import datos.Saldo;
import modelo.Finanzas;
import modelo.Usuario;
import utils.ValidarEntradaUsuario;

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
                if (ManejoGuis.cerrarVentana()) {
                    ManejoGuis.abrirGuiPrincipal(usuario);
                    dispose();
                }
            }
        });


    }

    private boolean validarEntrada() {
        return ValidarEntradaUsuario.validarDouble(montoGasto) &&
                ValidarEntradaUsuario.validarDoublePositivo(montoGasto) &&
                ValidarEntradaUsuario.validarNombre(nombreGasto.getText()) &&
                ValidarEntradaUsuario.validarInt(categoriaGasto);
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private boolean registrarGasto(Usuario usuario) {
        try {
            if (validarEntrada()) {
                double monto = Double.parseDouble(montoGasto.getText());
                String nombre = nombreGasto.getText();
                int categoria = Integer.parseInt(categoriaGasto.getText());

                if (Finanzas.esCategoriaValida(categoria)) {
                    Finanzas.registrarGasto(monto, categoria, nombre, usuario);
                    mostrarMensajeExito("Gasto registrado con éxito");
                    return true;
                } else {
                    mostrarMensajeError("Categoría inválida");
                }
            } else {
                mostrarMensajeError("Entrada inválida.\n Asegúrate de ingresar valores \n válidos en todos los campos.");
            }
        } catch (NumberFormatException e) {
            mostrarMensajeError("Entrada inválida.\n Asegúrate de ingresar valores  \n válidos en todos los campos.");
        }
        return false;
    }

}
