import java.util.ArrayList;

class Menu {

    private String[] categorias;
    private ArrayList<String>[] productosPorCategoria;

    public Menu(String[] categorias, ArrayList<String>[] productosPorCategoria) {
        this.categorias = categorias;
        this.productosPorCategoria = productosPorCategoria;
    }

    public void mostrarMenu(double saldoActual) {
        System.out.println("Fondos totales: $" + saldoActual);
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Añadir dinero a los fondos totales");
        System.out.println("2. Registrar un gasto");
        System.out.println("3. Revisar gastos por categoría");
        System.out.println("4. Salir");
    }

}
