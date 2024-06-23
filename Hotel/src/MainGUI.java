import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import usuario.Huesped; 

public class MainGUI {
    private JFrame frame;

    public MainGUI() {
        frame = new JFrame("Sistema de Gestión Hotelera");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        // CARGA HUESPED
        JButton btnCargarHuesped = new JButton("Cargar Nuevo Huesped");
        btnCargarHuesped.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCargaHuesped();
            }
        });
        panel.add(btnCargarHuesped);

        JButton btnCargarHabitaciones = new JButton("Cargar Habitaciones");
        btnCargarHabitaciones.addActionListener(new ManejadorCargarHabitaciones());
        panel.add(btnCargarHabitaciones);

        JButton btnBuscarHabitaciones = new JButton("Buscar Habitaciones");
        btnBuscarHabitaciones.addActionListener(new ManejadorBuscarHabitaciones());
        panel.add(btnBuscarHabitaciones);

        JButton btnReservarHabitacion = new JButton("Reservar Habitación");
        btnReservarHabitacion.addActionListener(new ManejadorReservarHabitacion());
        panel.add(btnReservarHabitacion);

        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        btnCancelarReserva.addActionListener(new ManejadorCancelarReserva());
        panel.add(btnCancelarReserva);

        JButton btnActualizarFacturacion = new JButton("Actualizar Facturación");
        btnActualizarFacturacion.addActionListener(new ManejadorActualizarFacturacion());
        panel.add(btnActualizarFacturacion);

        JButton btnEnviarFacturas = new JButton("Enviar Facturas");
        btnEnviarFacturas.addActionListener(new ManejadorEnviarFacturas());
        panel.add(btnEnviarFacturas);

        JButton btnGenerarReporte = new JButton("Generar Reporte");
        btnGenerarReporte.addActionListener(new ManejadorGenerarReporte());
        panel.add(btnGenerarReporte);

        frame.setVisible(true);
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

        int opcion = JOptionPane.showConfirmDialog(frame, panelFormulario, "Cargar Nuevo Huesped",
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

            
            JOptionPane.showMessageDialog(frame, "Huésped guardado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al guardar el huésped.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clases internas para manejar los eventos de los botones
    private class ManejadorCargarHabitaciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleCargarHabitaciones();
        }
    }

    private class ManejadorBuscarHabitaciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleBuscarHabitaciones();
        }
    }

    private class ManejadorReservarHabitacion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleReservarHabitacion();
        }
    }

    private class ManejadorCancelarReserva implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleCancelarReserva();
        }
    }

    private class ManejadorActualizarFacturacion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleActualizarFacturacion();
        }
    }

    private class ManejadorEnviarFacturas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleEnviarFacturas();
        }
    }

    private class ManejadorGenerarReporte implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleGenerarReporte();
        }
    }

    // Métodos para manejar las acciones de los botones
    private void handleCargarHabitaciones() {
        System.out.println("Cargar Habitaciones");
        // Aquí puedes implementar la lógica para cargar las habitaciones disponibles
    }

    private void handleBuscarHabitaciones() {
        System.out.println("Buscar Habitaciones");
        // Aquí puedes implementar la lógica para buscar habitaciones según criterios
    }

    private void handleReservarHabitacion() {
        System.out.println("Reservar Habitación");
        // Aquí puedes implementar la lógica para reservar una habitación
    }

    private void handleCancelarReserva() {
        System.out.println("Cancelar Reserva");
        // Aquí puedes implementar la lógica para cancelar una reserva de habitación
    }

    private void handleActualizarFacturacion() {
        System.out.println("Actualizar Facturación");
        // Aquí puedes implementar la lógica para actualizar la facturación
    }

    private void handleEnviarFacturas() {
        System.out.println("Enviar Facturas");
        // Aquí puedes implementar la lógica para enviar las facturas generadas
    }

    private void handleGenerarReporte() {
        System.out.println("Generar Reporte");
        // Aquí puedes implementar la lógica para generar un reporte del sistema
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
