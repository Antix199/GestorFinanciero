import Modelo.ValidarEntradaUsuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidarFormatoCorreoTest {

    @Test
    public void testValidarFormatoCorreo() {
        assertTrue(validarFormatoCorreo("usuario@dominio.com"));
        assertFalse(validarFormatoCorreo("user123@company.co.uk"));
        assertTrue(validarFormatoCorreo("john.doe123@example.com"));
        assertTrue(validarFormatoCorreo("1234@website.org"));
        assertFalse(validarFormatoCorreo("invalid.email@.com"));
        assertFalse(validarFormatoCorreo(".invalid@company.com"));
        assertFalse(validarFormatoCorreo("user@invalid.domain."));
        assertFalse(validarFormatoCorreo("user@.domain.com"));
        assertFalse(validarFormatoCorreo("user@domain..com"));
        assertFalse(validarFormatoCorreo("@domain.com"));
        assertFalse(validarFormatoCorreo("user@domain@company.com"));
        assertFalse(validarFormatoCorreo("user.@domain.com"));
    }

    private static boolean validarFormatoCorreo(String correo) {
        return ValidarEntradaUsuario.validarFormatoCorreo(correo);
    }
}
