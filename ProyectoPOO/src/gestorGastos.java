import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class gestorGastos {

    public static void guardarGastoEnCSV(String nombre, double cantidad, String categoria, String correoUsuario) {
        String rutaArchivo = System.getProperty("user.dir") + File.separator + "gastos(" + correoUsuario + ").csv";

        File archivo = new File(rutaArchivo);

        boolean existeArchivo = archivo.exists();

        try {
            FileWriter escritor = new FileWriter(archivo, true); // Usa la variable 'archivo' en lugar de 'rutaArchivo'

            if (!existeArchivo) {
                escritor.write("Nombre del Producto,Precio,Categoria,Usuario\n");
            }

            escritor.write(nombre + "," + cantidad + "," + categoria + "," + correoUsuario + "\n");

            escritor.close();
            System.out.println("Gasto registrado en el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
