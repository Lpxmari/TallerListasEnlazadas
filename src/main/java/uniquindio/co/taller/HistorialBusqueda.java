package uniquindio.co.taller;

class Nodo {
    String termino;
    Nodo siguiente;

    public Nodo(String termino) {
        this.termino = termino;
        this.siguiente = null;
    }
}

public class HistorialBusqueda {
    private Nodo cabeza;

    public HistorialBusqueda() {
        cabeza = null;
    }

    // Agregar un nuevo término al inicio
    public void agregar(String termino) {
        Nodo nuevoNodo = new Nodo(termino);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }

    // Eliminar duplicados dejando solo la búsqueda más reciente
    public void eliminarDuplicados() {
        Nodo actual = cabeza;
        while (actual != null) {
            Nodo prev = actual;
            Nodo temp = actual.siguiente;
            while (temp != null) {
                if (temp.termino.equals(actual.termino)) {
                    prev.siguiente = temp.siguiente; // Salta el duplicado
                } else {
                    prev = temp;
                }
                temp = temp.siguiente;
            }
            actual = actual.siguiente;
        }
    }

    // Mostrar términos que comiencen con una letra dada
    public void mostrarPorLetra(char letra) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.termino.charAt(0) == letra) {
                System.out.println(actual.termino);
            }
            actual = actual.siguiente;
        }
    }

    // Mostrar todo el historial
    public void mostrarHistorial() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.termino + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        HistorialBusqueda historial = new HistorialBusqueda();
        historial.agregar("netflix");
        historial.agregar("disney");
        historial.agregar("netflix");
        historial.agregar("max");
        historial.agregar("spotify");

        System.out.println("Historial original:");
        historial.mostrarHistorial();

        System.out.println("\nHistorial sin duplicados:");
        historial.eliminarDuplicados();
        historial.mostrarHistorial();

        System.out.println("\nTérminos que comienzan con 'n':");
        historial.mostrarPorLetra('j');
    }
}
