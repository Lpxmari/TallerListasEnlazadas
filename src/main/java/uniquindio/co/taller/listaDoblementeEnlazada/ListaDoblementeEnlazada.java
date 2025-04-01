package uniquindio.co.taller.listaDoblementeEnlazada;


public class ListaDoblementeEnlazada<E extends Comparable<E>> {
    private NodoDoble<E> cabeza;
    private NodoDoble<E> cola;
    private int tamanio;

    public ListaDoblementeEnlazada() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public void agregarInicio(E dato) {
        NodoDoble<E> nuevoNodo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = cola = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevoNodo);
            cabeza = nuevoNodo;
        }
        tamanio++;
    }

    public void agregarFinal(E dato) {
        NodoDoble<E> nuevoNodo = new NodoDoble<>(dato);
        if (cola == null) {
            cabeza = cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(cola);
            cola = nuevoNodo;
        }
        tamanio++;
    }

    public void eliminar(E dato) {
        if (cabeza == null) return;

        if (cabeza.getElemento().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            if (cabeza != null) {
                cabeza.setAnterior(null);
            } else {
                cola = null;
            }
            tamanio--;
            return;
        }

        NodoDoble<E> nodoRecorrer = cabeza;
        while (nodoRecorrer != null) {
            if (nodoRecorrer.getElemento().equals(dato)) {
                if (nodoRecorrer.getSiguiente() != null) {
                    nodoRecorrer.getSiguiente().setAnterior(nodoRecorrer.getAnterior());
                } else {
                    cola = nodoRecorrer.getAnterior();
                }
                if (nodoRecorrer.getAnterior() != null) {
                    nodoRecorrer.getAnterior().setSiguiente(nodoRecorrer.getSiguiente());
                }
                tamanio--;
                return;
            }
            nodoRecorrer = nodoRecorrer.getSiguiente();
        }
    }

    public boolean obtener(E dato) {
        NodoDoble<E> recorrerNodo = cabeza;
        while (recorrerNodo != null) {
            if (recorrerNodo.getElemento().equals(dato)) {
                return true;
            }
            recorrerNodo = recorrerNodo.getSiguiente();
        }
        return false;
    }

    public int localizar(E dato) {
        NodoDoble<E> recorrerNodo = cabeza;
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
        NodoDoble<E> nodoRecorrer = cabeza;
        StringBuilder cadena = new StringBuilder();
        while (nodoRecorrer != null) {
            cadena.append(nodoRecorrer.getElemento());
            if (nodoRecorrer.getSiguiente() != null) {
                cadena.append(" <-> ");
            }
            nodoRecorrer = nodoRecorrer.getSiguiente();
        }
        return cadena.toString();
    }
}
