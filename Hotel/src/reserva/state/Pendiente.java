package reserva.state;

import reserva.Reserva;

public class Pendiente implements IReservaState {
  private Reserva reserva;

  public Pendiente(Reserva reserva) {
    this.reserva = reserva;
  }

  @Override
  public String estado() {
    return "Pendiente";
  }

  @Override
  public void siguiente() {
    reserva.setEstado(new PagoPendiente(reserva));
  }
}