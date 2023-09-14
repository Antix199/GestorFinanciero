import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static double montoInicial;
    private static ArrayList<Producto> listaProductos = new ArrayList<>();

    public enum Categoria {
        ELECTRONICA,
        ROPA,
        ALIMENTOS,
        GASTOSPERSONALES,
        GASTOSHOGAS,
        OTROS
    }

    public static void main(String[] args) {
        iniciarSistema();
        while (true) {
            int opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    mostrarListaProductosYMontoRestante();
                    break;
                case 3:
                    salirDelPrograma();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    public static void iniciarSistema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el monto inicial: ");
        montoInicial = scanner.nextDouble();
    }

    public static int mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menú de opciones:");
        System.out.println("1. Agregar producto");
        System.out.println("2. Mostrar lista de productos y monto restante");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    public static void agregarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();

        Categoria categoria = elegirCategoria();

        Producto producto = new Producto(nombre, precio, categoria);
        listaProductos.add(producto);
        montoInicial -= precio;
        System.out.println("Producto agregado correctamente.");
    }

    public static void mostrarListaProductosYMontoRestante() {
        System.out.println("Lista de productos:");

        for (Producto producto : listaProductos) {
            System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Categoria: " + producto.getCategoria());
        }

        System.out.println("Monto restante: " + montoInicial);
    }

    public static void salirDelPrograma() {
        System.out.println("¡Gracias por usar el sistema!");
        System.exit(0);
    }

    public static Categoria elegirCategoria() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Categorías:");
        int i = 1;
        for (Categoria categoria : Categoria.values()) {
            System.out.println(i + ". " + categoria);
            i++;
        }
        System.out.print("Seleccione una categoría: ");
        int opcion = scanner.nextInt();

        if (opcion < 1 || opcion > Categoria.values().length) {
            System.out.println("Opción inválida. Seleccionando la categoría por defecto (OTROS).");
            return Categoria.OTROS;
        }

        return Categoria.values()[opcion - 1];
    }

    static class Producto {
        private String nombre;
        private double precio;
        private Categoria categoria;

        public Producto(String nombre, double precio, Categoria categoria) {
            this.nombre = nombre;
            this.precio = precio;
            this.categoria = categoria;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public Categoria getCategoria() {
            return categoria;
        }
    }
}

