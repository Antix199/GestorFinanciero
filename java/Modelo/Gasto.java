package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gasto {
    private String nombre;
    private double cantidad;
    private String categoria;
    private String correoUsuario;

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public Gasto(String nombre, double cantidad, String categoria, String correoUsuario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.correoUsuario = correoUsuario;
    }

    public static void guardarGastoEnCSV(String nombre, double cantidad, String categoria, String correoUsuario) {
        String rutaDirectorio = System.getProperty("user.dir") + File.separator + "gastos_por_usuarios";
        File directorio = new File(rutaDirectorio);
        directorio.mkdirs();

        String rutaArchivo = rutaDirectorio + File.separator + correoUsuario + "_gastos.csv";

        try {
            File archivo = new File(rutaArchivo);
            boolean existeArchivo = archivo.exists();

            FileWriter escritor = new FileWriter(rutaArchivo, true);

            if (!existeArchivo) {
                escritor.write("Nombre del Producto,Precio,Categoria\n");
            }

            escritor.write(nombre + "," + cantidad + "," + categoria + "\n");

            escritor.close();
            System.out.println("Modelo.Gasto registrado en el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Gasto> cargarGastos(String correoUsuario) {
        List<Gasto> gastos = new ArrayList<>();

        String rutaArchivo = System.getProperty("user.dir") + File.separator + "gastos_por_usuarios"
                + File.separator + correoUsuario + "_gastos.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Gasto gasto = new Gasto(partes[0], Double.parseDouble(partes[1]), partes[2], correoUsuario);
                    gastos.add(gasto);
                } else {
                    System.out.println("Error en el formato de línea del archivo CSV.");
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return gastos;
    }


}
