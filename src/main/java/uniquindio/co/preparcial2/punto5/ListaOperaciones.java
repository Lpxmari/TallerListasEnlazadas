package uniquindio.co.preparcial2.punto5;

public class ListaOperaciones {

    public static void insertarDespuesDeSuma(Nodo cabeza) {
        int sumaTotal = calcularSuma(cabeza);
        insertarRecursivo(cabeza, sumaTotal);
    }

    private static int calcularSuma(Nodo actual) {
        if (actual == null) return 0;
        return actual.valor + calcularSuma(actual.siguiente);
    }

    private static void insertarRecursivo(Nodo actual, int sumaTotal) {
        if (actual == null) return;

        if (actual.valor == sumaTotal) {
            Nodo nuevo = new Nodo(2);
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;

            insertarRecursivo(nuevo.siguiente, sumaTotal);  
        } else {
            insertarRecursivo(actual.siguiente, sumaTotal);
        }
    }

    public static void imprimirLista(Nodo cabeza) {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(Math.abs(actual.valor) + " -> ");
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

