package uniquindio.co.taller.listaDoblementeEnlazada;

public class NodoDoble<E> {
    private E elemento;
    private NodoDoble<E> anterior;
    private NodoDoble<E> siguiente;

    public NodoDoble(E elemento) {
        this.elemento = elemento;
        this.anterior = null;
        this.siguiente = null;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public NodoDoble<E> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<E> anterior) {
        this.anterior = anterior;
    }

    public NodoDoble<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble<E> siguiente) {
        this.siguiente = siguiente;
    }
}
