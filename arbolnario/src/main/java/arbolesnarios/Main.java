package arbolesnarios;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("ARBOLES N-ARIOS");
        System.out.print("\nIntroduce el valor de la raíz: ");
        int valorRaiz = input.nextInt();
        System.out.print("Introduce el número máximo de hijos: ");
        int maxHijos = input.nextInt();
        arbolNario AN = new arbolNario(valorRaiz, maxHijos);
        System.out.println("Árbol creado.");
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar nodo.");
            System.out.println("2. Eliminar nodo.");
            System.out.println("3. Buscar nodo.");
            System.out.println("4. Salir.");
            System.out.println("5. Recorrer son simbolos.");
            System.out.println("6. Salir.");
            System.out.print("Selecciona una opción: ");
            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el valor del nuevo nodo: ");
                    int valorHijo = input.nextInt();
                    //System.out.print("Introduce el número máximo de hijos para el nuevo nodo: ");
                    //int maxHijosNuevo = input.nextInt();
                    nodoNario nuevoNodo = new nodoNario(valorHijo, maxHijos);
                    AN.raiz.agregarHijo(nuevoNodo);
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
                    System.out.println("Recorrer en preorden.");
                    AN.recorrerEnPreOrden(AN.raiz);
                    break;

                case 5:
                    System.out.println("Recorrer son simbolos.");
                    System.out.print("Introduzca el prefijo: ");
                    String prefijo = input.nextLine();
                    AN.recorrerConSimbolos(AN.raiz, prefijo);
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    input.close();
                    return;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

}