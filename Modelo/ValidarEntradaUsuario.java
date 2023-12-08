package Modelo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidarEntradaUsuario {
    private static Scanner scanner = new Scanner(System.in);

    public double validarDouble() {
        while (true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                scanner.nextLine();
            }
        }
    }

    public static int validarInt() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número entero.");
                scanner.nextLine();
            }
        }
    }

    public static boolean validarFormatoCorreo(String correo) {
        String regex = "^[\\w.-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        return correo.matches(regex);
    }

    public static String obtenerContrasenaValida(Scanner scanner) {
        String contrasena;
        do {
            System.out.print("Ingresa tu contraseña, de mínimo 5 carácteres: ");
            contrasena = scanner.nextLine();
        } while (contrasena.length() < 5);

        return contrasena;
    }


}