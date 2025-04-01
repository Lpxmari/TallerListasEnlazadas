package uniquindio.co.tallerDos.punto1Y2;

public class Main {
    public static void main(String[] args) {
        // Prueba con números
        ListaNumeros listaNumeros = new ListaNumeros();
        listaNumeros.agregar(10);
        listaNumeros.agregar(21);
        listaNumeros.agregar(32);
        listaNumeros.agregar(43);
        listaNumeros.agregar(54);
        listaNumeros.obtenerPosicionesImpares(); // Debe imprimir: 10 32 54

        // Prueba con personas
        ListaPersonas listaPersonas = new ListaPersonas();
        listaPersonas.agregar("Ana", "4194");
        listaPersonas.agregar("Luis", "98765");
        listaPersonas.agregar("Carlos", "8900740");
        listaPersonas.agregar("María", "1047");
        listaPersonas.obtenerPersonasCedulaPar(); // Debe imprimir Ana y María
    }
}

