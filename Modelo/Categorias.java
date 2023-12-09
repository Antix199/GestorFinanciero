package Modelo;

import java.util.ArrayList;
import java.util.Scanner;
public class Categorias {
    private static int numCategorias = 5;
    private static String[] categorias = new String[numCategorias];
    private static ArrayList<String>[] productosPorCategoria = new ArrayList[numCategorias];


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

