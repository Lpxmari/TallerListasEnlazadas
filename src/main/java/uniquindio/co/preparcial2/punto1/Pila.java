package uniquindio.co.preparcial2.punto1;

import java.util.LinkedList;

class Nodo<T> {
    T dato;
    Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ListaSimple<T> {
    Nodo<T> cabeza;

    void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    void imprimir() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}

class Pila<T> {
    private LinkedList<T> elementos = new LinkedList<>();

    public void apilar(T elemento) {elementos.addFirst(elemento);}

    public T desapilar() {return elementos.removeFirst();}

    public boolean estaVacia() {return elementos.isEmpty();}
}




