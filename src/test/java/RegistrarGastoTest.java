import static org.junit.jupiter.api.Assertions.*;

import Modelo.Categorias;
import Modelo.Finanzas;
import Modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class RegistrarGastoTest {

    @BeforeEach
    public void setUp() {
        Categorias.inicializarCategorias();
    }

    @Test
    public void testRegistrarGastoExitoso() {

        Usuario usuario = new Usuario("Nombre de usuario", "correo@ejemplo.com", "contrasena");
        Finanzas.setSaldoActual(usuario);

        double saldoInicial = Finanzas.getSaldoActual();
        int categoriaSeleccionada = 5;
        String nombreProducto = "Producto de prueba";
        double cantidadARestar = 50.0;

        Finanzas.registrarGasto(cantidadARestar, categoriaSeleccionada, nombreProducto, usuario);

        assertEquals(saldoInicial - cantidadARestar, Finanzas.getSaldoActual(), 0.001);
        assertEquals(cantidadARestar, Finanzas.getGastosPorCategoria()[categoriaSeleccionada - 1], 0.001);
        assertEquals(cantidadARestar, Finanzas.getTotalGastado(), 0.001);

        assertTrue(Finanzas.getProductosPorCategoria()[categoriaSeleccionada - 1].contains(nombreProducto));
    }


    @Test
    public void testRegistrarGastoConFallo() {

        Usuario usuario = new Usuario("Nombre de usuario", "correo@ejemplo.com", "contrasena");
        Finanzas.setSaldoActual(usuario);

        double saldoInicial = Finanzas.getSaldoActual();
        int categoriaSeleccionada = 10;
        String nombreProducto = "Producto de prueba";
        double cantidadARestar = 50.0;

        Finanzas.registrarGasto(cantidadARestar, categoriaSeleccionada, nombreProducto, usuario);

        assertEquals(saldoInicial, Finanzas.getSaldoActual(), 0.001);

        assertEquals(0.0, Finanzas.getGastosPorCategoria()[categoriaSeleccionada - 1], 0.001);
        assertEquals(0.0, Finanzas.getTotalGastado(), 0.001);

        assertFalse(Finanzas.getProductosPorCategoria()[categoriaSeleccionada - 1].contains(nombreProducto));
    }

}