package Guis;

import Datos.Saldo;
import Modelo.Finanzas;
import Modelo.Usuario;

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
                if(true){
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
}
