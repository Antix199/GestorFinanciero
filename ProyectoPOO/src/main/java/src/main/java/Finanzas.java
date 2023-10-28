import java.util.Scanner;

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
                    calculadora.mostrarGastosPorCategoria();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
}
