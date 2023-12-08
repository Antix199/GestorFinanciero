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




    protected void anadirDinero(Usuario usuario) {
        System.out.print("Ingresa la cantidad a añadir: $");
        double cantidadAAnadir = validador.validarDouble();
        if (cantidadAAnadir > 0){
            saldoActual += cantidadAAnadir;
        }else{
            System.out.println("No puedes añadir una cantidad negativa o nula");
        }

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

    private static Map<String, List<Gasto>> agruparGastosPorCategoria(List<Gasto> gastos) {
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




    private String obtenerNombreProducto(Scanner scanner) {
        System.out.print("Ingresa el nombre del producto: ");
        return scanner.nextLine();
    }

    public void restarDinero(Usuario usuario, Scanner scanner) {
        System.out.print("Ingresa la cantidad a restar: $");
        double cantidadARestar = validador.validarDouble();

        if (esCantidadValida(cantidadARestar)) {
            mostrarCategoriasDisponibles();

            System.out.print("Selecciona la categoría en la que gastaste: ");
            int categoriaSeleccionada = validador.validarInt();

            if (esCategoriaValida(categoriaSeleccionada)) {
                String nombreProducto = obtenerNombreProducto(scanner);
                registrarGasto(cantidadARestar, categoriaSeleccionada, nombreProducto, usuario);
            } else {
                System.out.println("Categoría no válida.");
            }
        } else {
            System.out.println("No puedes restar una cantidad negativa o nula.");
        }
    }

    private boolean esCantidadValida(double cantidad) {
        if (cantidad > 0) {
            return true;
        } else {
            System.out.println("La cantidad debe ser mayor que cero.");
            return false;
        }
    }

    private void mostrarCategoriasDisponibles() {
        System.out.println("Categorías disponibles:");

        for (int i = 0; i < numCategorias; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }
    }

    private boolean esCategoriaValida(int categoriaSeleccionada) {
        return categoriaSeleccionada >= 1 && categoriaSeleccionada <= numCategorias;
    }

    private void registrarGasto(double cantidadARestar, int categoriaSeleccionada, String nombreProducto, Usuario usuario) {
        saldoActual -= cantidadARestar;
        gastosPorCategoria[categoriaSeleccionada - 1] += cantidadARestar;
        totalGastado += cantidadARestar;
        productosPorCategoria[categoriaSeleccionada - 1].add(nombreProducto);
        String categoria = categorias[categoriaSeleccionada - 1];
        System.out.println("Gasto registrado en la categoría: " + categoria);

        DatosGastos.guardarGastoEnCSV(nombreProducto, cantidadARestar, categoria, usuario.getCorreo());
    }
}
