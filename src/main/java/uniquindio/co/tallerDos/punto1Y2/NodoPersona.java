package uniquindio.co.tallerDos.punto1Y2;

public class NodoPersona {
    String nombre;
    String cedula;
    NodoPersona siguiente;

    public NodoPersona(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.siguiente = null;
    }
}

