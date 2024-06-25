package ui.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import reserva.Reserva;

public class ReservaTableModel extends AbstractTableModel {
  private List<Reserva> reservas;
  private String[] columnNames = { "Dni", "Huesped", "Habitacion", "Fecha Inicio", "Fecha Fín", "Estado",
      "Costo Total" };

  public ReservaTableModel(List<Reserva> reservas) {
    this.reservas = reservas;
  }

  @Override
  public int getRowCount() {
    return reservas.size();
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
    Reserva reserva = reservas.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return reserva.getHuesped().getDni();
      case 1:
        return reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellido();
      case 2:
        return reserva.getHabitacion().getIdentificador();
      case 3:
        return reserva.getFechaInicio();
      case 4:
        return reserva.getFechaFin();
      case 5:
        return reserva.getEstado().estado();
      case 6:
        return reserva.getGestorFacturacion().getSubtotal();
      default:
        return null;
    }
  }
}