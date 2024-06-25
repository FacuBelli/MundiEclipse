package ui.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import habitacion.Habitacion;

public class HabitacionTableModel extends AbstractTableModel {
  private List<Habitacion> habitaciones;
  private String[] columnNames = { "Identificador", "Tipo", "Estado", "Capacidad", "Tarifa", "Balcón", "Descripcion" };

  public HabitacionTableModel(List<Habitacion> habitaciones) {
    this.habitaciones = habitaciones;
  }

  @Override
  public int getRowCount() {
    return habitaciones.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Habitacion habitacion = habitaciones.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return habitacion.getIdentificador();
      case 1:
        return habitacion.getTipo().getNombre();
      case 2:
        return habitacion.getEstado().getEstado();
      case 3:
        return habitacion.getCapacidad();
      case 4:
        return habitacion.getTarifa();
      case 5:
        return habitacion.isBalcon();
      case 6:
        return habitacion.getDescripcion();
      default:
        return null;
    }
  }
}