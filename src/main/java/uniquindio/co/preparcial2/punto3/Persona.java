package uniquindio.co.preparcial2.punto3;

public class Persona {
    private String nombre;
    private int    edad;
    private char   sexo;          // 'M' = masculino, 'F' = femenino …

    public Persona(String nombre, int edad, char sexo) {
        this.nombre = nombre;
        this.edad  = edad;
        this.sexo  = sexo;
    }

    public String getNombre() { return nombre; }
    public int    getEdad()   { return edad;   }
    public char   getSexo()   { return sexo;   }

    @Override
    public String toString() {
        return nombre + " (" + edad + ", " + sexo + ")";
    }
}

/* ---------- 2. Nodo genérico ---------- */
class Nodo<T> {
    T dato;
    Nodo<T> siguiente;

    Nodo(T dato) { this.dato = dato; }
}

/* ---------- 3. Cola genérica ---------- */
class Cola<T> {

    private Nodo<T> frente;          // primer elemento
    private Nodo<T> fin;             // último elemento
    private int     tamano;

    /* -- operaciones básicas -- */

    public void encolar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamano++;
    }

    public T desencolar() {
        if (estaVacia()) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamano--;
        return dato;
    }

    public boolean estaVacia() { return frente == null; }

    public int tamano() { return tamano; }


    /**
     * Elimina de la cola a los hombres con edad entre 30 y 50 años (inclusive).
     * Complejidad: O(n) tiempo y O(1) memoria adicional.
     */
    public void filtrarHombres30a50() {

        int elementosOriginales = tamano;           // fijamos límite
        for (int i = 0; i < elementosOriginales; i++) {

            @SuppressWarnings("unchecked")
            Persona persona = (Persona) desencolar();  // siempre es Persona

            boolean esHombre    = persona.getSexo() == 'M';
            boolean edadRango   = persona.getEdad() >= 30 && persona.getEdad() <= 50;

            if (!(esHombre && edadRango)) {
                encolar((T) persona);               // se conserva el orden
            }
            // si cumple la condición, simplemente no se vuelve a encolar
        }
    }

    /* -- utilidades solo para mostrar la cola -- */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> p = frente;
        while (p != null) {
            sb.append(p.dato);
            p = p.siguiente;
            if (p != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}


