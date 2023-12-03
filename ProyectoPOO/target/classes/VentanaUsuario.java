import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaUsuario extends JFrame {

    private JTextField campoCorreo;
    private JPasswordField campoContrasena;

    public VentanaUsuario() {
        setTitle("Inicio de Sesión");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel etiquetaCorreo = new JLabel("Correo electrónico:");
        campoCorreo = new JTextField();

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField();

        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = campoCorreo.getText();
                char[] contrasenaChars = campoContrasena.getPassword();
                String contrasena = new String(contrasenaChars);

                Usuario usuario = Usuario.iniciarSesion(correo, contrasena);

                if (usuario != null) {
                    // Lógica después de iniciar sesión (puedes adaptarla según tus necesidades)
                    System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombre() + "!");
                } else {
                    // Manejo de error de inicio de sesión (puedes adaptarlo según tus necesidades)
                    System.out.println("Error en el inicio de sesión. Verifica tus credenciales.");
                }
            }
        });

        JButton botonCrearCuenta = new JButton("Crear Cuenta");
        botonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCuenta();
            }
        });

        JButton botonSalir = new JButton("Salir");
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(etiquetaCorreo);
        panel.add(campoCorreo);
        panel.add(etiquetaContrasena);
        panel.add(campoContrasena);
        panel.add(botonIniciarSesion);
        panel.add(botonCrearCuenta);
        panel.add(new JLabel()); // Espacio en blanco para alinear con el botón
        panel.add(botonSalir);

        add(panel);
    }

    private void crearCuenta() {
        // Implementación del método crearCuenta (puedes copiar la implementación actual)
        // ...
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaUsuario ventana = new VentanaUsuario();
                ventana.setVisible(true);
            }
        });
    }
}
