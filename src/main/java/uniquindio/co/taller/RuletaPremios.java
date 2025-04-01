package uniquindio.co.taller;

import java.util.Random;

class NodoPremio {
    String premio;
    int valor;
    NodoPremio siguiente;
    NodoPremio anterior;

    public NodoPremio(String premio, int valor) {
        this.premio = premio;
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }
}

public class RuletaPremios {
    private NodoPremio actual;
    private Random random;

    public RuletaPremios() {
        this.actual = null;
        this.random = new Random();
    }

    // Agregar un premio a la ruleta (al final)
    public void agregarPremio(String premio, int valor) {
        NodoPremio nuevo = new NodoPremio(premio, valor);
        if (actual == null) {
            actual = nuevo;
            actual.siguiente = actual;
            actual.anterior = actual;
        } else {
            NodoPremio ultimo = actual.anterior;
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            nuevo.siguiente = actual;
            actual.anterior = nuevo;
        }
        System.out.println("🎁 Premio agregado: " + premio + " ($" + valor + ")");
    }

    // Simular giro aleatorio y seleccionar un premio
    public void girarRuleta() {
        if (actual == null) {
            System.out.println("🚫 No hay premios en la ruleta.");
            return;
        }

        int giros = random.nextInt(10) + 1; // Giros entre 1 y 10
        for (int i = 0; i < giros; i++) {
            actual = actual.siguiente;
        }
        System.out.println("\n🎯 Premio seleccionado: " + actual.premio + " ($" + actual.valor + ")");
    }

    // Recorrer la ruleta hacia adelante
    public void recorrerRuletaAdelante() {
        if (actual == null) {
            System.out.println("🚫 No hay premios en la ruleta.");
            return;
        }

        NodoPremio temp = actual;
        System.out.println("\n🔄 Recorrido adelante:");
        do {
            System.out.println("🎁 " + temp.premio + " ($" + temp.valor + ")");
            temp = temp.siguiente;
        } while (temp != actual);
    }

    // Recorrer la ruleta hacia atrás
    public void recorrerRuletaAtras() {
        if (actual == null) {
            System.out.println("🚫 No hay premios en la ruleta.");
            return;
        }

        NodoPremio temp = actual.anterior;
        System.out.println("\n🔄 Recorrido atrás:");
        do {
            System.out.println("🎁 " + temp.premio + " ($" + temp.valor + ")");
            temp = temp.anterior;
        } while (temp != actual.anterior);
    }

    // Eliminar un premio de la ruleta
    public void eliminarPremio() {
        if (actual == null) {
            System.out.println("🚫 No hay premios en la ruleta.");
            return;
        }

        System.out.println("\n❌ Eliminando premio: " + actual.premio);
        if (actual.siguiente == actual) { // Solo un nodo
            actual = null;
        } else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
            actual = actual.siguiente; // Moverse al siguiente premio
        }
    }

    // Insertar un premio en una posición aleatoria
    public void insertarPremioAleatorio(String premio, int valor) {
        if (actual == null) {
            agregarPremio(premio, valor);
            return;
        }

        NodoPremio nuevo = new NodoPremio(premio, valor);
        int pasos = random.nextInt(5) + 1; // Insertar en una posición aleatoria (1-5 posiciones adelante)
        NodoPremio temp = actual;
        for (int i = 0; i < pasos; i++) {
            temp = temp.siguiente;
        }

        nuevo.siguiente = temp.siguiente;
        nuevo.anterior = temp;
        temp.siguiente.anterior = nuevo;
        temp.siguiente = nuevo;

        System.out.println("🎁 Premio insertado aleatoriamente: " + premio + " ($" + valor + ")");
    }

    public static void main(String[] args) {
        RuletaPremios ruleta = new RuletaPremios();

        // Agregar premios
        ruleta.agregarPremio("Auto", 50000);
        ruleta.agregarPremio("Viaje", 10000);
        ruleta.agregarPremio("TV", 1500);
        ruleta.agregarPremio("Celular", 800);

        // Mostrar premios en ambas direcciones
        ruleta.recorrerRuletaAdelante();
        ruleta.recorrerRuletaAtras();

        // Simular giro de ruleta
        ruleta.girarRuleta();

        // Eliminar un premio y mostrar la ruleta nuevamente
        ruleta.eliminarPremio();
        ruleta.recorrerRuletaAdelante();

        // Insertar un premio en una posición aleatoria
        ruleta.insertarPremioAleatorio("Laptop", 2000);
        ruleta.recorrerRuletaAdelante();
    }
}

