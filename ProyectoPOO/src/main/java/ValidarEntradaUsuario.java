import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidarEntradaUsuario {
    private Scanner scanner = new Scanner(System.in);

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

    public int validarInt() {
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
}
