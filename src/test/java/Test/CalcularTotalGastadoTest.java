package Test;
import utils.CalculadoraPorcentajeGastos;
import modelo.Gasto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcularTotalGastadoTest {

    @Test
    void testCalcularTotalGastado() {

        List<Gasto> gastos = new ArrayList<>();
        gastos.add(new Gasto("Comida", 20.0, "Alimentación", "correo@ejemplo.com"));
        gastos.add(new Gasto("Transporte", 30.0, "Transporte", "correo@ejemplo.com"));
        gastos.add(new Gasto("Entretenimiento", 15.0, "Entretenimiento", "correo@ejemplo.com"));


        double resultado = CalculadoraPorcentajeGastos.calcularTotalGastado(gastos);

        double totalEsperado = 20.0 + 30.0 + 15.0;
        assertEquals(totalEsperado, resultado, 0.001);
    }
}