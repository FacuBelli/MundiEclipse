package reserva;

import reserva.metodoDePago.MetodoDePago;

public class Pago {
  private MetodoDePago metodoDePago;
  private double monto;

  public void setMetodoDePago(MetodoDePago metodoDePago) {
    this.metodoDePago = metodoDePago;
  }

  public double getMonto() {
    return monto;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public Boolean procesarPago() {
    return true;
  }

  public MetodoDePago getMetodoDePago() {
    return metodoDePago;
  }
}