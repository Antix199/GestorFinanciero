import java.util.Scanner;

public class Finanzas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double saldoActual = 0;
        int numCategorias = 5; // Puedes ajustar el número de categorías según tus necesidades.
        double[] gastosPorCategoria = new double[numCategorias];
        double totalGastado = 0;
        String[] categorias = new String[numCategorias];

        // Inicializa las categorías
        categorias[0] = "Alimentación";
        categorias[1] = "Transporte";
        categorias[2] = "Gastos personales";
        categorias[3] = "Educación";
        categorias[4] = "Otras";

        while (true) {
            System.out.println("Fondos totales $"+ saldoActual);
            System.out.println("¿Qué deseas hacer?");
            System.out.println("1. Añadir dinero");
            System.out.println("2. Restar dinero");
            System.out.println("3. Revisar gastos por categoría");
            System.out.println("4. Salir");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa la cantidad a añadir: $");
                    double cantidadAAnadir = scanner.nextDouble();
                    saldoActual += cantidadAAnadir;
                    break;
                case 2:
                    System.out.print("Ingresa la cantidad a restar: $");
                    double cantidadARestar = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.println("Categorías disponibles:");

                    for (int i = 0; i < numCategorias; i++) {
                        System.out.println((i + 1) + ". " + categorias[i]);
                    }

                    System.out.print("Selecciona la categoría en la que gastaste: ");
                    int categoriaSeleccionada = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    if (categoriaSeleccionada >= 1 && categoriaSeleccionada <= numCategorias) {
                        saldoActual -= cantidadARestar;
                        gastosPorCategoria[categoriaSeleccionada - 1] += cantidadARestar;
                        totalGastado += cantidadARestar;
                        System.out.println("Gasto registrado en la categoría: " + categorias[categoriaSeleccionada - 1]);
                    } else {
                        System.out.println("Categoría no válida.");
                    }
                    break;
                case 3:
                    System.out.println("Porcentaje gastado por categoría:");

                    for (int i = 0; i < numCategorias; i++) {
                        double porcentaje = (gastosPorCategoria[i] / totalGastado) * 100;
                        System.out.println(categorias[i] + ": $" + gastosPorCategoria[i] + " (" + porcentaje + "% del total gastado)");
                    }
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
}
