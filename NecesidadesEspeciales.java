
import javax.swing.JOptionPane;

//Atributos de la clase
public class NecesidadesEspeciales {
    private Espacio[] espacios;
    private int espaciosRegistrados;
    
    // Constructor para inicializar espacios de necesidades especiales
    public NecesidadesEspeciales(int maxEspacios) {
        this.espacios = new Espacio[maxEspacios];
        this.espaciosRegistrados = 0;
    }

    // Registrar un nuevo parqueo para necesidades especiales
    public void registrarParqueoNecesidadesEspeciales(String tipo, int capacidad) {
        if (espaciosRegistrados < espacios.length) {
            Espacio nuevoEspacio = new Espacio(espaciosRegistrados + 1, tipo, capacidad);
            espacios[espaciosRegistrados] = nuevoEspacio;
            espaciosRegistrados++;
            ajustarNumeracionNecesidadesEspeciales();
            JOptionPane.showMessageDialog(null, "Parqueo de necesidades especiales agregado correctamente","Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Se ha alcanzado la capacidad máxima de espacios.",
                    "Capacidad Máxima Alcanzada", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //Ajustar numeracion de espacios
    private void ajustarNumeracionNecesidadesEspeciales() {
        for (int i = espaciosRegistrados - 1; i >= 0; i--) {
            Espacio espacioActual = espacios[i];
            if (espacioActual.getIdentificador() > espaciosRegistrados) {
                espacioActual.setIdentificador(espacioActual.getIdentificador() + 1);
            }
        }
    }

    // Marcar un espacio como disponible para necesidades especiales
    public void marcarEspacioComoDisponible(int idEspacio) {
        Espacio espacio = encontrarEspacioPorId(idEspacio);
        if (espacio != null) {
            espacio.setEspaciosDisponibles(espacio.getCapacidad());
            JOptionPane.showMessageDialog(null, "Espacio marcado como disponible", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Espacio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Espacio encontrarEspacioPorId(int idEspacio) {
        for (int i = 0; i < espaciosRegistrados; i++) {
            if (espacios[i].getIdentificador() == idEspacio) {
                return espacios[i];
            }
        }
        return null;
    }

    public Espacio[] getEspacios() {
        return espacios;
    }

    public int getEspaciosRegistrados() {
        return espaciosRegistrados;
    }
}


