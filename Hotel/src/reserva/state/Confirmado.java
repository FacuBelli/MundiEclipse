package reserva.state;

import reserva.Reserva;

public class Confirmado implements IReservaState {
  private Reserva reserva;

  public Confirmado(Reserva reserva) {
    this.reserva = reserva;
  }

  @Override
  public String estado() {
    return "Confirmado";
  }

  @Override
  public void siguiente() {
    reserva.setEstado(new Finalizado(reserva));
  }
}
