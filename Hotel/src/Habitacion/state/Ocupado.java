package habitacion.state;

import habitacion.Habitacion;

public class Ocupado implements IHabitacionState {
  private Habitacion habitacion;

  public Ocupado(Habitacion habitacion) {
    this.habitacion = habitacion;
  }

  @Override
  public String getEstado() {
    return "Ocupado";
  }

  @Override
  public void siguiente() {
    habitacion.setEstado(new Mantenimiento(habitacion));
  }
}
