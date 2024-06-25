package ui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import facade.FacadeHoteleria;
import habitacion.Habitacion;
import reserva.Reserva;
import usuario.Huesped;

public class ViewWeb extends JPanel {
  private FacadeHoteleria facade;
  private static final String FILE_PATH = "huespedes.txt";

  public ViewWeb(FacadeHoteleria facade) {
      this.facade = facade;
      this.add(new JLabel("View Web"));
      this.setVisible(true);

      JPanel panel = new JPanel();
      this.add(panel);

      // CARGA HUESPED
      JButton btnCargarHuesped = new JButton("Cargar Nuevo Huesped");
      btnCargarHuesped.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              mostrarFormularioCargaHuesped();
          }
      });
      panel.add(btnCargarHuesped);

      // BOTÓN BUSCAR HABITACIONES
      JButton btnBuscarHabitaciones = new JButton("Buscar Habitaciones");
      btnBuscarHabitaciones.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              buscarHabitaciones();
          }
      });
      panel.add(btnBuscarHabitaciones);

      JButton btnCargarHabitacion = new JButton("Cargar Nueva Habitación");
        btnCargarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCargaHabitacion();
            }
        });
        panel.add(btnCargarHabitacion);
        // BOTÓN RESERVAR HABITACIÓN - Agregado el botón y su acción
        JButton btnReservarHabitacion = new JButton("Reservar Habitación");
        btnReservarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservarHabitacion();
            }
        });
        panel.add(btnReservarHabitacion);

        // BOTÓN VER DISPONIBILIDAD DE HABITACIONES
    JButton btnVerDisponibilidad = new JButton("Ver Disponibilidad de Habitaciones");
    btnVerDisponibilidad.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarDisponibilidadHabitaciones();
        }
    });
    panel.add(btnVerDisponibilidad);
  }



  // Método para mostrar el formulario de carga de huésped
  private void mostrarFormularioCargaHuesped() {
      JTextField txtDni = new JTextField(10);
      JTextField txtNombre = new JTextField(20);
      JTextField txtApellido = new JTextField(20);
      JTextField txtTelefono = new JTextField(15);
      JTextField txtEmail = new JTextField(30);
      JCheckBox chkContactoSMS = new JCheckBox("Contacto por SMS");
      JCheckBox chkContactoWhatsApp = new JCheckBox("Contacto por WhatsApp");
      JCheckBox chkContactoEmail = new JCheckBox("Contacto por Email");

      JPanel panelFormulario = new JPanel(new GridLayout(0, 1));
      panelFormulario.add(new JLabel("DNI:"));
      panelFormulario.add(txtDni);
      panelFormulario.add(new JLabel("Nombre:"));
      panelFormulario.add(txtNombre);
      panelFormulario.add(new JLabel("Apellido:"));
      panelFormulario.add(txtApellido);
      panelFormulario.add(new JLabel("Teléfono:"));
      panelFormulario.add(txtTelefono);
      panelFormulario.add(new JLabel("Email:"));
      panelFormulario.add(txtEmail);
      panelFormulario.add(chkContactoSMS);
      panelFormulario.add(chkContactoWhatsApp);
      panelFormulario.add(chkContactoEmail);

      int opcion = JOptionPane.showConfirmDialog(this, panelFormulario, "Cargar Nuevo Huesped",
              JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

      if (opcion == JOptionPane.OK_OPTION) {
          // Obtener los datos del formulario
          String dni = txtDni.getText();
          String nombre = txtNombre.getText();
          String apellido = txtApellido.getText();
          String telefono = txtTelefono.getText();
          String email = txtEmail.getText();
          Boolean contactoSMS = chkContactoSMS.isSelected();
          Boolean contactoWhatsApp = chkContactoWhatsApp.isSelected();
          Boolean contactoEmail = chkContactoEmail.isSelected();

          // INSTANCIA Y GUARDA HUESPED
          Huesped nuevoHuesped = new Huesped(dni, nombre, apellido, telefono, email);
          nuevoHuesped.setContactoSMS(contactoSMS);
          nuevoHuesped.setContactoWhatsApp(contactoWhatsApp);
          nuevoHuesped.setContactoEmail(contactoEmail);

          // Llamar al método para guardar el huésped (Aquí deberías llamar a tu lógica correspondiente)
          guardarHuesped(nuevoHuesped);
      }
  }


  // Método para mostrar el formulario de carga de habitación - Agregado el método completo
  private void mostrarFormularioCargaHabitacion() {
    JTextField txtIdentificador = new JTextField(10);
    JTextField txtCapacidad = new JTextField(10);
    JTextField txtTarifa = new JTextField(10);
    JCheckBox chkBalcon = new JCheckBox("Balcón");
    JTextField txtDescripcion = new JTextField(20);

    String[] tiposHabitacion = {"Sencilla", "Doble", "Suite"};
    JComboBox<String> comboTipo = new JComboBox<>(tiposHabitacion);

    JPanel panelFormulario = new JPanel(new GridLayout(0, 1));
    panelFormulario.add(new JLabel("Identificador:"));
    panelFormulario.add(txtIdentificador);
    panelFormulario.add(new JLabel("Tipo:"));
    panelFormulario.add(comboTipo);
    panelFormulario.add(new JLabel("Capacidad:"));
    panelFormulario.add(txtCapacidad);
    panelFormulario.add(new JLabel("Tarifa:"));
    panelFormulario.add(txtTarifa);
    panelFormulario.add(chkBalcon);
    panelFormulario.add(new JLabel("Descripción:"));
    panelFormulario.add(txtDescripcion);

    int opcion = JOptionPane.showConfirmDialog(this, panelFormulario, "Cargar Habitación",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (opcion == JOptionPane.OK_OPTION) {
        int identificador = Integer.parseInt(txtIdentificador.getText());
        String tipoStr = (String) comboTipo.getSelectedItem();
        int capacidad = Integer.parseInt(txtCapacidad.getText());
        double tarifa = Double.parseDouble(txtTarifa.getText());
        boolean balcon = chkBalcon.isSelected();
        String descripcion = txtDescripcion.getText();

        // Crear objeto Habitacion y guardar
        Habitacion nuevaHabitacion = new Habitacion(identificador, tipoStr, capacidad, tarifa, balcon, descripcion);
        guardarHabitacion(nuevaHabitacion);
    }
}

private void mostrarDisponibilidadHabitaciones() {
    // Pedir fechas de inicio y fin para la disponibilidad
    JSpinner spinnerFechaInicio = new JSpinner(new SpinnerDateModel());
    JSpinner spinnerFechaFin = new JSpinner(new SpinnerDateModel());

    JPanel panelFormulario = new JPanel(new GridLayout(0, 1));
    panelFormulario.add(new JLabel("Fecha de Inicio:"));
    panelFormulario.add(spinnerFechaInicio);
    panelFormulario.add(new JLabel("Fecha de Fin:"));
    panelFormulario.add(spinnerFechaFin);

    int opcion = JOptionPane.showConfirmDialog(this, panelFormulario, "Ver Disponibilidad de Habitaciones",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (opcion == JOptionPane.OK_OPTION) {
        Date fechaInicio = (Date) spinnerFechaInicio.getValue();
        Date fechaFin = (Date) spinnerFechaFin.getValue();

        // Llamar a la fachada para obtener la disponibilidad de habitaciones
        List<Habitacion> habitacionesDisponibles = facade.verDisponibilidadHabitaciones(fechaInicio, fechaFin);

        // Mostrar las habitaciones disponibles
        StringBuilder mensaje = new StringBuilder("Habitaciones Disponibles:\n");
        if (habitacionesDisponibles.isEmpty()) {
            mensaje.append("No hay habitaciones disponibles para las fechas seleccionadas.");
        } else {
            for (Habitacion habitacion : habitacionesDisponibles) {
                mensaje.append("Identificador: ").append(habitacion.getIdentificador()).append("\n")
                        .append("Tipo: ").append(habitacion.getTipo()).append("\n")
                        .append("Capacidad: ").append(habitacion.getCapacidad()).append("\n")
                        .append("Tarifa: ").append(habitacion.getTarifa()).append("\n")
                        .append("Balcón: ").append(habitacion.isBalcon()).append("\n")
                        .append("Descripción: ").append(habitacion.getDescripcion()).append("\n\n");
            }
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Disponibilidad de Habitaciones",
                JOptionPane.INFORMATION_MESSAGE);
    }
}


    // Método para reservar una habitación
    private void reservarHabitacion() {
        JTextField txtIdentificador = new JTextField(10);
        JTextField txtDni = new JTextField(10);

        JPanel panelFormulario = new JPanel(new GridLayout(0, 1));
        panelFormulario.add(new JLabel("Identificador de la Habitación:"));
        panelFormulario.add(txtIdentificador);
        panelFormulario.add(new JLabel("DNI del Huésped:"));
        panelFormulario.add(txtDni);

        int opcion = JOptionPane.showConfirmDialog(this, panelFormulario, "Reservar Habitación",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                int identificador = Integer.parseInt(txtIdentificador.getText());
                String dni = txtDni.getText();

                // Obtener el huésped y la habitación
                Huesped huesped = buscarHuespedPorDNI(dni);
                Habitacion habitacion = facade.obtenerHabitacion(identificador);

                if (huesped == null) {
                    JOptionPane.showMessageDialog(this, "No se encontró un huésped con ese DNI.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (habitacion == null) {
                    JOptionPane.showMessageDialog(this, "No se encontró una habitación con ese identificador.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener fechas de reserva (aquí debes implementar la lógica para obtener las fechas)

                // Llamar al método para crear la reserva
                Reserva reserva = facade.crearReserva(huesped, habitacion, new Date(), new Date(), 0.0);

                // Mostrar mensaje de confirmación
                JOptionPane.showMessageDialog(this, "Reserva realizada correctamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un identificador válido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    
    }



  // Método para guardar un huésped (debes implementar tu lógica específica)
  private void guardarHuesped(Huesped nuevoHuesped) {
      // Lógica para guardar un huésped, implementar según tus necesidades
      // Aquí se muestra solo un mensaje de confirmación
      JOptionPane.showMessageDialog(this, "Huesped guardado correctamente.", "Éxito",
              JOptionPane.INFORMATION_MESSAGE);
  }

  

// Método para guardar una habitación 
private void guardarHabitacion(Habitacion habitacion) {
    facade.guardarHabitacion(habitacion); // Guardar la habitación a través de la fachada
    JOptionPane.showMessageDialog(this, "Habitación guardada correctamente.", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
}

private void buscarHabitaciones() {
    JTextField txtIdentificador = new JTextField(10);
    JPanel panelFormulario = new JPanel(new GridLayout(0, 1));
    panelFormulario.add(new JLabel("Identificador de la Habitación:"));
    panelFormulario.add(txtIdentificador);

    int opcion = JOptionPane.showConfirmDialog(this, panelFormulario, "Buscar Habitación",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (opcion == JOptionPane.OK_OPTION) {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            Habitacion habitacion = facade.obtenerHabitacion(identificador);

            if (habitacion != null) {
                // Mostrar la información de la habitación encontrada
                StringBuilder mensaje = new StringBuilder("Habitación Encontrada:\n");
                mensaje.append("Identificador: ").append(habitacion.getIdentificador()).append("\n")
                        .append("Tipo: ").append(habitacion.getTipo()).append("\n")
                        .append("Capacidad: ").append(habitacion.getCapacidad()).append("\n")
                        .append("Tarifa: ").append(habitacion.getTarifa()).append("\n")
                        .append("Balcón: ").append(habitacion.isBalcon()).append("\n")
                        .append("Descripción: ").append(habitacion.getDescripcion()).append("\n");

                JOptionPane.showMessageDialog(this, mensaje.toString(), "Habitación Encontrada",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una habitación con ese identificador.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un identificador válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Método para buscar un huésped por DNI en el archivo huespedes.txt
    private Huesped buscarHuespedPorDNI(String dni) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("huespedes.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length >= 8 && datos[0].trim().equals(dni.trim())) {
                    // Encontramos el huésped con el DNI buscado
                    String nombre = datos[1].trim();
                    String apellido = datos[2].trim();
                    String telefono = datos[3].trim();
                    String email = datos[4].trim();
                    boolean contactoSMS = Boolean.parseBoolean(datos[5].trim());
                    boolean contactoWhatsApp = Boolean.parseBoolean(datos[6].trim());
                    boolean contactoEmail = Boolean.parseBoolean(datos[7].trim());

                    // Crear objeto Huesped y devolverlo
                    Huesped huesped = new Huesped(dni, nombre, apellido, telefono, email);
                    huesped.setContactoSMS(contactoSMS);
                    huesped.setContactoWhatsApp(contactoWhatsApp);
                    huesped.setContactoEmail(contactoEmail);
                    return huesped;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades (por ejemplo, mostrar un mensaje de error)
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Si no se encontró el huésped con el DNI buscado, devolvemos null
        return null;
  
}

public static void guardarReserva(Reserva reserva) {
        String nombreArchivo = "reservas.txt";

        try (FileWriter fw = new FileWriter(nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Escribir los datos de la reserva en el archivo
            out.println("Reserva:");
            out.println("Huesped: " + reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellido());
            out.println("Habitacion: " + reserva.getHabitacion().getIdentificador());
            out.println("Fecha de Inicio: " + reserva.getFechaInicio());
            out.println("Fecha de Fin: " + reserva.getFechaFin());
            out.println("Costo Total: " + reserva.getCostoTotal());
            out.println(); // línea en blanco para separar reservas

            System.out.println("Reserva guardada en " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al guardar la reserva: " + e.getMessage());
        }
    }
}

