package uniquindio.co.preparcial2.punto4;

public class BalanceoSimbolos {

    // Clase de pila propia
    static class Pila<T> {
        private Nodo<T> cima;

        private static class Nodo<T> {
            T dato;
            Nodo<T> siguiente;

            Nodo(T dato) {
                this.dato = dato;
            }
        }

        public void apilar(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            nuevo.siguiente = cima;
            cima = nuevo;
        }

        public T desapilar() {
            if (estaVacia()) return null;
            T dato = cima.dato;
            cima = cima.siguiente;
            return dato;
        }

        public boolean estaVacia() {
            return cima == null;
        }
    }

    public static boolean estanBalanceados(String expresion) {
        Pila<Character> pila = new Pila<>();

        for (char c : expresion.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                pila.apilar(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (pila.estaVacia()) return false;

                char tope = pila.desapilar();
                if ((c == ')' && tope != '(') ||
                        (c == '}' && tope != '{') ||
                        (c == ']' && tope != '[')) {
                    return false;
                }
            }
        }

        return pila.estaVacia();
    }

    public static void main(String[] args) {
        String ejemplo1 = "(6-7)/4]";      // incorrecto
        String ejemplo2 = "[(1+2)*4]+5";   // correcto

        System.out.println("Ejemplo 1: " + estanBalanceados(ejemplo1)); // false
        System.out.println("Ejemplo 2: " + estanBalanceados(ejemplo2)); // true
    }
}
