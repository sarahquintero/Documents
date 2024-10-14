package arbolesnarios;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("ARBOLES N-ARIOS");
        System.out.print("Introduce el valor de la raíz: ");
        int valorRaiz = input.nextInt();
        System.out.print("Introduce el número máximo de hijos: ");
        int maxHijos = input.nextInt();
        arbolNario AN = new arbolNario(valorRaiz, maxHijos);
        System.out.println("Árbol creado.");

        System.out.println("\nMenu:");
        System.out.println("1. Agregar hijo");
        System.out.println("2. Recorrer en preorden");
        System.out.println("3. Recorrer con símbolos");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");
        int opcion = input.nextInt();

        switch (opcion) {
            case 1:
                System.out.print("Introduce el valor del nuevo nodo: ");
                int valorHijo = input.nextInt();

                System.out.print("Introduce el número máximo de hijos para el nuevo nodo: ");
                int maxHijosNuevo = input.nextInt();
                nodoNario nuevoNodo = new nodoNario(valorHijo, maxHijosNuevo);

                if (AN.raiz.numHijos > 0) {
                    AN.raiz.hijos[0].agregarHijo(nuevoNodo);
                    System.out.println("Nodo agregado como hijo del primer hijo de la raíz.");
                } else {
                    // Si no hay hijos, se puede agregar a la raíz o manejarlo de otra forma
                    AN.raiz.agregarHijo(nuevoNodo);
                    System.out.println("No había hijos, se agregó a la raíz.");
                }
                AN.recorrerConSimbolos(AN.raiz, ""); // Mostrar el árbol después de agregar
                break;

            case 2:
                System.out.print("Introduce el valor del nodo a eliminar: ");
                int valorEliminar = input.nextInt();
                AN.raiz.eliminarNodo(valorEliminar);
                AN.recorrerConSimbolos(AN.raiz, "");
                break;

            case 3:
                System.out.print("Introduce el valor del nodo a buscar: ");
                int valorBuscar = input.nextInt();
                AN.raiz.buscarNodo(valorBuscar);
                break;

            case 4:
                System.out.println("Saliendo...");
                input.close();
                return;

            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }
}