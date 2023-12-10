import Modelo.CalculadoraPorcentajeGastos;
import Modelo.Gasto;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraPorcentajeGastosTest {

    @Test
    public void testCalcularTotalGastadoPorCategoria() {
        // Arrange (Configuración)
        List<Gasto> listaDeGastos = new ArrayList<>();
        listaDeGastos.add(new Gasto("Gasto1", 50.0, "Comida", "correo1"));
        listaDeGastos.add(new Gasto("Gasto2", 30.0, "Transporte", "correo1"));
        listaDeGastos.add(new Gasto("Gasto3", 20.0, "Comida", "correo1"));
        listaDeGastos.add(new Gasto("Gasto4", 40.0, "Entretenimiento", "correo1"));

        // Act (Acción)
        double totalCalculado = CalculadoraPorcentajeGastos.calcularTotalGastadoPorCategoria(listaDeGastos, "Comida");

        // Assert (Afirmación)
        assertEquals(70.0, totalCalculado, 0.001);
    }
}