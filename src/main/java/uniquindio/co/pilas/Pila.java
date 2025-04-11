package uniquindio.co.pilas;

import java.util.*;

class Pila<T> {
    private LinkedList<T> elementos = new LinkedList<>();

    public void apilar(T elemento) {
        elementos.addFirst(elemento);
    }

    public T desapilar() {
        return estaVacia() ? null : elementos.removeFirst();
    }

    public T cima() {
        return estaVacia() ? null : elementos.getFirst();
    }

    public boolean estaVacia() {
        return elementos.isEmpty();
    }
}
