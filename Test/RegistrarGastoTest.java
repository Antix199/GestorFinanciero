import static org.junit.Assert.*;

import Modelo.Categorias;
import Modelo.Finanzas;
import Modelo.Usuario;
import org.junit.*;

public class RegistrarGastoTest {

    @Before
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
        assertEquals(cantidadARestar, Finanzas.gastosPorCategoria[categoriaSeleccionada - 1], 0.001);
        assertEquals(cantidadARestar, Finanzas.totalGastado, 0.001);
        assertTrue(Finanzas.productosPorCategoria[categoriaSeleccionada - 1].contains(nombreProducto));
    }

    @Test
    public void testRegistrarGastoConFallo() {

        Usuario usuario = new Usuario("Nombre de usuario", "correo@ejemplo.com", "contrasena");
        Finanzas.setSaldoActual(usuario);

        double saldoInicial = Finanzas.getSaldoActual();
        int categoriaSeleccionada = 10; // Valor que debería considerarse no válido
        String nombreProducto = "Producto de prueba";
        double cantidadARestar = 50.0;

        Finanzas.registrarGasto(cantidadARestar, categoriaSeleccionada, nombreProducto, usuario);

        assertEquals(saldoInicial, Finanzas.getSaldoActual(), 0.001);

        assertEquals(0.0, Finanzas.gastosPorCategoria[categoriaSeleccionada - 1], 0.001);
        assertEquals(0.0, Finanzas.totalGastado, 0.001);
        assertFalse(Finanzas.productosPorCategoria[categoriaSeleccionada - 1].contains(nombreProducto));
    }
}
