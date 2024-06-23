package habitacion.state;

import habitacion.Habitacion;

public class Disponible implements IHabitacionState {
  private Habitacion habitacion;

  public Disponible(Habitacion habitacion) {
    this.habitacion = habitacion;
  }

  @Override
  public String getEstado() {
    return "Disponible";
  }

  @Override
  public void siguiente() {
    habitacion.setEstado(new Ocupado(habitacion));
  }
}
