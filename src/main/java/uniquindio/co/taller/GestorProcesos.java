package uniquindio.co.taller;

import java.util.Random;

class NodoProceso {
    int id;
    int prioridad; // Mayor número = Mayor prioridad
    int tiempoEjecucion;
    NodoProceso siguiente;

    public NodoProceso(int id, int prioridad, int tiempoEjecucion) {
        this.id = id;
        this.prioridad = prioridad;
        this.tiempoEjecucion = tiempoEjecucion;
        this.siguiente = null;
    }
}

public class GestorProcesos {
    private NodoProceso primero;
    private Random random;

    public GestorProcesos() {
        this.primero = null;
        this.random = new Random();
    }

    // Insertar proceso en orden de prioridad (mayor prioridad primero)
    public void insertarProceso(int id, int prioridad, int tiempoEjecucion) {
        NodoProceso nuevo = new NodoProceso(id, prioridad, tiempoEjecucion);

        if (primero == null) {
            primero = nuevo;
            primero.siguiente = primero;
        } else {
            NodoProceso actual = primero, anterior = null;

            // Buscar la posición según prioridad
            do {
                if (actual.prioridad < prioridad) break;
                anterior = actual;
                actual = actual.siguiente;
            } while (actual != primero);

            if (anterior == null) { // Insertar al inicio
                nuevo.siguiente = primero;

                // Encontrar el último nodo
                NodoProceso temp = primero;
                while (temp.siguiente != primero) {
                    temp = temp.siguiente;
                }
                temp.siguiente = nuevo;
                primero = nuevo;
            } else { // Insertar en otra posición
                anterior.siguiente = nuevo;
                nuevo.siguiente = actual;
            }
        }
        System.out.println("Proceso agregado: ID=" + id + ", Prioridad=" + prioridad + ", Tiempo=" + tiempoEjecucion);
    }

    // Simular la ejecución de procesos
    public void ejecutarProceso() {
        if (primero == null) {
            System.out.println("No hay procesos en cola.");
            return;
        }

        System.out.println("\n⚡ Ejecutando proceso: ID=" + primero.id + ", Tiempo restante=" + primero.tiempoEjecucion);

        primero.tiempoEjecucion -= random.nextInt(3) + 1; // Reducir tiempo aleatoriamente entre 1 y 3 segundos

        if (primero.tiempoEjecucion <= 0) {
            System.out.println("Proceso finalizado: ID=" + primero.id);
            eliminarProceso();
        } else {
            // Rotar la cola después de la ejecución
            primero = primero.siguiente;
        }
    }

    // Eliminar el proceso finalizado
    public void eliminarProceso() {
        if (primero == null) return;

        NodoProceso actual = primero;

        if (primero.siguiente == primero) { // Solo un nodo
            primero = null;
        } else {
            // Encontrar el último nodo
            while (actual.siguiente != primero) {
                actual = actual.siguiente;
            }
            actual.siguiente = primero.siguiente;
            primero = primero.siguiente;
        }
    }

    // Mostrar los procesos en la cola
    public void mostrarCola() {
        if (primero == null) {
            System.out.println("🚫 No hay procesos en cola.");
            return;
        }

        NodoProceso temp = primero;
        System.out.println("\n📋 Procesos en cola:");
        do {
            System.out.println("🖥️ ID=" + temp.id + " | Prioridad=" + temp.prioridad + " | Tiempo restante=" + temp.tiempoEjecucion);
            temp = temp.siguiente;
        } while (temp != primero);
    }

    public static void main(String[] args) {
        GestorProcesos gestor = new GestorProcesos();

        // Insertar procesos
        gestor.insertarProceso(101, 3, 7);
        gestor.insertarProceso(102, 1, 4);
        gestor.insertarProceso(103, 2, 5);
        gestor.insertarProceso(104, 4, 8);

        gestor.mostrarCola();

        // Simular la ejecución de procesos
        for (int i = 0; i < 10; i++) {
            gestor.ejecutarProceso();
            gestor.mostrarCola();
        }
    }
}

