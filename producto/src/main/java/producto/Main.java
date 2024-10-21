package producto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        arbolBinario arbol = new arbolBinario();
        Scanner input = new Scanner(System.in);

        Producto producto1 = new Producto("Manzana", 5);
        Producto producto2 = new Producto("Banana", 60);
        Producto producto3 = new Producto("Cereza", 70);
        Producto producto4 = new Producto("Guanabana", 200);
        Producto producto5 = new Producto("Kiwi", 50);
        Producto producto6 = new Producto("Papaya", 80);
        Producto producto7 = new Producto("Sandia", 100);
        Producto producto8 = new Producto("Fresa", 30);
        Producto producto9 = new Producto("Mora", 10);
        Producto producto10 = new Producto("Coco", 20);

        arbol.insertar(producto1);
        arbol.insertar(producto2);
        arbol.insertar(producto3);
        arbol.insertar(producto4);
        arbol.insertar(producto5);
        arbol.insertar(producto6);
        arbol.insertar(producto7);
        arbol.insertar(producto8);
        arbol.insertar(producto9);
        arbol.insertar(producto10);

        int opc = 0;

        do {
            System.out.println("**Menu**");
            System.out.println(" ---BIENVENIDO AL SISTEMA---");
            System.out.println("1. Mostrar productos.");
            System.out.println("2. Buscar Producto.");
            System.out.println("3. Salir.");

            System.out.println("Seleccione la opcion que desee: ");

            opc = input.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("Productos en orden:");
                    arbol.recorrerEnOrden();
                    break;
                case 2:
                    System.out.println("Buscar Producto.");
                    System.out.print("Ingrese el nombre del producto a buscar: ");
                    input.nextLine();
                    String nombre = input.nextLine();
                    //String nombre = "Banana";
                    arbol.buscar(nombre);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opc != 3);
    }
}