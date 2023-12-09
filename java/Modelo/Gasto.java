package Modelo;


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



}
