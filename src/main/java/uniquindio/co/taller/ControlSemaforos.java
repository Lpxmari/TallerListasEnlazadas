package uniquindio.co.taller;

class NodoSemaforo {
    String color;
    int duracion; // Duración en segundos
    NodoSemaforo siguiente;

    public NodoSemaforo(String color, int duracion) {
        this.color = color;
        this.duracion = duracion;
        this.siguiente = null;
    }
}

public class ControlSemaforos {
    private NodoSemaforo actual;

    public ControlSemaforos() {
        this.actual = null;
    }

    // Agregar un nuevo estado al ciclo del semáforo
    public void agregarEstado(String color, int duracion) {
        NodoSemaforo nuevo = new NodoSemaforo(color, duracion);
        if (actual == null) {
            actual = nuevo;
            actual.siguiente = actual; // Se apunta a sí mismo (lista circular)
        } else {
            NodoSemaforo temp = actual;
            while (temp.siguiente != actual) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
            nuevo.siguiente = actual;
        }
        System.out.println("Estado agregado: " + color + " (" + duracion + "s)");
    }

    // Eliminar un estado del ciclo
    public void eliminarEstado(String color) {
        if (actual == null) {
            System.out.println("No hay estados en el ciclo.");
            return;
        }

        NodoSemaforo temp = actual, previo = null;
        do {
            if (temp.color.equalsIgnoreCase(color)) {
                System.out.println("Eliminando estado: " + color);
                if (temp == actual && temp.siguiente == actual) { // Solo un nodo
                    actual = null;
                } else {
                    if (temp == actual) {
                        actual = actual.siguiente;
                    }
                    if (previo != null) {
                        previo.siguiente = temp.siguiente;
                    } else { // Eliminar el último nodo
                        NodoSemaforo ultimo = actual;
                        while (ultimo.siguiente != temp) {
                            ultimo = ultimo.siguiente;
                        }
                        ultimo.siguiente = actual;
                    }
                }
                return;
            }
            previo = temp;
            temp = temp.siguiente;
        } while (temp != actual);
        System.out.println("Estado no encontrado.");
    }

    // Modificar la duración de un estado específico
    public void modificarDuracion(String color, int nuevaDuracion) {
        if (actual == null) {
            System.out.println("No hay estados en el ciclo.");
            return;
        }

        NodoSemaforo temp = actual;
        do {
            if (temp.color.equalsIgnoreCase(color)) {
                System.out.println("Modificando " + color + " a " + nuevaDuracion + "s.");
                temp.duracion = nuevaDuracion;
                return;
            }
            temp = temp.siguiente;
        } while (temp != actual);
        System.out.println("Estado no encontrado.");
    }

    // Simular el ciclo de luces por cierto tiempo
    public void simularCiclo(int tiempoTotal) {
        if (actual == null) {
            System.out.println("No hay estados en el ciclo.");
            return;
        }

        int tiempoRestante = tiempoTotal;
        while (tiempoRestante > 0) {
            System.out.println("🚦 Luz: " + actual.color + " (" + actual.duracion + "s)");
            try {
                Thread.sleep(actual.duracion * 1000L); // Simula la espera (en segundos)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            tiempoRestante -= actual.duracion;
            actual = actual.siguiente; // Pasar al siguiente estado
        }
        System.out.println("Simulación finalizada.");
    }

    public static void main(String[] args) {
        ControlSemaforos semaforo = new ControlSemaforos();

        // Agregar los estados básicos del semáforo
        semaforo.agregarEstado("Verde", 10);
        semaforo.agregarEstado("Amarillo", 3);
        semaforo.agregarEstado("Rojo", 7);

        System.out.println("\n🔄 Iniciando simulación por 25 segundos...");
        semaforo.simularCiclo(25);

        // Modificar la duración del amarillo
        semaforo.modificarDuracion("Amarillo", 5);

        // Eliminar el estado amarillo
        semaforo.eliminarEstado("Amarillo");

        // Nueva simulación después de cambios
        System.out.println("\n🔄 Simulación tras cambios (20s)...");
        semaforo.simularCiclo(20);
    }
}
