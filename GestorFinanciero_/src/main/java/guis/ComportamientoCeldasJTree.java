package guis;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class ComportamientoCeldasJTree extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected,
                expanded, leaf, row, hasFocus);

        if (component instanceof JLabel && value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            if (node.getUserObject() instanceof String) {
                String text = (String) node.getUserObject();
                ((JLabel) component).setText("<html><p width=\"200\">" + text + "</p></html>");
            }
        }

        return component;
    }
}
