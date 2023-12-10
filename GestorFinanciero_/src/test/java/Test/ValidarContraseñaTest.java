package Test;

import utils.ValidarEntradaUsuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidarContraseñaTest {

    @Test
    void testValidarContrasena() {
        assertFalse(ValidarEntradaUsuario.validarContrasena("1234"));
        assertTrue(ValidarEntradaUsuario.validarContrasena("12345"));
        assertFalse(ValidarEntradaUsuario.validarContrasena(" 123456"));
        assertFalse(ValidarEntradaUsuario.validarContrasena(""));
        assertFalse(ValidarEntradaUsuario.validarContrasena("12 34"));
    }
}