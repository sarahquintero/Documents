package producto;

public class Producto {
    private String nombre;
    private int numeroVentas;

    public Producto(String nombre, int numeroVentas) {
        this.nombre = nombre;
        this.numeroVentas = numeroVentas;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroVentas() {
        return numeroVentas;
    }

    public void setNumeroVentas(int numeroVentas) {
        this.numeroVentas = numeroVentas;
    }
}
