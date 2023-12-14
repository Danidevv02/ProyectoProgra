package proyectoprogra;

import java.time.Duration;
import java.time.LocalDateTime;

//Atributos
public class RegistroParqueo {
    private LocalDateTime horaIngreso;
    private String numeroPlaca;
    private Usuario cliente;
    private LocalDateTime horaSalida;
    private long horasEstacionado;
    private Espacio espacio;
    
    // Constructor para crear un nuevo registro de parqueo
    public RegistroParqueo(String numeroPlaca, Usuario cliente, Espacio espacio, LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
        this.numeroPlaca = numeroPlaca;
        this.cliente = cliente;
        this.horaSalida = null; // La hora de salida se establecerá cuando se finalice el registro
    }
    
    // Finalizar el registro de parqueo y generar una factura
    public Factura finalizarRegistro() {
        if (horaSalida == null) {
            this.horaSalida = LocalDateTime.now();
            long horasEstacionado = Duration.between(horaIngreso, horaSalida).toHours();
            return generarFactura();
        } else {
            throw new IllegalStateException("El registro ya ha sido finalizado.");
        }
    }
    // Calcular la tarifa del registro de parqueo
    public double calcularTarifa() {
        long horasEstacionado = Duration.between(horaIngreso, horaSalida).toHours();
        return horasEstacionado * 700; // 700 colones por hora
    }

    Factura generarFactura() {
        double montoTotal = calcularMontoTotal(horasEstacionado);
        double iva = calcularIVA(montoTotal);
        double totalPagar = calcularTotalPagar(montoTotal, iva);

        return new Factura(cliente.getNombre(), horaIngreso, horaSalida, horasEstacionado, montoTotal, iva, totalPagar);
    }

    private double calcularMontoTotal(long horasEstacionado) {
        return horasEstacionado * 700; // Costa Rica, colones
    }

    private double calcularIVA(double montoTotal) {
        return montoTotal * 0.13; // Asumiendo un IVA del 13%
    }

    private double calcularTotalPagar(double montoTotal, double iva) {
        return montoTotal + iva;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }
    
    //Getter
    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }
    
    public Espacio getEspacio() {
        return espacio;
    }
    
}


