package Modelo;

import java.util.List;

public class CalculadoraPorcentajeGastos {

    public static void mostrarPorcentajeGastos(List<Gasto> gastos) {
        double totalGastado = calcularTotalGastado(gastos);

        if (totalGastado > 0) {
            for (String categoria : Categorias.getCategorias()) {
                double totalGastadoPorCategoria = calcularTotalGastadoPorCategoria(gastos, categoria);
                double porcentaje = (totalGastadoPorCategoria / totalGastado) * 100;

                System.out.printf("Porcentaje gastado en la categoría %s: %.2f%%\n", categoria, porcentaje);
            }
        } else {
            System.out.println("No hay gastos registrados para calcular el porcentaje.");
        }
    }

    private static double calcularTotalGastado(List<Gasto> gastos) {
        double total = 0;
        for (Gasto gasto : gastos) {
            total += gasto.getCantidad();
        }
        return total;
    }

    private static double calcularTotalGastadoPorCategoria(List<Gasto> gastos, String categoria) {
        double totalPorCategoria = 0;
        for (Gasto gasto : gastos) {
            if (gasto.getCategoria().equals(categoria)) {
                totalPorCategoria += gasto.getCantidad();
            }
        }
        return totalPorCategoria;
    }
}
