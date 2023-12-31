package guis;

import datos.Saldo;
import modelo.Finanzas;
import modelo.Usuario;
import utils.ValidarEntradaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRegistrarIngreso extends JFrame {
    private JTextField montoIngreso;
    private JButton confirmarIngreso;
    private JButton cancelarRegistro;
    private JPanel registrarIngreso;

    public GuiRegistrarIngreso(Usuario usuario) {

        super("Registrar ingreso");
        setContentPane(registrarIngreso);
        confirmarIngreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ValidarEntradaUsuario.validarDouble(montoIngreso)){
                    if(ValidarEntradaUsuario.validarDoublePositivo(montoIngreso)){
                        double monto = Double.parseDouble(montoIngreso.getText());
                        Finanzas.anadirDinero(usuario, monto);
                        Saldo.guardarSaldoCSV(usuario);
                        ManejoGuis.abrirGuiPrincipal(usuario);
                        dispose();
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"      Formato inválido \n Porfavor ingrese un número. ","Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        });
        cancelarRegistro.addActionListener(new ActionListener() {
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
