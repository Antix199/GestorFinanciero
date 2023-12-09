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
    private static ValidarEntradaUsuario validador = new ValidarEntradaUsuario();


    public static void anadirDinero(Usuario usuario, double monto) {
        saldoActual += monto;
    }


    public static double getSaldoActual() {
        return saldoActual;
    }

    public static void setSaldoActual(Usuario usuario) {
        saldoActual = Saldo.cargarSaldoUsuario(usuario.getCorreo());
    }

    public static void mostrarGastosPorCategoria( List<Gasto> gastos) {
        System.out.println("Gastos por Categoría:");

        Map<String, List<Gasto>> gastosPorCategoria = agruparGastosPorCategoria(gastos);

        for (Map.Entry<String, List<Gasto>> entry : gastosPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            List<Gasto> gastosEnCategoria = entry.getValue();

            System.out.println("Categoría: " + categoria);
            mostrarDetallesGastos(gastosEnCategoria);
            System.out.println();
        }
    }

    public static Map<String, List<Gasto>> agruparGastosPorCategoria(List<Gasto> gastos) {
        Map<String, List<Gasto>> gastosPorCategoria = new HashMap<>();

        for (Gasto gasto : gastos) {
            String categoria = gasto.getCategoria();
            gastosPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).add(gasto);
        }

        return gastosPorCategoria;
    }

    private static void mostrarDetallesGastos(List<Gasto> gastos) {
        for (Gasto gasto : gastos) {
            System.out.println("Nombre: " + gasto.getNombre() + ", Monto: " + gasto.getCantidad());
        }
    }


    public static void restarDinero(Usuario usuario, double cantidad, int categoria, String nombreProducto) {


        if (esCantidadValida(cantidad)) {

            if (esCategoriaValida(categoria)) {
                registrarGasto(cantidad, categoria, nombreProducto, usuario);
            } else {
                System.out.println("Categoría no válida.");
            }
        } else {
            System.out.println("No puedes restar una cantidad negativa o nula.");
        }
    }

    public static boolean esCantidadValida(double cantidad) {
        if (cantidad > 0) {
            return true;
        } else {
            System.out.println("La cantidad debe ser mayor que cero.");
            return false;
        }
    }

    public static boolean esCategoriaValida(int categoriaSeleccionada) {
        return categoriaSeleccionada >= 1 && categoriaSeleccionada <= numCategorias;
    }

    private static void registrarGasto(double cantidadARestar, int categoriaSeleccionada, String nombreProducto, Usuario usuario) {
        saldoActual -= cantidadARestar;
        gastosPorCategoria[categoriaSeleccionada - 1] += cantidadARestar;
        totalGastado += cantidadARestar;
        productosPorCategoria[categoriaSeleccionada - 1].add(nombreProducto);
        String categoria = categorias[categoriaSeleccionada - 1];
        System.out.println("Gasto registrado en la categoría: " + categoria);

        DatosGastos.guardarGastoEnCSV(nombreProducto, cantidadARestar, categoria, usuario.getCorreo());
    }
}
