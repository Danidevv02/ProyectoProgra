package proyectoprogra;


import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Parqueo {
    private Espacio[] espacios;
    private int espaciosRegistrados;
    private Map<LocalDate, Double> gananciasPorDia;
    
    // Método para encontrar un espacio por su identificador
    private Espacio encontrarEspacioPorId(int idEspacio) {
        for (int i = 0; i < espaciosRegistrados; i++) {
            if (espacios[i].getIdentificador() == idEspacio) {
                return espacios[i];
            }
        }
        return null;
    }

    // Método para ajustar la numeración de los espacios
    private void ajustarNumeracion() {
        // Ajustar la numeración del resto de espacios
        for (int i = espaciosRegistrados - 1; i >= 0; i--) {
            Espacio espacioActual = espacios[i];
            if (espacioActual.getIdentificador() > espaciosRegistrados) {
                espacioActual.setIdentificador(espacioActual.getIdentificador() + 1);
            }
        }
    }
    
    private void generarFactura(RegistroParqueo registro) {
        // Implementar la lógica para generar la factura
    }

    public Parqueo(Espacio[] espacios, int espaciosRegistrados) {
        this.espacios = espacios;
        this.espaciosRegistrados = espaciosRegistrados;
        
        // Inicializar el mapa de ganancias por día
        gananciasPorDia = new HashMap<>();
    }

    // Método para agregar un nuevo espacio
    public void agregarEspacio(String tipo, int capacidad) {
        // Verificar si hay espacios disponibles
        if (espaciosRegistrados < espacios.length) {
            Espacio nuevoEspacio = new Espacio(espaciosRegistrados + 1, tipo, capacidad);
            espacios[espaciosRegistrados] = nuevoEspacio;
            espaciosRegistrados++;
            ajustarNumeracion();
            JOptionPane.showMessageDialog(null, "Espacio agregado correctamente","Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Se ha alcanzado la capacidad máxima de espacios.",
                  "Capacidad Máxima Alcanzada", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método para cambiar el tipo de un espacio
    public void cambiarTipoEspacio(int idEspacio, String nuevoTipo) {
        Espacio espacio = encontrarEspacioPorId(idEspacio);
        if (espacio != null) {
            espacio.editarEspacio(nuevoTipo, espacio.getCapacidad());
            JOptionPane.showMessageDialog(null, "Tipo de espacio modificado correctamente","Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Espacio no encontrado.","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar un espacio
    public void eliminarEspacio(int idEspacio) {
        Espacio espacio = encontrarEspacioPorId(idEspacio);
        if (espacio != null) {
            espacio.inactivarEspacio();
            espaciosRegistrados--;
            ajustarNumeracion();
            JOptionPane.showMessageDialog(null, "Espacio eliminado correctamente","Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Espacio no encontrado.","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     // Método para crear un registro de parqueo
    public RegistroParqueo crearRegistroParqueo(String numeroPlaca, Usuario cliente) {
        RegistroParqueo nuevoRegistro = new RegistroParqueo(numeroPlaca, cliente);
        // Puedes almacenar el registro de parqueo en algún lugar, por ejemplo, en un array o lista.
        // registrosParqueo.add(nuevoRegistro);
        JOptionPane.showMessageDialog(null, "Registro de parqueo creado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return nuevoRegistro;
    }

    // Método para finalizar un registro de parqueo
    public void finalizarRegistroParqueo(RegistroParqueo registro) {
        registro.finalizarRegistro();
        // Aquí puedes generar la factura y realizar otras acciones necesarias.
        generarFactura(registro);
        JOptionPane.showMessageDialog(null, "Registro de parqueo finalizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Método para agregar ganancias al mapa por día
    public void agregarGananciasPorDia(LocalDate fecha, double monto) {
        gananciasPorDia.put(fecha, gananciasPorDia.getOrDefault(fecha, 0.0) + monto);
    }

    // Método para obtener el día que más ganancia facturó al mes
    public LocalDate obtenerDiaMaxGanancia() {
        LocalDate diaMaxGanancia = null;
        double maxGanancia = 0.0;

        for (Map.Entry<LocalDate, Double> entry : gananciasPorDia.entrySet()) {
            if (entry.getValue() > maxGanancia) {
                maxGanancia = entry.getValue();
                diaMaxGanancia = entry.getKey();
            }
        }

        return diaMaxGanancia;
        
    }
    public Iterable<RegistroParqueo> getRegistros() {
        // Falta lógica para obtener registros
        
        return null;
        // Falta lógica para obtener registros
        
    }

    public Espacio getEspacio() {
        // Falta lógica para obtener un espacio
        
        return null;
        // Falta lógica para obtener un espacio
        
    }

    void crearRegistroParqueo(RegistroParqueo registro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

