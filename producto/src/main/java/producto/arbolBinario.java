package producto;

public class arbolBinario {
    private Nodo raiz;

    public arbolBinario() {

    }

    public void insertar(Producto producto) {
        Nodo nuevoNodo = new Nodo(producto);
        Nodo actual = raiz;
        Nodo anterior = null;

        while (actual != null) {
            anterior = actual;
            if (producto.getNombre().compareTo(actual.getProducto().getNombre()) < 0) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }

        // En este punto, actual es nulo, lo que significa que hemos llegado a la
        // posición donde debe insertarse el nuevo nodo
        if (anterior == null) {
            raiz = nuevoNodo;
        } else if (producto.getNombre().compareTo(anterior.getProducto().getNombre()) < 0) {
            anterior.izquierdo = nuevoNodo;
        } else {
            anterior.derecho = nuevoNodo;
        }
    }

    public void recorrerEnOrden() {
        Nodo nodoActual = raiz;
        Nodo nodoAnterior = null;

        while (nodoActual != null) {

            if (nodoActual.izquierdo == null) {
                System.out.println(nodoActual.getProducto());
                nodoActual = nodoActual.derecho;
            } else {
                nodoAnterior = nodoActual.izquierdo;
                while (nodoAnterior.derecho != null && nodoAnterior.derecho != nodoActual) {
                    nodoAnterior = nodoAnterior.derecho;
                }
                if (nodoAnterior.derecho == nodoActual) {
                    nodoAnterior.derecho = null;
                    System.out.println(nodoActual.getProducto());
                    nodoActual = nodoActual.derecho;
                } else {
                    nodoAnterior.derecho = nodoActual;
                    nodoActual = nodoActual.izquierdo;
                }
            }
        }
    }

    public Producto buscar(String nombre) {
        Nodo nodoActual = raiz;
    
        while (nodoActual != null) {
            int comparacion = nombre.compareTo(nodoActual.getProducto().getNombre());
    
            if (comparacion < 0) {
                nodoActual = nodoActual.izquierdo;
            } else if (comparacion > 0) {
                nodoActual = nodoActual.derecho;
            } else {
                // Se encontró el producto, devolverlo
                System.out.println("El producto " + nombre + " se encontró.");
                System.out.println("Número de ventas: " + nodoActual.getProducto().getNumeroVentas());
                return nodoActual.getProducto();
            }
        }
    
        // Si se llega aquí, el producto no se encontró
        System.out.println("El producto " + nombre + " no se encontró.");
        return null;
    }
}
