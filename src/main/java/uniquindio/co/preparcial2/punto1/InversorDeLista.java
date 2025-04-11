package uniquindio.co.preparcial2.punto1;


public class InversorDeLista {

    public static <T> ListaSimple<T> invertirLista(ListaSimple<T> listaOriginal) {
        Pila<T> pila = new Pila<>();
        Nodo<T> actual = listaOriginal.cabeza;

        // Paso 1: apilar todos los elementos
        while (actual != null) {
            pila.apilar(actual.dato);
            actual = actual.siguiente;
        }

        // Paso 2: construir nueva lista desde la pila
        ListaSimple<T> listaInvertida = new ListaSimple<>();
        while (!pila.estaVacia()) {
            listaInvertida.agregar(pila.desapilar());
        }

        return listaInvertida;
    }

    public static void main(String[] args) {
        ListaSimple<Integer> lista = new ListaSimple<>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);

        System.out.print("Lista original: ");
        lista.imprimir();

        ListaSimple<Integer> invertida = invertirLista(lista);
        System.out.print("Lista invertida: ");
        invertida.imprimir();
    }
}