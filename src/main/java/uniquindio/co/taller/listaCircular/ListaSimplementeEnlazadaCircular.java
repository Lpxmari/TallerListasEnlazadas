package uniquindio.co.taller.listaCircular;


public class ListaSimplementeEnlazadaCircular<E extends Comparable<E>> {
    private Nodo<E> cabeza;
    private int tamanio;

    public ListaSimplementeEnlazadaCircular() {
        cabeza = null;
        tamanio = 0;
    }

    public void agregarInicio(E dato) {
        Nodo<E> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cabeza.setSiguiente(cabeza);
        } else {
            Nodo<E> temp = cabeza;
            while (temp.getSiguiente() != cabeza) {
                temp = temp.getSiguiente();
            }
            nuevoNodo.setSiguiente(cabeza);
            temp.setSiguiente(nuevoNodo);
            cabeza = nuevoNodo;
        }
        tamanio++;
    }

    public void agregarFinal(E dato) {
        Nodo<E> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cabeza.setSiguiente(cabeza);
        } else {
            Nodo<E> temp = cabeza;
            while (temp.getSiguiente() != cabeza) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(cabeza);
        }
        tamanio++;
    }

    public void eliminar(E dato) {
        if (cabeza == null) return;
        Nodo<E> actual = cabeza, previo = null;
        do {
            if (actual.getElemento().equals(dato)) {
                if (actual == cabeza) {
                    Nodo<E> temp = cabeza;
                    while (temp.getSiguiente() != cabeza) {
                        temp = temp.getSiguiente();
                    }
                    cabeza = cabeza.getSiguiente();
                    temp.setSiguiente(cabeza);
                } else {
                    previo.setSiguiente(actual.getSiguiente());
                }
                tamanio--;
                return;
            }
            previo = actual;
            actual = actual.getSiguiente();
        } while (actual != cabeza);
    }

    public boolean obtener(E dato) {
        if (cabeza == null) return false;
        Nodo<E> recorrerNodo = cabeza;
        do {
            if (recorrerNodo.getElemento().equals(dato)) {
                return true;
            }
            recorrerNodo = recorrerNodo.getSiguiente();
        } while (recorrerNodo != cabeza);
        return false;
    }

    public int localizar(E dato) {
        if (cabeza == null) return -1;
        Nodo<E> recorrerNodo = cabeza;
        int contador = 0;
        do {
            if (recorrerNodo.getElemento().equals(dato)) {
                return contador;
            }
            recorrerNodo = recorrerNodo.getSiguiente();
            contador++;
        } while (recorrerNodo != cabeza);
        return -1;
    }

    public String mostrar() {
        if (cabeza == null) return "Lista vacía";
        Nodo<E> nodoRecorrer = cabeza;
        StringBuilder cadena = new StringBuilder();
        do {
            cadena.append(nodoRecorrer.getElemento()).append(" -> ");
            nodoRecorrer = nodoRecorrer.getSiguiente();
        } while (nodoRecorrer != cabeza);
        return cadena.substring(0, cadena.length() - 4);
    }

    public boolean esCircular() {
        if (cabeza == null) return false;
        Nodo<E> nodo = cabeza;
        while (nodo.getSiguiente() != null && nodo.getSiguiente() != cabeza) {
            nodo = nodo.getSiguiente();
        }
        return nodo.getSiguiente() == cabeza;
    }

    public void convertirEnCircular() {
        if (cabeza == null) return;
        Nodo<E> nodo = cabeza;
        while (nodo.getSiguiente() != null) {
            nodo = nodo.getSiguiente();
        }
        nodo.setSiguiente(cabeza);
    }
}

