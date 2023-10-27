import java.util.ArrayList;

public class GestorCategorias {
    private static int numCategorias = 5;
    private static ArrayList<String>[] productosPorCategoria = new ArrayList[numCategorias];
    private static String[] categorias = new String[numCategorias];

    public static void inicializarCategorias() {
        categorias[0] = "Alimentación";
        categorias[1] = "Transporte";
        categorias[2] = "Entretenimiento";
        categorias[3] = "Educación";
        categorias[4] = "Otras";

        for (int i = 0; i < numCategorias; i++) {
            productosPorCategoria[i] = new ArrayList<>();
        }
    }

    public static ArrayList<String>[] getProductosPorCategoria() {
        return productosPorCategoria;
    }

    public static String[] getCategorias() {
        return categorias;
    }
}