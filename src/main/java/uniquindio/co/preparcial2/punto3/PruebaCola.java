package uniquindio.co.preparcial2.punto3;

public class PruebaCola {
    public static void main(String[] args) {

        Cola<Persona> cola = new Cola<>();
        cola.encolar(new Persona("Ana",   25, 'F'));
        cola.encolar(new Persona("Luis",  35, 'M'));  // ← se elimina
        cola.encolar(new Persona("María", 40, 'F'));
        cola.encolar(new Persona("José",  50, 'M'));  // ← se elimina
        cola.encolar(new Persona("Pedro", 60, 'M'));

        System.out.println("Antes  : " + cola);
        cola.filtrarHombres30a50();
        System.out.println("Después: " + cola);
    }
}
