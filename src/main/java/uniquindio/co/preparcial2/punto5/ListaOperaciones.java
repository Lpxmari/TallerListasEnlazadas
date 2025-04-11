package uniquindio.co.preparcial2.punto5;

public class ListaOperaciones {

    public static void insertarDespuesDeSuma(Nodo cabeza) {
        insertarRecursivo(cabeza, new int[]{0});
    }

    // Método recursivo que hace todo en un solo recorrido
    private static void insertarRecursivo(Nodo actual, int[] sumaTotal) {
        if (actual == null) return;

        // calcular la suma
        sumaTotal[0] += actual.valor;

        insertarRecursivo(actual.siguiente, sumaTotal);

        // insertar después si cumple condición
        if (actual.valor == sumaTotal[0]) {
            Nodo nuevo = new Nodo(2);
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }

    public static void imprimirLista(Nodo cabeza) {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.valor + " -> ");
            actual = actual.siguiente;
        }
        System.out.println(".");
    }

    public static void main(String[] args) {
        Nodo cabeza = new Nodo(1);
        cabeza.siguiente = new Nodo(2);
        cabeza.siguiente.siguiente = new Nodo(3);
        cabeza.siguiente.siguiente.siguiente = new Nodo(2);
        cabeza.siguiente.siguiente.siguiente.siguiente = new Nodo(-6);

        System.out.println("Antes:");
        imprimirLista(cabeza);

        insertarDespuesDeSuma(cabeza);

        System.out.println("Después:");
        imprimirLista(cabeza);
    }
}

