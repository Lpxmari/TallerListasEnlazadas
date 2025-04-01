package uniquindio.co.taller.listaCircularDoble;

class NodoDoble<E extends Comparable<E>> {
    E elemento;
    NodoDoble<E> siguiente;
    NodoDoble<E> anterior;

    public NodoDoble(E elemento) {
        this.elemento = elemento;
        this.siguiente = this.anterior = null;
    }
}

public class ListaDoblementeEnlazadaCircular<E extends Comparable<E>> {
    private NodoDoble<E> cabeza;
    private int tamanio;

    public ListaDoblementeEnlazadaCircular() {
        cabeza = null;
        tamanio = 0;
    }

    public void insertarInicio(E dato) {
        NodoDoble<E> nuevoNodo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            NodoDoble<E> ultimo = cabeza.anterior;
            nuevoNodo.siguiente = cabeza;
            nuevoNodo.anterior = ultimo;
            cabeza.anterior = nuevoNodo;
            ultimo.siguiente = nuevoNodo;
            cabeza = nuevoNodo;
        }
        tamanio++;
    }

    public void insertarFinal(E dato) {
        if (cabeza == null) {
            insertarInicio(dato);
            return;
        }
        NodoDoble<E> nuevoNodo = new NodoDoble<>(dato);
        NodoDoble<E> ultimo = cabeza.anterior;
        nuevoNodo.siguiente = cabeza;
        nuevoNodo.anterior = ultimo;
        ultimo.siguiente = nuevoNodo;
        cabeza.anterior = nuevoNodo;
        tamanio++;
    }

    public void recorrerHorario() {
        if (cabeza == null) return;
        NodoDoble<E> temp = cabeza;
        do {
            System.out.print(temp.elemento + " <-> ");
            temp = temp.siguiente;
        } while (temp != cabeza);
        System.out.println("(inicio)");
    }

    public void recorrerAntihorario() {
        if (cabeza == null) return;
        NodoDoble<E> temp = cabeza.anterior;
        do {
            System.out.print(temp.elemento + " <-> ");
            temp = temp.anterior;
        } while (temp != cabeza.anterior);
        System.out.println("(inicio)");
    }

    public void eliminar(E dato) {
        if (cabeza == null) return;
        NodoDoble<E> temp = cabeza;
        do {
            if (temp.elemento.equals(dato)) {
                if (temp == cabeza && tamanio == 1) {
                    cabeza = null;
                } else {
                    temp.anterior.siguiente = temp.siguiente;
                    temp.siguiente.anterior = temp.anterior;
                    if (temp == cabeza) cabeza = temp.siguiente;
                }
                tamanio--;
                return;
            }
            temp = temp.siguiente;
        } while (temp != cabeza);
    }

    public boolean buscarSublista(E[] sublista) {
        if (cabeza == null) return false;
        NodoDoble<E> temp = cabeza;
        do {
            NodoDoble<E> actual = temp;
            boolean encontrada = true;
            for (E elemento : sublista) {
                if (actual == null || !actual.elemento.equals(elemento)) {
                    encontrada = false;
                    break;
                }
                actual = actual.siguiente;
            }
            if (encontrada) return true;
            temp = temp.siguiente;
        } while (temp != cabeza);
        return false;
    }

    public void rotarIzquierda(int n) {
        if (cabeza == null || tamanio < 2) return;
        for (int i = 0; i < n; i++) cabeza = cabeza.siguiente;
    }

    public void rotarDerecha(int n) {
        if (cabeza == null || tamanio < 2) return;
        for (int i = 0; i < n; i++) cabeza = cabeza.anterior;
    }
}

