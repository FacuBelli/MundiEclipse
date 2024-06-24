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
      List<Habitacion> habitaciones = facade.getHabitaciones(); // Obtener las habitaciones desde la fachada

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
}