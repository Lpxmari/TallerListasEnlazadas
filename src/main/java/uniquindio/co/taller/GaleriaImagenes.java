package uniquindio.co.taller;

class NodoImagen {
    int id;
    String nombre;
    double peso; // En MB
    NodoImagen anterior, siguiente;

    public NodoImagen(int id, String nombre, double peso) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.anterior = null;
        this.siguiente = null;
    }
}

public class GaleriaImagenes {
    private NodoImagen cabeza;
    private NodoImagen cola;

    public GaleriaImagenes() {
        cabeza = cola = null;
    }

    // Agregar una nueva imagen al final de la galería
    public void agregarImagen(int id, String nombre, double peso) {
        NodoImagen nueva = new NodoImagen(id, nombre, peso);
        if (cabeza == null) {
            cabeza = cola = nueva;
        } else {
            cola.siguiente = nueva;
            nueva.anterior = cola;
            cola = nueva;
        }
    }

    // Avanzar en la galería
    public void avanzar(NodoImagen actual) {
        if (actual != null && actual.siguiente != null) {
            System.out.println("Mostrando siguiente: " + actual.siguiente.nombre);
        } else {
            System.out.println("Fin de la galería.");
        }
    }

    // Retroceder en la galería
    public void retroceder(NodoImagen actual) {
        if (actual != null && actual.anterior != null) {
            System.out.println("Mostrando anterior: " + actual.anterior.nombre);
        } else {
            System.out.println("Inicio de la galería.");
        }
    }

    // Eliminar una imagen por ID
    public void eliminarImagen(int id) {
        NodoImagen actual = cabeza;
        while (actual != null) {
            if (actual.id == id) {
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
                System.out.println("Imagen eliminada: " + actual.nombre);
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("Imagen no encontrada.");
    }

    // Ordenar imágenes por tamaño (Burbuja)
    public void ordenarPorTamanio() {
        if (cabeza == null) return;
        boolean cambiado;
        do {
            cambiado = false;
            NodoImagen actual = cabeza;
            while (actual.siguiente != null) {
                if (actual.peso > actual.siguiente.peso) {
                    // Intercambiar los datos
                    int idTemp = actual.id;
                    String nombreTemp = actual.nombre;
                    double pesoTemp = actual.peso;

                    actual.id = actual.siguiente.id;
                    actual.nombre = actual.siguiente.nombre;
                    actual.peso = actual.siguiente.peso;

                    actual.siguiente.id = idTemp;
                    actual.siguiente.nombre = nombreTemp;
                    actual.siguiente.peso = pesoTemp;

                    cambiado = true;
                }
                actual = actual.siguiente;
            }
        } while (cambiado);
    }

    // Mover una imagen a una nueva posición (1-based index)
    public void moverImagen(int id, int nuevaPos) {
        if (cabeza == null || nuevaPos < 1) return;

        NodoImagen actual = cabeza, prev = null;
        while (actual != null && actual.id != id) {
            prev = actual;
            actual = actual.siguiente;
        }
        if (actual == null) return; // No encontrado

        // Desconectar nodo
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

        // Insertar en nueva posición
        NodoImagen temp = cabeza;
        for (int i = 1; temp != null && i < nuevaPos - 1; i++) {
            temp = temp.siguiente;
        }

        if (temp == null) { // Mover al final
            cola.siguiente = actual;
            actual.anterior = cola;
            actual.siguiente = null;
            cola = actual;
        } else { // Insertar en medio
            actual.siguiente = temp.siguiente;
            actual.anterior = temp;
            if (temp.siguiente != null) temp.siguiente.anterior = actual;
            temp.siguiente = actual;
        }
    }

    // Mostrar la galería
    public void mostrarGaleria() {
        NodoImagen actual = cabeza;
        while (actual != null) {
            System.out.println("ID: " + actual.id + " | " + actual.nombre + " | " + actual.peso + "MB");
            actual = actual.siguiente;
        }
    }

    public static void main(String[] args) {
        GaleriaImagenes galeria = new GaleriaImagenes();
        galeria.agregarImagen(1, "foto1.jpg", 3.2);
        galeria.agregarImagen(2, "foto2.png", 5.1);
        galeria.agregarImagen(3, "foto3.gif", 2.8);
        galeria.agregarImagen(4, "foto4.bmp", 4.5);

        System.out.println("Galería original:");
        galeria.mostrarGaleria();

        System.out.println("\nOrdenando por tamaño...");
        galeria.ordenarPorTamanio();
        galeria.mostrarGaleria();

        System.out.println("\nEliminando imagen con ID 2...");
        galeria.eliminarImagen(2);
        galeria.mostrarGaleria();

        System.out.println("\nMoviendo imagen con ID 3 a la posición 2...");
        galeria.moverImagen(3, 2);
        galeria.mostrarGaleria();
    }
}

