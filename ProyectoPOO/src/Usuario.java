import java.io.*;
import java.util.*;

public class Usuario {

    private static Scanner scanner = new Scanner(System.in);
    private static String rutaUsuarios = System.getProperty("user.dir") + File.separator + "usuarios1.csv";

    private String nombre;
    private String correo;
    private String contrasena;

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }


    public static Usuario iniciarSesion() {
        System.out.print("Ingresa tu correo electrónico: ");
        String correo = scanner.nextLine();

        List<Usuario> usuarios = cargarUsuarios();
        Optional<Usuario> usuarioOptional = usuarios.stream()
                .filter(usuario -> usuario.correo.equals(correo))
                .findFirst();

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            System.out.print("Ingresa tu contraseña: ");
            String contrasena = scanner.nextLine();

            if (usuario.contrasena.equals(contrasena)) {
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuario.nombre + "!");
                return usuario;
            } else {
                System.out.println("Contraseña incorrecta. Vuelve a intentarlo.");
                return null;
            }
        } else {
            System.out.println("El correo electrónico no está registrado. Crea una cuenta.");
            return null;
        }
    }
    public static boolean correoExiste(String correo) {
        List<Usuario> usuarios = cargarUsuarios();

        return usuarios.stream()
                .anyMatch(usuario -> usuario.correo.equals(correo));
    }

    public static void guardarUsuario(Usuario usuario) {
        if (correoExiste(usuario.correo)) {
            System.out.println("Correo ya en uso. Usa otro.");
            return;
        }

        try {
            FileWriter archivo = new FileWriter(rutaUsuarios, true);
            PrintWriter escribir = new PrintWriter(archivo);

            escribir.println(usuario.nombre + "," + usuario.correo + "," + usuario.contrasena);

            archivo.close();
            System.out.println("Cuenta creada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            File archivoCSV = new File(rutaUsuarios);
            if (!archivoCSV.exists()) {
                return usuarios;
            }

            BufferedReader reader = new BufferedReader(new FileReader(archivoCSV));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Usuario usuario = new Usuario(partes[0], partes[1], partes[2]);
                    usuarios.add(usuario);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Getters para obtener información del usuario
    public String getNombre() {
        return nombre;
    }


    public String getCorreo() {
        return correo;
    }



    public static boolean validarFormatoCorreo(String correo) {
        String regex = "^[\\w.-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        return correo.matches(regex);
    }

}