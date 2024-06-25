package reserva;

import java.util.List;


public class GestorFacturacion {
  private Reserva reserva;
  private double subtotal;
  private List<Pago> pagos;
  private Factura factura;

  public GestorFacturacion(Reserva reserva) {
    this.reserva = reserva;
  }


  public double calcularSubtotal() {
    long dias = Math.ceilDiv(reserva.getFechaFin().getTime() - reserva.getFechaInicio().getTime(), (long) 8.64e+7);
    subtotal = reserva.getHabitacion().getTarifa() * dias;
    return subtotal;
  }

  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }

  public List<Pago> getPagos() {
    return pagos;
  }

  public void setPagos(List<Pago> pagos) {
    this.pagos = pagos;
  }

  public Factura generarFactura() {
    factura = new Factura(this);
    return factura;
  }
}
