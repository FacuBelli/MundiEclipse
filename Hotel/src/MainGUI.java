import javax.swing.*;

import habitacion.Habitacion;
import habitacion.state.Disponible;
import reserva.Reserva;
import reserva.state.IReservaState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import usuario.Huesped; 

public class MainGUI {
    private JFrame frame;

    public MainGUI() {

        String[] roles = {"Rol1", "Rol2", "Rol3", "Rol4"};
        String rolSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione su rol",
                "Seleccionar Rol",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roles,
                roles[0]
        );

        if (rolSeleccionado == null) {
            // Si el usuario cancela la selección de rol, cerrar la aplicación
            System.exit(0);
        }

        if (rolSeleccionado == "Rol1"){
            frame = new JFrame("Sistema de Gestión Hotelera");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

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

        /*JButton btnCargarHabitaciones = new JButton("Cargar Habitaciones");
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
        panel.add(btnReservar);*/

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





private void reservarHabitacion() {
    // Mostrar un formulario para ingresar los datos de la reserva
    JTextField txtNombreHuesped = new JTextField(20);
    JTextField txtFechaInicio = new JTextField(10);
    JTextField txtFechaFin = new JTextField(10);

    // Supongamos que tenemos una lista de habitaciones disponibles
    String[] habitacionesDisponibles = {"101", "102", "103"};  // Esto debería obtenerse de tu sistema
    JComboBox<String> comboHabitacion = new JComboBox<>(habitacionesDisponibles);

    // Supongamos que tenemos estados posibles para una reserva
    String[] estadosReserva = {"Activo", "Cancelado"};
    JComboBox<String> comboEstado = new JComboBox<>(estadosReserva);

    JPanel panelFormulario = new JPanel(new GridLayout(0, 1));
    panelFormulario.add(new JLabel("Nombre del huésped:"));
    panelFormulario.add(txtNombreHuesped);
    panelFormulario.add(new JLabel("Fecha de inicio (dd/mm/aaaa):"));
    panelFormulario.add(txtFechaInicio);
    panelFormulario.add(new JLabel("Fecha de fin (dd/mm/aaaa):"));
    panelFormulario.add(txtFechaFin);
    panelFormulario.add(new JLabel("Habitación:"));
    panelFormulario.add(comboHabitacion);
    panelFormulario.add(new JLabel("Estado de la Reserva:"));
    panelFormulario.add(comboEstado);

    int opcion = JOptionPane.showConfirmDialog(frame, panelFormulario, "Reservar Habitación",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (opcion == JOptionPane.OK_OPTION) {
        String nombreHuesped = txtNombreHuesped.getText();
        String fechaInicioStr = txtFechaInicio.getText();
        String fechaFinStr = txtFechaFin.getText();
        String habitacionSeleccionada = (String) comboHabitacion.getSelectedItem();
        String estadoSeleccionado = (String) comboEstado.getSelectedItem();

        // Conversión de fechas
        Date fechaInicio = null;
        Date fechaFin = null;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            fechaInicio = formatoFecha.parse(fechaInicioStr);
            fechaFin = formatoFecha.parse(fechaFinStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(frame, "Formato de fecha incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        /* 
        // Verificar disponibilidad de habitaciones
        boolean habitacionDisponible = verificarDisponibilidad(fechaInicio, fechaFin, habitacionSeleccionada);

        if (habitacionDisponible) {
            // Crear una instancia de Huesped y Habitacion
            Huesped huesped = new Huesped(nombre);  // Asumiendo que tienes un constructor adecuado
            Habitacion habitacion = new Habitacion(tipo);  // Asumiendo que tienes un constructor adecuado

            // Crear una instancia del estado (Aquí deberías tener lógica para convertir el string en un estado real)
            IReservaState estado = new Activo();  // Esto es un ejemplo, ajusta según tus estados

            // Crear y guardar la reserva
            Reserva nuevaReserva = new Reserva(huesped, habitacion, fechaInicio, fechaFin, estado, 0.0);
            guardarReserva(nuevaReserva);
            JOptionPane.showMessageDialog(frame, "Reserva realizada correctamente para " + nombreHuesped +
                    " del " + fechaInicioStr + " al " + fechaFinStr);
        } else {
            JOptionPane.showMessageDialog(frame, "Lo sentimos, no hay habitaciones disponibles para ese periodo.");
        } */
    } 
}

private boolean verificarDisponibilidad(Date fechaInicio, Date fechaFin, String habitacionSeleccionada) {
    // Aquí deberías implementar la lógica para verificar la disponibilidad de habitaciones
    // Por simplicidad, este método siempre devuelve true
    return true;
}

private void guardarReserva(Reserva reserva) {
    String nombreArchivo = "reservas.txt";

    try {
        FileWriter fw = new FileWriter(nombreArchivo, true);
        BufferedWriter bw = new BufferedWriter(fw);

        // Crear una cadena con los datos de la reserva
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String datosReserva = reserva.getHuesped().getNombre() + "," +
                              reserva.getHabitacion().getIdentificador() + "," +
                              formatoFecha.format(reserva.getFechaInicio()) + "," +
                              formatoFecha.format(reserva.getFechaFin()) + "," +
                              reserva.getEstado().getClass().getSimpleName();

        // Escribir los datos de la reserva en el archivo
        bw.write(datosReserva);
        bw.newLine();  // Agregar nueva línea para separar cada reserva

        // Cerrar el BufferedWriter
        bw.close();

    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(frame, "Error al guardar la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
    }
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
