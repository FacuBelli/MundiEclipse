package habitacion.state;

import habitacion.Habitacion;

public class Mantenimiento implements IHabitacionState {
  private Habitacion habitacion;

  public Mantenimiento(Habitacion habitacion) {
    this.habitacion = habitacion;
  }

  @Override
  public String getEstado() {
    return "Mantenimiento";
  }

  @Override
  public void siguiente() {
    habitacion.setEstado(new Disponible(habitacion));
  }
}
