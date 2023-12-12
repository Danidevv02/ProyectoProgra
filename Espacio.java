import java.time.LocalDateTime;

public class Espacio {
    private int identificador;
    private boolean activo;
    private String tipo;
    private int capacidad;
    private int espaciosDisponibles;
    private Usuario ocupante;  // Asumiendo que un espacio puede ser ocupado por un Usuario

    public Espacio(int identificador, String tipo, int capacidad) {
        this.identificador = identificador;
        this.activo = true; // Se activa por defecto al crear un espacio
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.espaciosDisponibles = capacidad;
    }

    public void ocuparEspacio(Usuario usuario) throws Exception {
        if (isActivo() && espaciosDisponibles > 0) {
            espaciosDisponibles--;
            ocupante = usuario;
            System.out.println("Espacio ocupado por: " + usuario.getNombre());
        } else {
            throw new Exception("El espacio no está disponible.");
        }
    }

    public void desocupar() throws Exception {
        if (ocupante != null) {
            espaciosDisponibles++;
            System.out.println("Espacio desocupado. Gracias, " + ocupante.getNombre() + "!");
            ocupante = null;
        } else {
            throw new Exception("El espacio ya está desocupado.");
        }
    }

    // Método para obtener el ocupante del espacio
    public Usuario getOcupante() {
        return ocupante;
    }

    // Métodos para agregar, editar e inactivar espacios

    public void editarEspacio(String tipo, int capacidad) throws Exception {
        if (espaciosDisponibles == this.capacidad) { // Solo se puede editar si todos los espacios están disponibles
            this.tipo = tipo;
            this.capacidad = capacidad;
            this.espaciosDisponibles = capacidad; // Al editar, se reinicia la cantidad de espacios disponibles
        } else {
            throw new Exception("No se puede editar el espacio porque no todos los espacios están disponibles.");
        }
    }

    public void inactivarEspacio() throws Exception {
        if (espaciosDisponibles == capacidad) { // Solo se puede inactivar si todos los espacios están disponibles
            this.activo = false;
            this.espaciosDisponibles = 0; // Cuando se inactiva, no hay espacios disponibles
        } else {
            throw new Exception("No se puede inactivar el espacio porque no todos los espacios están disponibles.");
        }
    }

    public String toString() {
        String ocupanteInfo = (ocupante != null) ? ocupante.getNombre() + " " + ocupante.getApellidos() : "N/A";
        return "ID: " + identificador + ", Tipo: " + tipo + ", Capacidad: " + capacidad + ", Disponibles: " + espaciosDisponibles + ", Ocupante: " + ocupanteInfo;
    }
    

    public void asignarUsuario(Usuario usuario) throws Exception {
    if (isActivo() && espaciosDisponibles > 0) {
        espaciosDisponibles--;
        ocupante = usuario;
        System.out.println("Espacio asignado a: " + usuario.getNombre());
    } else {
        throw new Exception("El espacio no está disponible para asignar.");
    }
}

    // Getters y Setters

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public void setEspaciosDisponibles(int espaciosDisponibles) {
        this.espaciosDisponibles = espaciosDisponibles;
    }

    public String getNumeroPlaca() {
        return null;
    }

    public LocalDateTime getHoraIngreso() {
        return null;
    } 
}


