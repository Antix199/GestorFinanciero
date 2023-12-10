import Modelo.CalculadoraPorcentajeGastos;
import Modelo.Gasto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcularTotalGastadoTest {

    @Test
    void testCalcularTotalGastado() {
        // Crear una lista de gastos para probar
        List<Gasto> gastos = new ArrayList<>();
        gastos.add(new Gasto("Comida", 20.0, "Alimentación", "correo@ejemplo.com"));
        gastos.add(new Gasto("Transporte", 30.0, "Transporte", "correo@ejemplo.com"));
        gastos.add(new Gasto("Entretenimiento", 15.0, "Entretenimiento", "correo@ejemplo.com"));

        // Llamar al método a probar
        double resultado = CalculadoraPorcentajeGastos.calcularTotalGastado(gastos);

        // Verificar el resultado esperado
        double totalEsperado = 20.0 + 30.0 + 15.0;
        assertEquals(totalEsperado, resultado, 0.001);
    }
}
