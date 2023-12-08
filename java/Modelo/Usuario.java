package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;


public class Usuario {

    private String nombre;
    private String correo;
    private String contrasena;
    private static String rutaUsuarios = System.getProperty("user.dir") + File.separator + "usuarios.csv";

    public String getNombre() {
        return nombre;
    }
    public String getCorreo(){ return correo;}

    private static Scanner scanner = new Scanner(System.in);

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public static boolean correoExiste(String correo) {
        List<Usuario> usuarios = cargarUsuarios();
        return usuarios.stream().anyMatch(usuario -> usuario.correo.equals(correo));
    }



    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaUsuarios))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Usuario usuario = new Usuario(partes[0], partes[1], partes[2]);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos de usuario, por favor revise la ruta y datos del archivo.");
        }

        return usuarios;
    }

    public static void guardarUsuario(Usuario usuario) {
        if (usuario == null || usuario.nombre == null || usuario.correo == null || usuario.contrasena == null) {
            System.out.println("Error: El objeto Usuario o sus campos no pueden ser nulos.");
            return;
        }

        if (correoExiste(usuario.correo)) {
            System.out.println("Error: Correo ya en uso. Usa otro por favor.");
            return;
        }

        try (FileWriter archivo = new FileWriter(rutaUsuarios, true);
             PrintWriter escribir = new PrintWriter(archivo)) {

            escribir.println(usuario.nombre + "," + usuario.correo + "," + usuario.contrasena);
            System.out.println("¡Cuenta creada exitosamente.!");

        } catch (IOException e) {
            System.out.println("Error: El usuario no se ha podido registrar.");
        }
    }

    public static Usuario iniciarSesion() {
        System.out.print("Ingresa tu correo electrónico: ");
        String correo = scanner.nextLine().trim().toLowerCase();

        List<Usuario> usuarios = cargarUsuarios();
        Optional<Usuario> usuarioOptional = buscarUsuarioPorCorreo(correo);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (verificarContrasena(usuario)) {
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuario.nombre + "!");
                return usuario;
            } else {
                System.out.println("Contraseña incorrecta. Vuelve a intentarlo.");
            }
        } else {
            System.out.println("El correo electrónico no está registrado. Crea una cuenta.");
        }
        return null;
    }

    private static Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
        return cargarUsuarios().stream()
                .filter(usuario -> usuario.correo.equals(correo))
                .findFirst();
    }

    private static boolean verificarContrasena(Usuario usuario) {
        System.out.print("Ingresa tu contraseña: ");
        String contrasena = scanner.nextLine();
        return usuario.contrasena.equals(contrasena);
    }



}