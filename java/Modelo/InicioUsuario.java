package Modelo;

import java.util.Scanner;

public class InicioUsuario {

    private static Scanner scanner = new Scanner(System.in);

    public static void iniciarSistemaDeUsuarios() {
        while (true) {
            Menu.mostrarMenuPrincipal();
            int opcion = obtenerOpcionUsuario();

            switch (opcion) {
                case 1:
                    iniciarSesionYManejarFinanzas();
                    break;
                case 2:
                    crearCuenta();
                    Menu.cerrarSistema();
                    break;
                case 3:
                    Menu.cerrarSistema();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
    private static void iniciarSesionYManejarFinanzas() {
        Usuario usuario = Usuario.iniciarSesion();
        if (usuario != null) {
            Categorias.inicializarCategorias();
            Menu menu = new Menu(Categorias.getCategorias(), Categorias.getProductosPorCategoria());
            Finanzas finanzas = new Finanzas();
            Finanzas.setSaldoActual(usuario);
            Menu.ejecutarMenuUsuario(usuario, menu, finanzas);
        }
    }

    private static int obtenerOpcionUsuario() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un número válido.");
            return obtenerOpcionUsuario();
        }
    }

    public static void crearCuenta() {
        Scanner scanner = new Scanner(System.in);

        String nombre = obtenerNombreValido(scanner);
        String correo = obtenerCorreoValido(scanner);
        String contrasena = ValidarEntradaUsuario.obtenerContrasenaValida(scanner);

        System.out.println("¿Desea continuar con la creación de la cuenta? El sistéma se cerrará de forma automática (s para confirmar)");
        String respuesta = scanner.nextLine().toLowerCase();

        if (respuesta.equals("s")) {
            Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena);
            Usuario.guardarUsuario(nuevoUsuario);
        } else {
            System.out.println("Creación de cuenta cancelada.");
        }
    }

    private static String obtenerNombreValido(Scanner scanner) {
        String nombre;

        do {
            System.out.print("Ingresa tu nombre: ");
            nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío, Por favor, inténtalo de nuevo.");
            }
        } while (nombre.isEmpty());

        return nombre;
    }

    private static String obtenerCorreoValido(Scanner scanner) {
        String correo;
        do {
            System.out.print("Ingresa tu correo electrónico: ");
            correo = scanner.nextLine().trim().toLowerCase();

            if (!ValidarEntradaUsuario.validarFormatoCorreo(correo)) {
                System.out.println("El formato del correo electrónico no es válido. Por favor, inténtalo de nuevo.");
            } else if (Usuario.correoExiste(correo)) {
                System.out.println("Correo ya en uso. Usa otro.");
            }
        } while (!ValidarEntradaUsuario.validarFormatoCorreo(correo) || Usuario.correoExiste(correo));

        return correo;
    }
}