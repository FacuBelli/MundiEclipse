package ui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import facade.FacadeHoteleria;
import habitacion.Habitacion;
import usuario.Huesped;

public class ViewWeb extends JPanel {
  private FacadeHoteleria facade;

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

  // Método para buscar habitaciones
  private void buscarHabitaciones() {
      // Lógica para buscar habitaciones, puedes implementarla según tu necesidad
      // En este ejemplo, se muestra cómo obtener las habitaciones y mostrarlas en un diálogo
      List<Habitacion> habitaciones = facade.obtenerHabitaciones(); // Obtener las habitaciones desde la fachada

      StringBuilder mensaje = new StringBuilder("Habitaciones Disponibles:\n");
      for (Habitacion habitacion : habitaciones) {
          mensaje.append("Identificador: ").append(habitacion.getIdentificador())
                  .append(", Tipo: ").append(habitacion.getTipo())
                  .append(", Estado: ").append(habitacion.getEstado())
                  .append(", Capacidad: ").append(habitacion.getCapacidad())
                  .append(", Tarifa: ").append(habitacion.getTarifa())
                  .append(", Balcón: ").append(habitacion.isBalcon())
                  .append(", Descripción: ").append(habitacion.getDescripcion())
                  .append("\n");
      }

      JOptionPane.showMessageDialog(this, mensaje.toString(), "Habitaciones Disponibles",
              JOptionPane.INFORMATION_MESSAGE);
  }

  // Método para guardar un huésped (debes implementar tu lógica específica)
  private void guardarHuesped(Huesped nuevoHuesped) {
      // Lógica para guardar un huésped, implementar según tus necesidades
      // Aquí se muestra solo un mensaje de confirmación
      JOptionPane.showMessageDialog(this, "Huesped guardado correctamente.", "Éxito",
              JOptionPane.INFORMATION_MESSAGE);
  }

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

// Método para guardar una habitación
private void guardarHabitacion(Habitacion habitacion) {
    try {
        facade.guardarHabitacion(habitacion); // Guardar la habitación a través de la fachada
        JOptionPane.showMessageDialog(this, "Habitación guardada correctamente.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al guardar la habitación.", "Error",
                JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
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
          Habitacion habitacion = facade.obtenerHabitacionPorIdentificador(identificador);

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

}