package uniquindio.co.taller;

import java.util.ArrayList;
import java.util.List;

public class RegistroTemperaturas {
    private static class Nodo {
        double temperatura;
        Nodo siguiente;

        Nodo(double temperatura) {
            this.temperatura = temperatura;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private int tamanio;
    private double sumaTemperaturas;

    public RegistroTemperaturas() {
        this.cabeza = null;
        this.tamanio = 0;
        this.sumaTemperaturas = 0;
    }

    // Agregar una nueva temperatura al final de la lista
    public void agregarTemperatura(double temperatura) {
        Nodo nuevoNodo = new Nodo(temperatura);
        sumaTemperaturas += temperatura;

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamanio++;
    }

    // Calcular la temperatura promedio
    public double calcularTemperaturaPromedio() {
        if (tamanio == 0) return 0;
        return sumaTemperaturas / tamanio;
    }

    // Obtener los días con temperatura superior a un umbral
    public List<Integer> obtenerDiasPorEncimaDe(double umbral) {
        List<Integer> dias = new ArrayList<>();
        Nodo actual = cabeza;
        int dia = 1;

        while (actual != null) {
            if (actual.temperatura > umbral) {
                dias.add(dia);
            }
            actual = actual.siguiente;
            dia++;
        }
        return dias;
    }
}

