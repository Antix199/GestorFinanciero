package Datos;

import Modelo.Finanzas;
import Modelo.Usuario;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Saldo {

    private static final String rutaArchivo = System.getProperty("user.dir") + "/Usuarios/saldoActual_por_usuario.csv";

    public static void guardarSaldoCSV(Usuario usuario) {
        try {
            Path path = Paths.get(rutaArchivo);

            verificarYCrearArchivo(path);

            String lineaUsuario = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

            String nuevaLinea = construirNuevaLinea(usuario, lineaUsuario);

            Files.write(path, nuevaLinea.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void verificarYCrearArchivo(Path path) throws IOException {
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private static String construirNuevaLinea(Usuario usuario, String lineasUsuario) {
        String correoUsuario = usuario.getCorreo();
        String nuevaLinea = correoUsuario + "," + Finanzas.getSaldoActual();

        boolean usuarioEncontrado = false;

        String[] lineas = lineasUsuario.split(System.lineSeparator());
        StringBuilder nuevoContenidoUsuario = new StringBuilder();

        for (String linea : lineas) {
            String[] partes = linea.split(",");
            String correoExistente = partes[0].trim();

            if (correoExistente.equals(correoUsuario)) {
                nuevoContenidoUsuario.append(nuevaLinea).append(System.lineSeparator());
                usuarioEncontrado = true;
            } else {
                nuevoContenidoUsuario.append(linea).append(System.lineSeparator());
            }
        }

        if (!usuarioEncontrado) {
            nuevoContenidoUsuario.append(nuevaLinea).append(System.lineSeparator());
        }

        return nuevoContenidoUsuario.toString();
    }

    public static double cargarSaldoUsuario(String correoUsuario) {
        try {
            Path path = Paths.get(rutaArchivo);

            verificarYCrearArchivo(path);

            String contenido = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

            return buscarSaldoUsuario(correoUsuario, contenido);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    private static double buscarSaldoUsuario(String correoUsuario, String contenido) {
        String[] lineas = contenido.split(System.lineSeparator());
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            String correoExistente = partes[0].trim();

            if (correoExistente.equals(correoUsuario)) {
                return Double.parseDouble(partes[1].trim());
            }
        }

        return 0.0;
    }

}