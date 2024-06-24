package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import facade.FacadeHoteleria;
import usuario.Huesped;

public class ViewWeb extends JPanel {
    private FacadeHoteleria facade;

    public ViewWeb(FacadeHoteleria facade) {


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

            // Llamar al método para guardar el huésped
            guardarHuesped(nuevoHuesped);
        }
    }

    // Método para guardar un nuevo huésped
    private void guardarHuesped(Huesped huesped) {
        // Crear una cadena con los datos del huésped
        String datosHuesped = huesped.getDni() + "," +
                               huesped.getNombre() + "," +
                               huesped.getApellido() + "," +
                               huesped.getTelefono() + "," +
                               huesped.getEmail() + "," +
                               huesped.getContactoSMS() + "," +
                               huesped.getContactoWhatsApp() + "," +
                               huesped.getContactoEmail();

        // Definir el nombre del archivo donde se guardará la información
        String nombreArchivo = "huéspedes.txt";

        try {
            FileWriter fw = new FileWriter(nombreArchivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(datosHuesped);
            bw.newLine();
            bw.close();

            JOptionPane.showMessageDialog(this, "Huésped guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el huésped.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
