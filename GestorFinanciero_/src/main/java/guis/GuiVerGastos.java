package guis;

import datos.DatosGastos;
import utils.CalculadoraPorcentajeGastos;
import modelo.Gasto;
import modelo.Usuario;
import modelo.Finanzas;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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

        DefaultMutableTreeNode root = construirArbolGastosPorCategoria(gastosPorCategoria);
        actualizarModeloArbol(tree, root);

        tree.setCellRenderer(new ComportamientoCeldasJTree());
    }




    private static DefaultMutableTreeNode construirArbolGastosPorCategoria(Map<String, List<Gasto>> gastosPorCategoria) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Gastos por Categoría");

        for (Map.Entry<String, List<Gasto>> entry : gastosPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            List<Gasto> gastosEnCategoria = entry.getValue();
            DefaultMutableTreeNode categoriaNode = construirNodoCategoria(categoria, gastosEnCategoria);
            root.add(categoriaNode);
        }

        return root;
    }

    private static DefaultMutableTreeNode construirNodoCategoria(String categoria, List<Gasto> gastosEnCategoria) {
        DefaultMutableTreeNode categoriaNode = new DefaultMutableTreeNode(categoria);

        double totalPorCategoria = CalculadoraPorcentajeGastos.calcularTotalGastadoPorCategoria(gastosEnCategoria, categoria);
        categoriaNode.setUserObject(categoria + ": TOTAL $" + totalPorCategoria);

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
