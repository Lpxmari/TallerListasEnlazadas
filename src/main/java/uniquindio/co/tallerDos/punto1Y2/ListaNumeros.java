package uniquindio.co.tallerDos.punto1Y2;

public class ListaNumeros {
    private NodoNumero cabeza;

    public ListaNumeros() {
        this.cabeza = null;
    }

    // Agregar número al final de la lista
    public void agregar(int dato) {
        NodoNumero nuevo = new NodoNumero(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoNumero temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    // Obtener números en posiciones impares
    public void obtenerPosicionesImpares() {
        if (cabeza == null) {
            System.out.println("Lista vacía.");
            return;
        }

        NodoNumero temp = cabeza;
        int pos = 1;
        System.out.print(" Números en posiciones impares: ");
        while (temp != null) {
            if (pos % 2 != 0) { // Posición impar
                System.out.print(temp.dato + " ");
            }
            temp = temp.siguiente;
            pos++;
        }
        System.out.println();
    }

}

