package Modelo;

import java.util.ArrayList;
public class Categorias {
    private static final int numCategorias = 5;
    private static final String[] categorias = new String[numCategorias];
    private static final ArrayList<String>[] productosPorCategoria = new ArrayList[numCategorias];


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
    public static String[] getCategorias() {
        return categorias;
    }

    public static ArrayList<String>[] getProductosPorCategoria() {
        return productosPorCategoria;
    }
}

