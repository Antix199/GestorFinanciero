package Guis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiVerGastos extends JFrame {
    private JButton cerrarVentana;
    private JPanel verGastos;

    public GuiVerGastos() {

        super("Ver gastos");
        setContentPane(verGastos);
        cerrarVentana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
