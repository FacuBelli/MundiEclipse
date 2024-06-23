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

        JButton btnReservar = new JButton("Reservar Habitación");
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método para reservar habitación
                reservarHabitacion();
            }
        });
        panel.add(btnReservar);

        JButton btnCancelar = new JButton("Cancelar Reserva");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método para cancelar reserva de habitación
                cancelarReserva();
            }
        });
        panel.add(btnCancelar);



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

    private void reservarHabitacion() {
            // Mostrar un formulario para ingresar los datos de la reserva
            JTextField txtNombre = new JTextField(20);
            JTextField txtFechaInicio = new JTextField(10);
            JTextField txtFechaFin = new JTextField(10);
        
            JPanel panelFormulario = new JPanel(new GridLayout(0, 1));
            panelFormulario.add(new JLabel("Nombre del huésped:"));
            panelFormulario.add(txtNombre);
            panelFormulario.add(new JLabel("Fecha de inicio (dd/mm/aaaa):"));
            panelFormulario.add(txtFechaInicio);
            panelFormulario.add(new JLabel("Fecha de fin (dd/mm/aaaa):"));
            panelFormulario.add(txtFechaFin);
        
            int opcion = JOptionPane.showConfirmDialog(frame, panelFormulario, "Reservar Habitación",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
            if (opcion == JOptionPane.OK_OPTION) {
                String nombreHuesped = txtNombre.getText();
                String fechaInicio = txtFechaInicio.getText();
                String fechaFin = txtFechaFin.getText();
        
                // Aquí iría la lógica para verificar disponibilidad de habitaciones
                boolean habitacionDisponible = verificarDisponibilidad();
        
                if (habitacionDisponible) {
                    // Si la habitación está disponible, realizar la reserva
                    // Aquí puedes implementar el código para guardar la reserva en una base de datos o archivo
                    JOptionPane.showMessageDialog(frame, "Reserva realizada correctamente para " + nombreHuesped +
                            " del " + fechaInicio + " al " + fechaFin);
                } else {
                    JOptionPane.showMessageDialog(frame, "Lo sentimos, no hay habitaciones disponibles para ese periodo.");
                }
            }
        }

    private boolean verificarDisponibilidad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verificarDisponibilidad'");
    }

    // Método para cancelar la reserva de una habitación
    private void cancelarReserva() {
        JOptionPane.showMessageDialog(frame, "Reserva cancelada presencialmente o vía web.");
        // Aquí puedes implementar la lógica para cancelar la reserva de la habitación
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
