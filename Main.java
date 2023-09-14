import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static double saldoActual = 0;
    private static int numCategorias = 5;
    private static double[] gastosPorCategoria = new double[numCategorias];
    private static double totalGastado = 0;
    private static ArrayList<String>[] productosPorCategoria = new ArrayList[numCategorias];
    private static String[] categorias = new String[numCategorias];

    public static void main(String[] args) {
        inicializarCategorias();
        ejecutarMenu();
    }

    private static void inicializarCategorias() {
        categorias[0] = "Alimentación";
        categorias[1] = "Transporte";
        categorias[2] = "Entretenimiento";
        categorias[3] = "Educación";
        categorias[4] = "Otras";

        for (int i = 0; i < numCategorias; i++) {
            productosPorCategoria[i] = new ArrayList<>();
        }
    }

    private static void ejecutarMenu() {
        while (true) {
            mostrarMenu();
            int opcion = obtenerEntradaUsuario();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    anadirDinero();
                    break;
                case 2:
                    restarDinero();
                    break;
                case 3:
                    mostrarGastosPorCategoria();
                    break;
                case 4:
                    salir();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Fondos totales: $" + saldoActual);
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Añadir dinero a los fondos totales");
        System.out.println("2. Registrar compra");
        System.out.println("3. Revisar gastos por categoría");
        System.out.println("4. Salir");
    }

    private static void anadirDinero() {
        System.out.print("Ingresa la cantidad a añadir: $");
        double cantidadAAnadir = obtenerEntradaUsuario();
        saldoActual += cantidadAAnadir;
    }

    private static void restarDinero() {
        System.out.print("Ingresa la cantidad a restar: $");
        double cantidadARestar = obtenerEntradaUsuario();
        System.out.println("Categorías disponibles:");

        for (int i = 0; i < numCategorias; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        System.out.print("Selecciona la categoría en la que gastaste: ");
        int categoriaSeleccionada = obtenerEntradaUsuario();
        if (categoriaSeleccionada >= 1 && categoriaSeleccionada <= numCategorias) {
            String nombreProducto = obtenerNombreProducto();
            registrarGasto(cantidadARestar, categoriaSeleccionada, nombreProducto);
        } else {
            System.out.println("Categoría no válida.");
        }
    }


    private static String obtenerNombreProducto() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Ingresa el nombre del producto: ");
        String nombre = scanner1.next();
        return nombre;
    }

    private static void registrarGasto(double cantidadARestar, int categoriaSeleccionada, String nombreProducto) {
        saldoActual -= cantidadARestar;
        gastosPorCategoria[categoriaSeleccionada - 1] += cantidadARestar;
        totalGastado += cantidadARestar;
        productosPorCategoria[categoriaSeleccionada - 1].add(nombreProducto);
    }



    private static void mostrarGastosPorCategoria() {
        System.out.println("Porcentaje gastado por categoría:");

        for (int i = 0; i < numCategorias; i++) {
            double porcentaje = (gastosPorCategoria[i] / totalGastado) * 100;
            System.out.println(categorias[i] +": $" + gastosPorCategoria[i] + " (" + porcentaje + "% del total gastado)");
            mostrarProductosEnCategoria(i);
        }
    }

    private static void mostrarProductosEnCategoria(int categoriaIndex) {
        System.out.println("Productos en esta categoría:");

        for (String producto : productosPorCategoria[categoriaIndex]) {
            System.out.println("- " + producto);
        }
    }

    private static void salir() {
        System.out.println("¡Hasta luego!");
        scanner.close();
        System.exit(0);
    }

    private static int obtenerEntradaUsuario() {
        return scanner.nextInt();
    }
}