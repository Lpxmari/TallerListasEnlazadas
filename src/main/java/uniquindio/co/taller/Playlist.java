package uniquindio.co.taller;

class NodoCancion {
    String nombre;
    String artista;
    double duracion; // Duración en minutos
    NodoCancion anterior, siguiente;

    public NodoCancion(String nombre, String artista, double duracion) {
        this.nombre = nombre;
        this.artista = artista;
        this.duracion = duracion;
        this.anterior = null;
        this.siguiente = null;
    }
}

public class Playlist {
    private NodoCancion cabeza;
    private NodoCancion cola;

    public Playlist() {
        cabeza = cola = null;
    }

    // Agregar una canción al final de la playlist
    public void agregarCancion(String nombre, String artista, double duracion) {
        NodoCancion nueva = new NodoCancion(nombre, artista, duracion);
        if (cabeza == null) {
            cabeza = cola = nueva;
        } else {
            cola.siguiente = nueva;
            nueva.anterior = cola;
            cola = nueva;
        }
    }

    // Agregar una canción en una posición específica (1-based index)
    public void agregarEnPosicion(String nombre, String artista, double duracion, int posicion) {
        NodoCancion nueva = new NodoCancion(nombre, artista, duracion);
        if (posicion < 1) return;

        if (posicion == 1) { // Insertar al inicio
            nueva.siguiente = cabeza;
            if (cabeza != null) cabeza.anterior = nueva;
            cabeza = nueva;
            if (cola == null) cola = nueva;
            return;
        }

        NodoCancion temp = cabeza;
        for (int i = 1; temp != null && i < posicion - 1; i++) {
            temp = temp.siguiente;
        }

        if (temp == null) { // Insertar al final
            cola.siguiente = nueva;
            nueva.anterior = cola;
            cola = nueva;
        } else { // Insertar en medio
            nueva.siguiente = temp.siguiente;
            nueva.anterior = temp;
            if (temp.siguiente != null) temp.siguiente.anterior = nueva;
            temp.siguiente = nueva;
        }
    }

    // Eliminar una canción por nombre
    public void eliminarCancion(String nombre) {
        NodoCancion actual = cabeza;
        while (actual != null) {
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                if (actual == cabeza) {
                    cabeza = actual.siguiente;
                    if (cabeza != null) cabeza.anterior = null;
                } else if (actual == cola) {
                    cola = actual.anterior;
                    cola.siguiente = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                }
                System.out.println("Canción eliminada: " + actual.nombre);
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("Canción no encontrada.");
    }

    // Calcular el total de duración de la playlist
    public double calcularDuracionTotal() {
        double total = 0;
        NodoCancion actual = cabeza;
        while (actual != null) {
            total += actual.duracion;
            actual = actual.siguiente;
        }
        return total;
    }

    // Reproducir canciones en orden inverso
    public void reproducirInverso() {
        NodoCancion actual = cola;
        System.out.println("\nReproduciendo en orden inverso:");
        while (actual != null) {
            System.out.println(actual.nombre + " - " + actual.artista + " (" + actual.duracion + " min)");
            actual = actual.anterior;
        }
    }

    // Mostrar la playlist en orden normal
    public void mostrarPlaylist() {
        NodoCancion actual = cabeza;
        System.out.println("\nPlaylist actual:");
        while (actual != null) {
            System.out.println(actual.nombre + " - " + actual.artista + " (" + actual.duracion + " min)");
            actual = actual.siguiente;
        }
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.agregarCancion("Bohemian Rhapsody", "Queen", 5.55);
        playlist.agregarCancion("Shape of You", "Ed Sheeran", 4.24);
        playlist.agregarCancion("Blinding Lights", "The Weeknd", 3.22);
        playlist.agregarCancion("Hotel California", "Eagles", 6.30);

        playlist.mostrarPlaylist();

        System.out.println("\nAgregando canción en la posición 2...");
        playlist.agregarEnPosicion("Stairway to Heaven", "Led Zeppelin", 8.02, 2);
        playlist.mostrarPlaylist();

        System.out.println("\nEliminando 'Shape of You'...");
        playlist.eliminarCancion("Shape of You");
        playlist.mostrarPlaylist();

        System.out.println("\nDuración total: " + playlist.calcularDuracionTotal() + " minutos");

        playlist.reproducirInverso();
    }
}

