import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class gestorGastos {
    public static void guardarGastoEnCSV(String nombre, double cantidad, String categoria, String correoUsuario) {
        String rutaDirectorio = System.getProperty("user.dir") + File.separator + "gastos_por_usuarios";
        File directorio = new File(rutaDirectorio);
        directorio.mkdirs(); // Crear el directorio si no existe


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
            System.out.println("Gasto registrado en el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
