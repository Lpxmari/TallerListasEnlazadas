package uniquindio.co.taller.listaSiplementeEnlazada;

public class ListaSimplementeEnlazada<E> {
    private Nodo<E> inicial;
    private int tamanio;

    public ListaSimplementeEnlazada() {
        inicial = null;
        tamanio = 0;
    }

    public void agregarInicio(E dato) {
        Nodo<E> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(inicial);
        inicial = nuevoNodo;
        tamanio++;
    }

    public void agregarFinal(E dato) {
        Nodo<E> nuevoNodo = new Nodo<>(dato);
        if (inicial == null) {
            inicial = nuevoNodo;
        } else {
            Nodo<E> nodoRecorrer = inicial;
            while (nodoRecorrer.getSiguiente() != null) {
                nodoRecorrer = nodoRecorrer.getSiguiente();
            }
            nodoRecorrer.setSiguiente(nuevoNodo);
        }
        tamanio++;
    }

    public void eliminar(E dato) {
        if (inicial == null) return;

        if (inicial.getElemento().equals(dato)) {
            inicial = inicial.getSiguiente();
            tamanio--;
            return;
        }

        Nodo<E> nodoRecorrer = inicial;
        while (nodoRecorrer.getSiguiente() != null) {
            if (nodoRecorrer.getSiguiente().getElemento().equals(dato)) {
                nodoRecorrer.setSiguiente(nodoRecorrer.getSiguiente().getSiguiente());
                tamanio--;
                break;
            } else {
                nodoRecorrer = nodoRecorrer.getSiguiente();
            }
        }
    }

    public boolean obtener(E dato) {
        Nodo<E> recorrerNodo = inicial;
        while (recorrerNodo != null) {
            if (recorrerNodo.getElemento().equals(dato)) {
                return true;
            }
            recorrerNodo = recorrerNodo.getSiguiente();
        }
        return false;
    }

    public int localizar(E dato) {
        Nodo<E> recorrerNodo = inicial;
        int contador = 0;
        while (recorrerNodo != null) {
            if (recorrerNodo.getElemento().equals(dato)) {
                return contador;
            }
            recorrerNodo = recorrerNodo.getSiguiente();
            contador++;
        }
        return -1;
    }

    public String mostrar() {
        StringBuilder cadena = new StringBuilder();
        Nodo<E> nodoRecorrer = inicial;
        while (nodoRecorrer != null) {
            cadena.append(nodoRecorrer.getElemento());
            if (nodoRecorrer.getSiguiente() != null) {
                cadena.append(" -> ");
            }
            nodoRecorrer = nodoRecorrer.getSiguiente();
        }
        return cadena.toString();
    }
}
