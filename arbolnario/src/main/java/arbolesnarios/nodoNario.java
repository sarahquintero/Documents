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

    public void agregarHijo(nodoNario nuevoHijo) {
        if (this.numHijos < this.maxHijos) {
            this.hijos[this.numHijos] = nuevoHijo;
            this.numHijos++;
        } else {
            // Buscar un hijo con espacio y agregar allí
            for (int i = 0; i < this.numHijos; i++) {
                if (this.hijos[i].numHijos < this.hijos[i].maxHijos) {
                    this.hijos[i].agregarHijo(nuevoHijo);
                    return;
                }
            }
            // Si ningún hijo tiene espacio, se puede manejar de diferentes formas:
            // 1. Ignorar el nuevo nodo
            // 2. Redimensionar el vector de hijos (no recomendado si se quiere un tamaño fijo)
            // 3. Implementar una estrategia de rebalanceo
            System.out.println("No hay espacio para el nuevo nodo");
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
            System.out.println("Nodo encontrado.");
            return this; // Nodo encontrado
        }
        for (int i = 0; i < numHijos; i++) {
            if (hijos[i] != null && hijos[i].buscarNodo(valorBuscado) != null) {
                return hijos[i].buscarNodo(valorBuscado);
            }
        }
        System.out.println("Nodo no encontrado.");
        return null; // Nodo no encontrado
    }
}
