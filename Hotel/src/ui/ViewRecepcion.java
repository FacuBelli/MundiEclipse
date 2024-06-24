package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import facade.FacadeHoteleria;
import habitacion.Habitacion;
import habitacion.tipo.IHabitacionTipo;
import habitacion.tipo.Simple;
import habitacion.tipo.Suite;
import reserva.Reserva;
import usuario.Huesped;

import java.util.Date;
import java.util.List;

public class ViewRecepcion extends JPanel {
  private FacadeHoteleria facade;
  private JTextField txtNombre, txtApellido, txtDni, txtTelefono, txtEmail;
  private JTextField txtHabitacionId, txtCapacidad, txtTarifa, txtDescripcion;
  private JTextField txtFechaInicio, txtFechaFin, txtCostoTotal;
  private JTextArea areaReservas, areaHabitaciones;
  private JComboBox<IHabitacionTipo> cbTipoHabitacion;
  private JCheckBox cbBalcon;

  public ViewRecepcion(FacadeHoteleria facade) {
    this.facade = facade;
    this.setLayout(new BorderLayout());

    // Panel de Registro de Huespedes
    JPanel panelRegistro = new JPanel(new GridLayout(6, 2));
    panelRegistro.setBorder(BorderFactory.createTitledBorder("Registro de Huespedes"));
    txtNombre = new JTextField();
    txtApellido = new JTextField();
    txtDni = new JTextField();
    txtTelefono = new JTextField();
    txtEmail = new JTextField();
    panelRegistro.add(new JLabel("Nombre:"));
    panelRegistro.add(txtNombre);
    panelRegistro.add(new JLabel("Apellido:"));
    panelRegistro.add(txtApellido);
    panelRegistro.add(new JLabel("DNI:"));
    panelRegistro.add(txtDni);
    panelRegistro.add(new JLabel("Telefono:"));
    panelRegistro.add(txtTelefono);
    panelRegistro.add(new JLabel("Email:"));
    panelRegistro.add(txtEmail);

    JButton btnRegistrar = new JButton("Registrar");
    btnRegistrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        registrarHuesped();
      }
    });
    panelRegistro.add(btnRegistrar);

    // Panel de Creación de Habitaciones
    JPanel panelHabitaciones = new JPanel(new GridLayout(6, 2));
    panelHabitaciones.setBorder(BorderFactory.createTitledBorder("Crear Habitación"));
    txtHabitacionId = new JTextField();
    txtCapacidad = new JTextField();
    txtTarifa = new JTextField();
    cbTipoHabitacion = new JComboBox<IHabitacionTipo>();
    cbTipoHabitacion.addItem(new Simple());
    cbTipoHabitacion.addItem(new Suite());
    cbBalcon = new JCheckBox("Balcon");
    txtDescripcion = new JTextField();
    panelHabitaciones.add(new JLabel("ID Habitación:"));
    panelHabitaciones.add(txtHabitacionId);
    panelHabitaciones.add(new JLabel("Capacidad:"));
    panelHabitaciones.add(txtCapacidad);
    panelHabitaciones.add(new JLabel("Tarifa:"));
    panelHabitaciones.add(txtTarifa);
    panelHabitaciones.add(new JLabel("Tipo:"));
    panelHabitaciones.add(cbTipoHabitacion);
    panelHabitaciones.add(new JLabel("Balcon:"));
    panelHabitaciones.add(cbBalcon);
    panelHabitaciones.add(new JLabel("Descripción:"));
    panelHabitaciones.add(txtDescripcion);

    JButton btnCrearHabitacion = new JButton("Crear Habitación");
    btnCrearHabitacion.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        crearHabitacion();
      }
    });
    panelHabitaciones.add(btnCrearHabitacion);

    // Panel de Creación de Reservas
    JPanel panelReservas = new JPanel(new GridLayout(6, 2));
    panelReservas.setBorder(BorderFactory.createTitledBorder("Crear Reserva"));
    txtFechaInicio = new JTextField();
    txtFechaFin = new JTextField();
    txtCostoTotal = new JTextField();
    panelReservas.add(new JLabel("Fecha Inicio (dd/MM/yyyy):"));
    panelReservas.add(txtFechaInicio);
    panelReservas.add(new JLabel("Fecha Fin (dd/MM/yyyy):"));
    panelReservas.add(txtFechaFin);
    panelReservas.add(new JLabel("Costo Total:"));
    panelReservas.add(txtCostoTotal);

    JButton btnCrearReserva = new JButton("Crear Reserva");
    btnCrearReserva.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        crearReserva();
      }
    });
    panelReservas.add(btnCrearReserva);

    // Panel para mostrar reservas
    areaReservas = new JTextArea(10, 30);
    areaReservas.setEditable(false);
    JPanel panelMostrarReservas = new JPanel(new BorderLayout());
    panelMostrarReservas.setBorder(BorderFactory.createTitledBorder("Reservas"));
    panelMostrarReservas.add(new JScrollPane(areaReservas), BorderLayout.CENTER);

    JButton btnVerReservas = new JButton("Ver Reservas");
    btnVerReservas.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mostrarReservas();
      }
    });
    panelMostrarReservas.add(btnVerReservas, BorderLayout.SOUTH);

    // Panel para mostrar habitaciones
    areaHabitaciones = new JTextArea(10, 30);
    areaHabitaciones.setEditable(false);
    JPanel panelMostrarHabitaciones = new JPanel(new BorderLayout());
    panelMostrarHabitaciones.setBorder(BorderFactory.createTitledBorder("Habitaciones"));
    panelMostrarHabitaciones.add(new JScrollPane(areaHabitaciones), BorderLayout.CENTER);

    JButton btnVerHabitaciones = new JButton("Ver Habitaciones");
    btnVerHabitaciones.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mostrarHabitaciones();
      }
    });
    panelMostrarHabitaciones.add(btnVerHabitaciones, BorderLayout.SOUTH);

    JPanel panelCentro = new JPanel(new GridLayout(1, 2));
    panelCentro.add(panelMostrarReservas);
    panelCentro.add(panelMostrarHabitaciones);

    this.add(panelRegistro, BorderLayout.NORTH);
    this.add(panelHabitaciones, BorderLayout.WEST);
    this.add(panelReservas, BorderLayout.EAST);
    this.add(panelCentro, BorderLayout.CENTER);
  }

  private void registrarHuesped() {
    String nombre = txtNombre.getText();
    String apellido = txtApellido.getText();
    String dni = txtDni.getText();
    String telefono = txtTelefono.getText();
    String email = txtEmail.getText();

    Huesped huesped = facade.registrarHuesped(nombre, apellido, dni, telefono, email);
    JOptionPane.showMessageDialog(this, "Huesped registrado: " + huesped.getNombre() + " " + huesped.getApellido());
  }

  private void crearHabitacion() {
    try {
      int identificador = Integer.parseInt(txtHabitacionId.getText());
      IHabitacionTipo tipo = (IHabitacionTipo) cbTipoHabitacion.getSelectedItem();
      int capacidad = Integer.parseInt(txtCapacidad.getText());
      double tarifa = Double.parseDouble(txtTarifa.getText());
      boolean balcon = cbBalcon.isSelected();
      String descripcion = txtDescripcion.getText();

      Habitacion habitacion = facade.cargarHabitacion(identificador, tipo, capacidad, tarifa, balcon, descripcion);
      JOptionPane.showMessageDialog(this, "Habitación creada: " + habitacion.getIdentificador());
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: Verifique los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void crearReserva() {
    try {
      Huesped huesped = facade.obtenerHuesped(txtDni.getText());
      if (huesped == null) {
        JOptionPane.showMessageDialog(this, "Error: Huesped no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      int habitacionId = Integer.parseInt(txtHabitacionId.getText());
      Habitacion habitacion = facade.obtenerHabitacion(habitacionId);
      if (habitacion == null) {
        JOptionPane.showMessageDialog(this, "Error: Habitación no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      Date fechaInicio = new SimpleDateFormat("dd/MM/yyyy").parse(txtFechaInicio.getText());
      Date fechaFin = new SimpleDateFormat("dd/MM/yyyy").parse(txtFechaFin.getText());
      double costoTotal = Double.parseDouble(txtCostoTotal.getText());

      Reserva reserva = facade.crearReserva(huesped, habitacion, fechaInicio, fechaFin, costoTotal);
      JOptionPane.showMessageDialog(this, "Reserva creada: " + reserva.getHabitacion().getIdentificador());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Error: Verifique los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void mostrarReservas() {
    List<Reserva> reservas = facade.obtenerReservas();
    StringBuilder sb = new StringBuilder();
    for (Reserva reserva : reservas) {
      sb.append("Reserva: ").append(reserva.getHabitacion().getIdentificador())
          .append(", Huesped: ").append(reserva.getHuesped().getNombre())
          .append(" ").append(reserva.getHuesped().getApellido())
          .append(", Fecha Inicio: ").append(reserva.getFechaInicio())
          .append(", Fecha Fin: ").append(reserva.getFechaFin()).append("\n");
    }
  }

  private void mostrarHabitaciones() {
    List<Habitacion> habitaciones = facade.obtenerHabitaciones();
    StringBuilder sb = new StringBuilder();
    for (Habitacion habitacion : habitaciones) {
        sb.append("Habitación ID: ").append(habitacion.getIdentificador())
          .append(", Tipo: ").append(habitacion.getTipo().getClass().getSimpleName())
          .append(", Capacidad: ").append(habitacion.getCapacidad())
          .append(", Tarifa: ").append(habitacion.getTarifa())
          .append(", Balcón: ").append(habitacion.isBalcon() ? "Sí" : "No")
          .append(", Descripción: ").append(habitacion.getDescripcion()).append("\n");
    }
    areaHabitaciones.setText(sb.toString());
}

}