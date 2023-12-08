package Datos;

import Modelo.Gasto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatosGastos {

    public static void guardarGastoEnCSV(String nombre, double cantidad, String categoria, String correoUsuario) {
        String rutaArchivo = obtenerRutaArchivo(correoUsuario);

        try {
            FileWriter escritor = new FileWriter(rutaArchivo, true);

            if (!archivoExiste(rutaArchivo)) {
                escribirEncabezado(escritor);
            }

            escribirGasto(escritor, nombre, cantidad, categoria);

            escritor.close();
            System.out.println("Gasto registrado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String obtenerRutaArchivo(String correoUsuario) {
        String rutaDirectorio = System.getProperty("user.dir") + File.separator + "gastos_por_usuarios";
        File directorio = new File(rutaDirectorio);
        directorio.mkdirs();

        return rutaDirectorio + File.separator + correoUsuario + "_gastos.csv";
    }

    private static boolean archivoExiste(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        return archivo.exists();
    }

    private static void escribirEncabezado(FileWriter escritor) throws IOException {
        escritor.write("Nombre del Producto,Precio,Categoria\n");
    }

    private static void escribirGasto(FileWriter escritor, String nombre, double cantidad, String categoria) throws IOException {
        escritor.write(nombre + "," + cantidad + "," + categoria + "\n");
    }

    public static List<Gasto> cargarGastos(String correoUsuario) {
        List<Gasto> gastos = new ArrayList<>();
        String rutaArchivo = obtenerRutaArchivo(correoUsuario);

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            procesarArchivo(reader, gastos, correoUsuario);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gastos;
    }

    private static void procesarArchivo(BufferedReader reader, List<Gasto> gastos, String correoUsuario) throws IOException {
        String linea;
        boolean primeraLinea = true;

        while ((linea = reader.readLine()) != null) {
            if (primeraLinea) {
                primeraLinea = false;
                continue;
            }

            procesarLinea(linea, gastos, correoUsuario);
        }
    }

    private static void procesarLinea(String linea, List<Gasto> gastos, String correoUsuario) {
        String[] partes = linea.split(",");
        if (partes.length == 3) {
            Gasto gasto = new Gasto(partes[0], Double.parseDouble(partes[1]), partes[2], correoUsuario);
            gastos.add(gasto);
        } else {
            System.out.println("Error en el formato de línea del archivo CSV.");
        }
    }


}
