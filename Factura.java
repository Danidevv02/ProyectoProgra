
import java.time.LocalDateTime;
import java.time.Duration;

//Atributos de la clase
public class Factura {
    private String nombreCliente;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private long horasEstacionado;
    private double montoTotal;
    private double iva;
    private double totalPagar;
    
    // Constructor para crear una nueva factura
    public Factura(String nombre, LocalDateTime horaIngreso, LocalDateTime horaSalida, long horasEstacionado, double montoTotal, double iva, double totalPagar) {
        //this.nombreCliente = nombreCliente;
        //this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.horasEstacionado = Duration.between(horaEntrada, horaSalida).toHours();
        this.montoTotal = calcularMontoTotal();
        this.iva = calcularIVA();
        this.totalPagar = calcularTotalPagar();
    }

    private double calcularMontoTotal() {
        return horasEstacionado * 700; // 700 colones por hora
    }

    private double calcularIVA() {
        return montoTotal * 0.13; // IVA del 13%
    }

    private double calcularTotalPagar() {
        return montoTotal + iva;
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

    public double getMontoTotal() {
        return montoTotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    @Override
    public String toString() {
        return "Factura{" +
            "nombreCliente='" + nombreCliente + '\'' +
            ", horaEntrada=" + horaEntrada +
            ", horaSalida=" + horaSalida +
            ", horasEstacionado=" + horasEstacionado +
            ", montoTotal=" + montoTotal +
            ", iva=" + iva +
            ", totalPagar=" + totalPagar +
            '}';
    }
}


