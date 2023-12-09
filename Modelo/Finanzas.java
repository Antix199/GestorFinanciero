package Modelo;

import Datos.DatosGastos;
import Datos.Saldo;


import java.util.*;

public class Finanzas {

    public static double saldoActual = 0;
    private static int numCategorias = 5;
    private static double[] gastosPorCategoria = new double[numCategorias];
    private static double totalGastado = 0;
    private static ArrayList<String>[] productosPorCategoria = Categorias.getProductosPorCategoria();
    private static String[] categorias = Categorias.getCategorias();


    public static void anadirDinero(Usuario usuario, double monto) {
        saldoActual += monto;
    }

    public static double getSaldoActual() {
        return saldoActual;
    }

    public static void setSaldoActual(Usuario usuario) {
        saldoActual = Saldo.cargarSaldoUsuario(usuario.getCorreo());
    }

    public static Map<String, List<Gasto>> agruparGastosPorCategoria(List<Gasto> gastos) {
        Map<String, List<Gasto>> gastosPorCategoria = new HashMap<>();

        for (Gasto gasto : gastos) {
            String categoria = gasto.getCategoria();
            gastosPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).add(gasto);
        }
        return gastosPorCategoria;
    }

    public static boolean esCategoriaValida(int categoriaSeleccionada) {
        return categoriaSeleccionada >= 1 && categoriaSeleccionada <= numCategorias;
    }

    public static void registrarGasto(double cantidadARestar, int categoriaSeleccionada, String nombreProducto, Usuario usuario) {
        saldoActual -= cantidadARestar;
        gastosPorCategoria[categoriaSeleccionada - 1] += cantidadARestar;
        totalGastado += cantidadARestar;
        productosPorCategoria[categoriaSeleccionada - 1].add(nombreProducto);
        String categoria = categorias[categoriaSeleccionada - 1];
        System.out.println("Gasto registrado en la categoría: " + categoria);

        DatosGastos.guardarGastoEnCSV(nombreProducto, cantidadARestar, categoria, usuario.getCorreo());
    }
}
