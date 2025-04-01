package uniquindio.co.taller;

class NodoPaciente {
    String nombre;
    NodoPaciente siguiente;

    public NodoPaciente(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}

public class SalaDeEspera {
    private NodoPaciente ultimo;

    public SalaDeEspera() {
        this.ultimo = null;
    }

    // Agregar un paciente a la cola
    public void agregarPaciente(String nombre) {
        NodoPaciente nuevo = new NodoPaciente(nombre);
        if (ultimo == null) { // Si la lista está vacía
            ultimo = nuevo;
            ultimo.siguiente = ultimo;
        } else {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        System.out.println("Paciente agregado: " + nombre);
    }

    // Atender al primer paciente (eliminarlo y pasar al siguiente)
    public void atenderPaciente() {
        if (ultimo == null) {
            System.out.println("No hay pacientes en la sala de espera.");
            return;
        }

        NodoPaciente primero = ultimo.siguiente;
        System.out.println("Atendiendo a: " + primero.nombre);

        if (primero == ultimo) { // Si hay un solo paciente
            ultimo = null;
        } else {
            ultimo.siguiente = primero.siguiente;
        }
    }

    // Mostrar la lista de pacientes en orden de turno
    public void mostrarPacientes() {
        if (ultimo == null) {
            System.out.println("No hay pacientes en espera.");
            return;
        }

        NodoPaciente temp = ultimo.siguiente;
        System.out.println("\nPacientes en espera:");
        do {
            System.out.println("- " + temp.nombre);
            temp = temp.siguiente;
        } while (temp != ultimo.siguiente);
    }

    // Simular la atención continua de pacientes en rondas
    public void atenderCiclo(int rondas) {
        if (ultimo == null) {
            System.out.println("No hay pacientes en espera.");
            return;
        }

        for (int i = 0; i < rondas; i++) {
            if (ultimo == null) {
                System.out.println("Todos los pacientes han sido atendidos.");
                break;
            }
            atenderPaciente();
        }
    }

    public static void main(String[] args) {
        SalaDeEspera sala = new SalaDeEspera();

        sala.agregarPaciente("Ana");
        sala.agregarPaciente("Luis");
        sala.agregarPaciente("Carlos");
        sala.agregarPaciente("María");

        sala.mostrarPacientes();

        System.out.println("\n--- Atendiendo pacientes ---");
        sala.atenderCiclo(5); // Simular la atención de 5 pacientes
    }
}

