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
import ui.table.HabitacionTableModel;
import ui.table.HuespedTableModel;
import ui.table.ReservaTableModel;
import usuario.Huesped;

import java.util.Date;
import java.util.List;

public class ViewRecepcion extends JPanel {
  public ViewRecepcion(FacadeHoteleria facade) {
    JPanel seccionHuespedes = new JPanel();
    seccionHuespedes.add(new JLabel("Huespedes:"));
    seccionHuespedes.setLayout(new BoxLayout(seccionHuespedes, BoxLayout.Y_AXIS));

    JButton btnRegistrarHuesped = new JButton("Registrar Huesped");
    btnRegistrarHuesped.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new RegistrarHuespedFrame(facade);
      }
    });
    seccionHuespedes.add(btnRegistrarHuesped);
    btnRegistrarHuesped.setAlignmentX(Component.LEFT_ALIGNMENT);

    JButton btnVerHuespedes = new JButton("Ver Huespedes");
    btnVerHuespedes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new VerHuespedesFrame(facade);
      }
    });
    seccionHuespedes.add(btnVerHuespedes);
    btnVerHuespedes.setAlignmentX(Component.LEFT_ALIGNMENT);

    JPanel seccionHabitaciones = new JPanel();
    seccionHabitaciones.add(new JLabel("Habitaciones:"));
    seccionHabitaciones.setLayout(new BoxLayout(seccionHabitaciones, BoxLayout.Y_AXIS));

    JButton btnCrearHabitacion = new JButton("Crear Habitación");
    btnCrearHabitacion.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CrearHabitacionFrame(facade);
      }
    });
    seccionHabitaciones.add(btnCrearHabitacion);
    btnCrearHabitacion.setAlignmentX(Component.LEFT_ALIGNMENT);

    JButton btnVerHabitaciones = new JButton("Ver Habitaciones");
    btnVerHabitaciones.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new VerHabitacionesFrame(facade);
      }
    });
    seccionHabitaciones.add(btnVerHabitaciones);
    btnVerHabitaciones.setAlignmentX(Component.LEFT_ALIGNMENT);

    JPanel seccionReservas = new JPanel();
    seccionReservas.add(new JLabel("Reservas:"));
    seccionReservas.setLayout(new BoxLayout(seccionReservas, BoxLayout.Y_AXIS));
    
    JButton btnCrearReserva = new JButton("Crear Reserva");
    btnCrearReserva.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CrearReservaFrame(facade);
      }
    });
    seccionReservas.add(btnCrearReserva);
    btnCrearReserva.setAlignmentX(Component.LEFT_ALIGNMENT);

    JButton btnVerReservas = new JButton("Ver Reservas");
    btnVerReservas.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new VerReservasFrame(facade);
      }
    });
    seccionReservas.add(btnVerReservas);
    btnVerReservas.setAlignmentX(Component.LEFT_ALIGNMENT);

    this.add(seccionHuespedes);
    this.add(seccionHabitaciones);
    this.add(seccionReservas);
  }

  private class RegistrarHuespedFrame extends JFrame {
    private FacadeHoteleria facade;
    private JTextField txtNombre, txtApellido, txtDni, txtTelefono, txtEmail;

    public RegistrarHuespedFrame(FacadeHoteleria facade) {
      this.facade = facade;
      this.setTitle("Registrar Huesped");
      this.setSize(400, 300);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      JPanel panelRegistro = new JPanel(new GridLayout(6, 2));
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

      this.add(panelRegistro);
      this.setVisible(true);
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
  }

  private class CrearHabitacionFrame extends JFrame {
    private FacadeHoteleria facade;
    private JTextField txtHabitacionId, txtCapacidad, txtTarifa, txtDescripcion;
    private JComboBox<IHabitacionTipo> cbTipoHabitacion;
    private JCheckBox cbBalcon;

    public CrearHabitacionFrame(FacadeHoteleria facade) {
      this.facade = facade;
      this.setTitle("Crear Habitación");
      this.setSize(400, 300);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      JPanel panelHabitaciones = new JPanel(new GridLayout(7, 2));
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

      this.add(panelHabitaciones);
      this.setVisible(true);
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
        JOptionPane.showMessageDialog(this, "Error: Verifique los datos ingresados", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private class VerHabitacionesFrame extends JFrame {
    private JTable table;
    private List<Habitacion> habitaciones;

    public VerHabitacionesFrame(FacadeHoteleria facade) {
      this.setTitle("Ver Habitaciones");
      this.setSize(500, 400);
      this.habitaciones = facade.obtenerHabitaciones();
      table = new JTable(new HabitacionTableModel(habitaciones));
      this.add(table);
      this.getContentPane().add(new JScrollPane(table));
      this.setVisible(true);
    }
  }

  private class CrearReservaFrame extends JFrame {
    private FacadeHoteleria facade;
    private JTextField txtDni, txtHabitacionId, txtFechaInicio, txtFechaFin;

    public CrearReservaFrame(FacadeHoteleria facade) {
      this.facade = facade;
      this.setTitle("Crear Reserva");
      this.setSize(400, 300);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      JPanel panelReservas = new JPanel(new GridLayout(6, 2));
      txtDni = new JTextField();
      txtHabitacionId = new JTextField();
      txtFechaInicio = new JTextField();
      txtFechaFin = new JTextField();
      panelReservas.add(new JLabel("DNI Huesped:"));
      panelReservas.add(txtDni);
      panelReservas.add(new JLabel("ID Habitación:"));
      panelReservas.add(txtHabitacionId);
      panelReservas.add(new JLabel("Fecha Inicio (dd/MM/yyyy):"));
      panelReservas.add(txtFechaInicio);
      panelReservas.add(new JLabel("Fecha Fin (dd/MM/yyyy):"));
      panelReservas.add(txtFechaFin);

      JButton btnCrearReserva = new JButton("Crear Reserva");
      btnCrearReserva.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          crearReserva();
        }
      });
      panelReservas.add(btnCrearReserva);

      this.add(panelReservas);
      this.setVisible(true);
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

        Reserva reserva = facade.crearReserva(huesped, habitacion, fechaInicio, fechaFin);
        JOptionPane.showMessageDialog(this, "Reserva creada: " + reserva.getHabitacion().getIdentificador());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: Verifique los datos ingresados", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private class VerHuespedesFrame extends JFrame {
    private FacadeHoteleria facade;
    private JTextField txtBuscarDni;
    private JTable table;
    private List<Huesped> huespedes;
    private List<Huesped> huespedesFiltrado;

    public VerHuespedesFrame(FacadeHoteleria facade) {
      this.facade = facade;
      this.setTitle("Ver y Buscar Huespedes");
      this.setSize(400, 300);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      JPanel panelBuscar = new JPanel(new FlowLayout());
      txtBuscarDni = new JTextField(10);
      panelBuscar.add(new JLabel("Buscar por DNI:"));
      panelBuscar.add(txtBuscarDni);
      
      JButton btnBuscar = new JButton("Buscar");
      btnBuscar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          buscarHuesped();
        }
      });
      panelBuscar.add(btnBuscar);

      this.huespedes = facade.obtenerHuespedes();
      this.huespedesFiltrado = List.copyOf(huespedes);
      
      this.table = new JTable(new HuespedTableModel(huespedesFiltrado));

      this.add(panelBuscar, BorderLayout.NORTH);
      this.add(table, BorderLayout.CENTER);
      this.setVisible(true);
    }

    private void buscarHuesped() {
      String dni = txtBuscarDni.getText();
      Huesped huesped = facade.obtenerHuesped(dni);
      huespedesFiltrado.clear();
      if (huesped != null) {
        huespedesFiltrado.add(huesped);
      }
      table.repaint();
    }
  }

  private class VerReservasFrame extends JFrame {
    private JTable table;
    private List<Reserva> reservas;

    public VerReservasFrame(FacadeHoteleria facade) {
      this.setTitle("Ver y Buscar Reservas");
      this.setSize(500, 400);
      this.reservas = facade.obtenerReservas();
      table = new JTable(new ReservaTableModel(reservas));
      this.add(table);
      this.getContentPane().add(new JScrollPane(table));
      this.setVisible(true);
    }
  }
}
