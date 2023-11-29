
package proyectoprogra;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RegistroParqueo {
    private LocalDateTime horaIngreso;
    private String numeroPlaca;
    private Usuario cliente;
    private LocalDateTime horaSalida;

    public RegistroParqueo(String numeroPlaca, Usuario cliente) {
        this.horaIngreso = LocalDateTime.now();
        this.numeroPlaca = numeroPlaca;
        this.cliente = cliente;
        this.horaSalida = null; // La hora de salida se establecerá cuando se finalice el registro
    }

    RegistroParqueo(String numeroPlaca, Usuario cliente, Espacio espacio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // Método para generar factura al registrar la salida
    public Factura generarFactura() {
        if (horaSalida != null) {
            long horasEstacionado = ChronoUnit.HOURS.between(horaIngreso, horaSalida);
            int costoPorHora = 700;
            int montoTotal = (int) horasEstacionado * costoPorHora;
            double iva = montoTotal * 0.13;
            double totalPagar = montoTotal + iva;

            // Marcar el espacio como disponible al generar la factura
            cliente.desocuparEspacio();

            // Crear la factura
            Factura factura = new Factura(cliente.getNombre(), horaIngreso, horaSalida, horasEstacionado, montoTotal, iva, totalPagar);

            return factura;
        } 
        else {
            return null; // El registro aún no ha finalizado
        }
    }

    private double calcularMontoTotal(long horasEstacionado) {
        double costoPorHora = 700; // Costa Rica, colones
        return horasEstacionado * costoPorHora;
    }

    // Método para finalizar el registro de parqueo
    public void finalizarRegistro() {
        this.horaSalida = LocalDateTime.now();
    }

    // Otros getters para la información del registro
    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    Object getEspacio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

