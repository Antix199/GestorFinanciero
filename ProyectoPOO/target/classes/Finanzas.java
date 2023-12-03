import java.util.*;

public class Finanzas {

    private static ValidarEntradaUsuario validador = new ValidarEntradaUsuario();

    public static void main(String[] args) {

        Calculadora calculadora = new Calculadora();
        GestorCategorias gestorCategorias = new GestorCategorias();
        gestorCategorias.inicializarCategorias();
        Usuario usuario = Usuario.iniciarSesion();

        if (usuario != null) {
            Menu menu = new Menu(GestorCategorias.getCategorias(), GestorCategorias.getProductosPorCategoria());
            ejecutarMenu(usuario, menu, calculadora);
        } else {
            System.out.println("No se pudo iniciar sesión. Saliendo del programa.");
        }
    }

    public static void ejecutarMenu(Usuario usuario, Menu menu, Calculadora calculadora) {
        while (true) {
            menu.mostrarMenu(calculadora.getSaldoActual());
            int opcion = validador.validarInt();
            switch (opcion) {
                case 1:
                    calculadora.anadirDinero();
                    break;
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    calculadora.restarDinero(usuario, scanner);
                    break;
                case 3:
                    // Mostrar gastos por categoría
                    List<Gasto> gastos = Gasto.cargarGastos(usuario.getCorreo());
                    Finanzas.mostrarGastosPorCategoria(usuario, gastos);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
    public static void mostrarGastosPorCategoria(Usuario usuario, List<Gasto> gastos) {
        System.out.println("Gastos por Categoría:");

        // Crear un mapa para agrupar los gastos por categoría
        Map<String, List<Gasto>> gastosPorCategoria = new HashMap<>();

        // Agrupar los gastos por categoría
        for (Gasto gasto : gastos) {
            String categoria = gasto.getCategoria();
            gastosPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).add(gasto);
        }

        // Mostrar los gastos por categoría
        for (Map.Entry<String, List<Gasto>> entry : gastosPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            List<Gasto> gastosEnCategoria = entry.getValue();

            System.out.println("Categoría: " + categoria);
            for (Gasto gasto : gastosEnCategoria) {
                System.out.println("Nombre: " + gasto.getNombre() + ", Monto: " + gasto.getCantidad());
            }
            System.out.println();
        }
    }
}
