package uniquindio.co.pilas;

import java.util.Scanner;

public class Ejercicio1 {

    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
                case '-':
                return 1;
            case '*':
                case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    // Método para convertir de infija a postfija
    public static String convertirInfijaAPostfija(String expresion) {
        StringBuilder salida = new StringBuilder();
        Pila<Character> pila = new Pila<>();

        for (char token : expresion.toCharArray()) {
            if (Character.isLetterOrDigit(token)) {
                salida.append(token);
            } else if (token == '(') {
                pila.apilar(token);
            } else if (token == ')') {
                while (!pila.estaVacia() && pila.cima() != '(') {
                    salida.append(pila.desapilar());
                }
                pila.desapilar();
            } else {
                while (!pila.estaVacia() && precedencia(pila.cima()) >= precedencia(token)) {
                    salida.append(pila.desapilar());
                }
                pila.apilar(token);
            }
        }

        while (!pila.estaVacia()) {
            salida.append(pila.desapilar());
        }
        return salida.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese expresión infija: ");
            String expresion = scanner.nextLine();
            System.out.println("Expresión en postfija: " + convertirInfijaAPostfija(expresion));
        }
    }
}


