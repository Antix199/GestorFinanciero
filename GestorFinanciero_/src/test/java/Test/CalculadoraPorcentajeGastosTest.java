package Test;

import utils.CalculadoraPorcentajeGastos;
import modelo.Gasto;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraPorcentajeGastosTest {

    @Test
    public void testCalcularTotalGastadoPorCategoria() {
       
    
        List<Gasto> listaDeGastos = new ArrayList<>();
        listaDeGastos.add(new Gasto("Gasto1", 50.0, "Comida", "correo1"));
        listaDeGastos.add(new Gasto("Gasto2", 30.0, "Transporte", "correo1"));
        listaDeGastos.add(new Gasto("Gasto3", 20.0, "Comida", "correo1"));
        listaDeGastos.add(new Gasto("Gasto4", 40.0, "Entretenimiento", "correo1"));

     
        double totalCalculado = CalculadoraPorcentajeGastos.calcularTotalGastadoPorCategoria(listaDeGastos, "Comida");

        assertEquals(70.0, totalCalculado, 0.001);
    }
}
