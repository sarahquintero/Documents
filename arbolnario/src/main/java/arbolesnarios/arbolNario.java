package arbolesnarios;

public class arbolNario {

    public nodoNario raiz;

    public arbolNario(int valorRaiz, int maxHijos) {
        this.raiz = new nodoNario(valorRaiz, maxHijos);
    }

    public void recorrerEnPreOrden(nodoNario nodo) {
        if (nodo == null) {
            return;
        }
        System.out.println(nodo.valorNodo);
        for (int i = 0; i < nodo.maxHijos; i++) {
            recorrerEnPreOrden(nodo.hijos[i]);
        }
    }

    public void recorrerConSimbolos(nodoNario nodo, String prefijo) {
        if (nodo != null) {
            System.out.println(prefijo + "+--_" + nodo.valorNodo);
            for (int i = 0; i < nodo.numHijos; i++) {
                recorrerConSimbolos(nodo.hijos[i], prefijo + "|   ");
            }
        }
    }
}
