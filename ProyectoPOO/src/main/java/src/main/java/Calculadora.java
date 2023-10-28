import java.util.ArrayList;
import java.util.Scanner;

class Calculadora{

    protected static double saldoActual = 0;
    private static int numCategorias = 5;
    private static double[] gastosPorCategoria = new double[numCategorias];
    private static double totalGastado = 0;
    private static ArrayList<String>[] productosPorCategoria = GestorCategorias.getProductosPorCategoria();
    private static String[] categorias = GestorCategorias.getCategorias();
    private static ValidarEntradaUsuario validador = new ValidarEntradaUsuario();


    protected void anadirDinero() {
        System.out.print("Ingresa la cantidad a añadir: $");
        double cantidadAAnadir = validador.validarInt();
        if (cantidadAAnadir > 0){
            saldoActual += cantidadAAnadir;
        }else{
            System.out.println("No puedes añadir una cantidad negativa o nula");
        }

    }

    public double getSaldoActual() {
        return saldoActual;
    }

    private String obtenerNombreProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el nombre del producto: ");
        return scanner.nextLine();
    }

    public void mostrarGastosPorCategoria() {
        for (int i = 0; i < numCategorias; i++) {
            System.out.println("Categoría: " + categorias[i]);
            System.out.println("Gasto total: $" + gastosPorCategoria[i]);
            System.out.println("Productos:");
            for (String producto : productosPorCategoria[i]) {
                System.out.println("- " + producto);
            }
            System.out.println("--------");
        }
        System.out.println("Gasto total en todas las categorías: $" + totalGastado);
    }



    private String obtenerNombreProducto(Scanner scanner) {
        System.out.print("Ingresa el nombre del producto: ");
        return scanner.nextLine();
    }

    public void restarDinero(Usuario usuario, Scanner scanner) {
        System.out.print("Ingresa la cantidad a restar: $");
        double cantidadARestar = validador.validarInt();
        if (cantidadARestar > 0){
            System.out.println("Categorías disponibles:");

            for (int i = 0; i < numCategorias; i++) {
                System.out.println((i + 1) + ". " + categorias[i]);
            }

            System.out.print("Selecciona la categoría en la que gastaste: ");
            int categoriaSeleccionada = (int) validador.validarInt();
            if (categoriaSeleccionada >= 1 && categoriaSeleccionada <= numCategorias) {
                String nombreProducto = obtenerNombreProducto(scanner);
                registrarGasto(cantidadARestar, categoriaSeleccionada, nombreProducto, usuario);
            } else {
                System.out.println("Categoría no válida.");
            }
        } else {
            System.out.println("No puedes restar una cantidad negativa o nula.");
        }
    }


    private void registrarGasto(double cantidadARestar, int categoriaSeleccionada, String nombreProducto, Usuario usuario) {
        saldoActual -= cantidadARestar;
        gastosPorCategoria[categoriaSeleccionada - 1] += cantidadARestar;
        totalGastado += cantidadARestar;
        productosPorCategoria[categoriaSeleccionada - 1].add(nombreProducto);
        String categoria = categorias[categoriaSeleccionada - 1];
        System.out.println("Gasto registrado en la categoría: " + categoria);

        // Llama al método para guardar en el archivo CSV
        gestorGastos.guardarGastoEnCSV(nombreProducto, cantidadARestar, categoria, usuario.getCorreo());
    }
}