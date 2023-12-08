package Modelo;

import Datos.Saldo;
import Datos.DatosGastos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private String[] categorias;
    private ArrayList<String>[] productosPorCategoria;

    private static ValidarEntradaUsuario validador = new ValidarEntradaUsuario();

    public Menu(String[] categorias, ArrayList<String>[] productosPorCategoria) {
        this.categorias = categorias;
        this.productosPorCategoria = productosPorCategoria;
    }


    public void mostrarMenuUsuario(double saldoActual) {
        System.out.println("Fondos totales: $" + saldoActual);
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Añadir dinero a los fondos totales");
        System.out.println("2. Registrar un gasto");
        System.out.println("3. Revisar gastos por categoría");
        System.out.println("4. Salir");
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("Bienvenido al Sistema de Usuarios");
        System.out.println("Opciones:");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Crear Cuenta");
        System.out.println("3. Salir");
    }

    public static void ejecutarMenuUsuario(Usuario usuario, Menu menu, Finanzas finanzas) {
        while (true) {
            menu.mostrarMenuUsuario(finanzas.getSaldoActual());
            int opcion = validador.validarInt();
            switch (opcion) {
                case 1:
                    finanzas.anadirDinero(usuario);
                    break;
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    finanzas.restarDinero(usuario, scanner);
                    break;
                case 3:
                    List<Gasto> gastos = DatosGastos.cargarGastos(usuario.getCorreo());
                    Finanzas.mostrarGastosPorCategoria(gastos);
                    CalculadoraPorcentajeGastos.mostrarPorcentajeGastos(gastos);
                    break;
                case 4:
                    Saldo.guardarSaldolCSV(usuario);
                    cerrarSistema();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }

    public static void cerrarSistema() {
        System.out.println("¡Hasta luego!");
        scanner.close();
        System.exit(0);
    }

}