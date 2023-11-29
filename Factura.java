
package proyectoprogra;

import java.time.LocalDateTime;

public class Factura {
    private String nombreCliente;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private long horasEstacionado;
    private int montoTotal;
    private double iva;
    private double totalPagar;

    public Factura(String nombreCliente, LocalDateTime horaEntrada, LocalDateTime horaSalida, long horasEstacionado, int montoTotal, double iva, double totalPagar) {
        this.nombreCliente = nombreCliente;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.horasEstacionado = horasEstacionado;
        this.montoTotal = montoTotal;
        this.iva = iva;
        this.totalPagar = totalPagar;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public long getHorasEstacionado() {
        return horasEstacionado;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotalPagar() {
        return totalPagar;
    }
}

