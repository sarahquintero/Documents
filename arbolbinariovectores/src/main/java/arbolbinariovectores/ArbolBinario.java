package arbolbinariovectores;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinario {

    int valor;
    int hijoIzquierdo;
    int hijoDerecho;
    List<Object> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    List<Object> crearNodo(int valor) {
        List<Object> nodo = new ArrayList<>();
        nodo.add(valor);
        nodo.add(null);
        nodo.add(null);
        return nodo;
    }

    public void agregar(int valor) {
        if (raiz == null) {
            raiz = crearNodo(valor);
        } else {
            agregarNodo(raiz, valor);
        }
    }

    void agregarNodo(List<Object> nodo, int valor) {
        int valorNodo = (int) nodo.get(0);
        if (valor < valorNodo) {
            if (nodo.get(1) == null) {
                nodo.set(1, crearNodo(valor));
            } else {
                agregarNodo((List<Object>) nodo.get(1), valor);
            }
        } else if (valor > valorNodo) {
            // Si el valor es mayor, ir al hijo derecho
            if (nodo.get(2) == null) {
                nodo.set(2, crearNodo(valor));
            } else {
                agregarNodo((List<Object>) nodo.get(2), valor);
            }
        } else {
            // Manejar duplicados (por ejemplo, no insertar)
            System.out.println("Valor duplicado: " + valor);
        }
    }

    public void recorridoInorden() {
        recorridoInorden(raiz);
        System.out.println();
    }

    public void recorridoInorden(List<Object> nodo) {
        if (nodo != null) {
            recorridoInorden((List<Object>) nodo.get(1));
            System.out.print(nodo.get(0) + " ");
            recorridoInorden((List<Object>) nodo.get(2));
        }
    }

    public void recorridoPreorden() {
        recorridoPreorden(raiz);
        System.out.println();
    }

    public void recorridoPreorden(List<Object> nodo) {
        if (nodo != null) {
            System.out.print(nodo.get(0) + " ");
            recorridoPreorden((List<Object>) nodo.get(1));
            recorridoPreorden((List<Object>) nodo.get(2));
        }
    }

    public boolean buscar(int valor) {
        return buscarNodo(raiz, valor);
    }

    private boolean buscarNodo(List<Object> nodo, int valor) {
        if (nodo == null) {
            return false; // No se encontró el valor
        }
        int valorNodo = (int) nodo.get(0);
        if (valor == valorNodo) {
            return true; // Valor encontrado
        } else if (valor < valorNodo) {
            return buscarNodo((List<Object>) nodo.get(1), valor); // Buscar en el subárbol izquierdo
        } else {
            return buscarNodo((List<Object>) nodo.get(2), valor); // Buscar en el subárbol derecho
        }
    }
}
