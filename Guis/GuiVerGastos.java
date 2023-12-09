package Guis;

import Datos.DatosGastos;
import Modelo.Gasto;
import Modelo.Usuario;
import Modelo.Finanzas;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class GuiVerGastos extends JFrame {
    private JButton cerrarVentana;
    private JPanel verGastos;
    private JTree tree1;

    public GuiVerGastos(Usuario usuario) {
        super("Ver gastos");
        setContentPane(verGastos);

        mostrarGastosPorCategoria(usuario, tree1);

        cerrarVentana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ManejoGuis.cerrarVentana()) {
                    dispose();
                }
            }
        });
    }



    private static void mostrarGastosPorCategoria(Usuario usuario, JTree tree) {
        List<Gasto> gastos = DatosGastos.cargarGastos(usuario.getCorreo());
        Map<String, List<Gasto>> gastosPorCategoria = Finanzas.agruparGastosPorCategoria(gastos);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Gastos por Categoría");
        construirArbolGastosPorCategoria(gastosPorCategoria, root);
        actualizarModeloArbol(tree, root);

        // Aquí es donde agregas el renderizador de celdas personalizado
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                          boolean selected, boolean expanded,
                                                          boolean leaf, int row, boolean hasFocus) {
                Component component = super.getTreeCellRendererComponent(tree, value, selected,
                        expanded, leaf, row, hasFocus);
                if (component instanceof JLabel && value instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    if (node.getUserObject() instanceof String) {
                        String text = (String) node.getUserObject();
                        ((JLabel)component).setText("<html><p width=\"200\">" + text + "</p></html>");
                    }
                }
                return component;
            }
        });
    }



    private static void construirArbolGastosPorCategoria(Map<String, List<Gasto>> gastosPorCategoria, DefaultMutableTreeNode root) {
        for (Map.Entry<String, List<Gasto>> entry : gastosPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            List<Gasto> gastosEnCategoria = entry.getValue();
            DefaultMutableTreeNode categoriaNode = construirNodoCategoria(categoria, gastosEnCategoria);
            root.add(categoriaNode);
        }
    }


    private static DefaultMutableTreeNode construirNodoCategoria(String categoria, List<Gasto> gastosEnCategoria) {
        DefaultMutableTreeNode categoriaNode = new DefaultMutableTreeNode(categoria);

        for (Gasto gasto : gastosEnCategoria) {
            DefaultMutableTreeNode gastoNode = construirNodoGasto(gasto);
            categoriaNode.add(gastoNode);
        }

        return categoriaNode;
    }


    private static DefaultMutableTreeNode construirNodoGasto(Gasto gasto) {
        return new DefaultMutableTreeNode(gasto.getNombre() + ": $" + gasto.getCantidad());
    }


    private static void actualizarModeloArbol(JTree tree, DefaultMutableTreeNode root) {
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        tree.setModel(treeModel);
    }
}
