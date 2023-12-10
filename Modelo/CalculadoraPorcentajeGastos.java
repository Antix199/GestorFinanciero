package Modelo;

import java.util.List;

public class CalculadoraPorcentajeGastos {



    public static double calcularTotalGastado(List<Gasto> gastos) {
        double total = 0;
        for (Gasto gasto : gastos) {
            total += gasto.getCantidad();
        }
        return total;
    }

    public static double calcularTotalGastadoPorCategoria(List<Gasto> gastos, String categoria) {
        double totalPorCategoria = 0;
        for (Gasto gasto : gastos) {
            if (gasto.getCategoria().equals(categoria)) {
                totalPorCategoria += gasto.getCantidad();
            }
        }
        return totalPorCategoria;
    }

}
