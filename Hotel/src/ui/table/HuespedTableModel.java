package ui.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import usuario.Huesped;

public class HuespedTableModel extends AbstractTableModel {
  private List<Huesped> huespedes;
  private String[] columnNames = { "Dni", "Nombre", "Apellido", "Telefono", "Email" };

  public HuespedTableModel(List<Huesped> huespedes) {
    this.huespedes = huespedes;
  }

  @Override
  public int getRowCount() {
    return huespedes.size();
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
    Huesped Huesped = huespedes.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return Huesped.getDni();
      case 1:
        return Huesped.getNombre();
      case 2:
        return Huesped.getApellido();
      case 3:
        return Huesped.getTelefono();
      case 4:
        return Huesped.getEmail();
      default:
        return null;
    }
  }
}
