package arbolesnarios;

public class nodoNario {
    int valorNodo;
    int numHijos;
    int maxHijos;
    nodoNario[] hijos;

    public nodoNario(int valorNodo, int maxHijos) {
        this.valorNodo = valorNodo;
        this.numHijos = 0;
        this.maxHijos = maxHijos;
        this.hijos = new nodoNario[maxHijos];
    }

    public void agregarHijo(nodoNario hijo) {
        if (numHijos < maxHijos) {
            hijos[numHijos] = hijo;
            numHijos++;
        } else {
            System.out.println("Ya se alcanzo el numero maximo de hijos.");
        }
    }

    public void eliminarNodo(int valor) {
        for (int i = 0; i < numHijos; i++) {
            if (hijos[i] != null && hijos[i].valorNodo == valor) {
                // Nodo encontrado
                // Desplazar los elementos a la izquierda si no es una hoja
                if (hijos[i].numHijos > 0) {
                    for (int j = i; j < numHijos - 1; j++) {
                        hijos[j] = hijos[j + 1];
                    }
                    hijos[numHijos - 1] = null;
                    System.out.println("Nodo padre ha sido eliminado y sus nodos hijo re ubicados.");
                } else {
                    // Si es una hoja, marcar como nulo
                    hijos[i] = null;
                    System.out.println("Nodo hoja ha sido eliminado.");
                }
                numHijos--;
                return;
            } else {
                hijos[i].eliminarNodo(valor);
            }
        }
    }

    public nodoNario buscarNodo(int valorBuscado) {
        if (this.valorNodo == valorBuscado) {
            return this; // Nodo encontrado
        }
        for (int i = 0; i < numHijos; i++) {
            if (hijos[i] != null && hijos[i].buscarNodo(valorBuscado) != null) {
                return hijos[i].buscarNodo(valorBuscado);
            }
        }
        return null; // Nodo no encontrado
    }
}
