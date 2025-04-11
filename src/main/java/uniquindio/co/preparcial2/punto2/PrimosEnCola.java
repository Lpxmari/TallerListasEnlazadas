package uniquindio.co.preparcial2.punto2;
import java.util.*;

public class PrimosEnCola {

    private static boolean esPrimo(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static Queue<Integer> pasarPrimosAPila(Pila<Integer> pilaOriginal) {
        Queue<Integer> colaDePrimos = new LinkedList<>();
        Pila<Integer> auxiliar = new Pila<>();

        while (!pilaOriginal.estaVacia()) {
            int elemento = pilaOriginal.desapilar();
            auxiliar.apilar(elemento);
        }

        while (!auxiliar.estaVacia()) {
            int elemento = auxiliar.desapilar();
            pilaOriginal.apilar(elemento); // restaurar
            if (esPrimo(elemento)) {
                colaDePrimos.offer(elemento);
            }
        }

        return colaDePrimos;
    }

    static class Pila<T> {
        private LinkedList<T> elementos = new LinkedList<>();

        public void apilar(T elemento) {
            elementos.addFirst(elemento);
        }

        public T desapilar() {
            return elementos.removeFirst();
        }

        public boolean estaVacia() {
            return elementos.isEmpty();
        }

        public T cima() {
            return elementos.getFirst();
        }

        public void imprimir() {
            for (T elem : elementos) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(11);
        pila.apilar(8);
        pila.apilar(5);
        pila.apilar(10);
        pila.apilar(3);
        pila.apilar(4);

        System.out.print("Pila original: ");
        pila.imprimir();

        Queue<Integer> cola = pasarPrimosAPila(pila);

        System.out.print("Cola con primos: ");
        while (!cola.isEmpty()) {
            System.out.print(cola.poll() + " ");
        }
    }
}

