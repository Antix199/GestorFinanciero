package Guis;

import Modelo.Usuario;
import Modelo.Finanzas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// En la clase AgregarGasto
public class AgregarGasto extends JPanel {
    private JList list1;
    JPanel agregarGasto;
    private JTextField dineroRestar;
    private JTextField categoria;
    private JTextField nombreProducto;
    private JButton agregarButton;
    private JButton atrasButton;
    private Usuario usuario;
    // Constructor que acepta un Usuario
    public AgregarGasto(Usuario usuario) {
        this.usuario = usuario;

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acceder al objeto Usuario a través de la variable de instancia
                double cantidadARestar;
                int categoriaSeleccionada;

                try {
                    cantidadARestar = Double.parseDouble(dineroRestar.getText());
                    String categoriaText = categoria.getText().trim();
                    if (!categoriaText.isEmpty() && categoriaText.matches("\\d+")) {
                        categoriaSeleccionada = Integer.parseInt(categoriaText);
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa una categoría válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    cantidadARestar = Double.NaN;
                    categoriaSeleccionada = -1;
                    return;  // Retorna para evitar continuar con la ejecución del código
                }

                if (!Double.isNaN(cantidadARestar) && categoriaSeleccionada >= 0) {
                    Finanzas.registrarGasto(cantidadARestar, categoriaSeleccionada, nombreProducto.getText(), usuario);
                    // Agregar lógica adicional si es necesario, como actualizar la interfaz
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad o categoría no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


}
