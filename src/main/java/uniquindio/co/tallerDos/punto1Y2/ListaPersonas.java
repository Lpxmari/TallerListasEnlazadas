package uniquindio.co.tallerDos.punto1Y2;

public class ListaPersonas {
    private NodoPersona cabeza;

    public ListaPersonas() {
        this.cabeza = null;
    }

    // Agregar persona al final de la lista
    public void agregar(String nombre, String cedula) {
        NodoPersona nuevo = new NodoPersona(nombre, cedula);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoPersona temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    // Obtener personas con cédula de cantidad par de dígitos
    public void obtenerPersonasCedulaPar() {
        if (cabeza == null) {
            System.out.println("Lista vacía.");
            return;
        }

        NodoPersona temp = cabeza;
        System.out.println("\n Personas con cédula de cantidad par de dígitos:");
        while (temp != null) {
            if (temp.cedula.length() % 2 == 0) { // Longitud par
                System.out.println(temp.nombre + " - Cédula: " + temp.cedula);
            }
            temp = temp.siguiente;
        }
    }
}

