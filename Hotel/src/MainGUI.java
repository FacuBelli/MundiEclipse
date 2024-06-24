import javax.swing.*;

import habitacion.*;
import habitacion.state.Disponible;
import habitacion.state.IHabitacionState;
import habitacion.tipo.IHabitacionTipo;
import reserva.Reserva;
import reserva.state.IReservaState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import usuario.Huesped; 

public class MainGUI {
    private JFrame frame;
    private List<Habitacion> habitaciones;

    public MainGUI() {

        habitaciones = new ArrayList<>();
        

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

            private void cancelarReserva() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'cancelarReserva'");
            }
        });
        panel.add(btnCancelar);

        JButton btnCargarHabitaciones = new JButton("Cargar Habitaciones");
        btnCargarHabitaciones.addActionListener(new ManejadorCargarHabitaciones());

        JButton btnBuscarHabitaciones = new JButton("Buscar Habitaciones");
        btnBuscarHabitaciones.addActionListener(new ManejadorBuscarHabitaciones());

        panel.add(btnCargarHabitaciones);
        panel.add(btnBuscarHabitaciones);



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





private class ManejadorCargarHabitaciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarFormularioCargaHabitacion();
        }
    }

    private class ManejadorBuscarHabitaciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarDisponibilidadHabitaciones();
        }
    }

    // Método para mostrar el formulario de carga de habitaciones
    // Método para mostrar el formulario de carga de habitaciones
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

        int opcion = JOptionPane.showConfirmDialog(frame, panelFormulario, "Cargar Habitación",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String tipoStr = (String) comboTipo.getSelectedItem();
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            double tarifa = Double.parseDouble(txtTarifa.getText());
            boolean balcon = chkBalcon.isSelected();
            String descripcion = txtDescripcion.getText();

            IHabitacionTipo tipo = new TipoHabitacion(tipoStr);
            IHabitacionState estado = new Disponible(null); // Por defecto, asignar un estado disponible

            Habitacion nuevaHabitacion = new Habitacion();
            habitaciones.add(nuevaHabitacion);

            guardarHabitacion(nuevaHabitacion);
            JOptionPane.showMessageDialog(frame, "Habitación cargada correctamente.");
        }
    }

    // Método para guardar una habitación en un archivo
    private void guardarHabitacion(Habitacion habitacion) {
        String nombreArchivo = "habitaciones.txt";

        try {
            FileWriter fw = new FileWriter(nombreArchivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String datosHabitacion = habitacion.getIdentificador() + "," +
                                     habitacion.getTipo().getClass().getSimpleName() + "," +
                                     habitacion.getEstado().getClass().getSimpleName() + "," +
                                     habitacion.getCapacidad() + "," +
                                     habitacion.getTarifa() + "," +
                                     habitacion.isBalcon() + "," +
                                     habitacion.getDescripcion();

            bw.write(datosHabitacion);
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al guardar la habitación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para mostrar la disponibilidad de habitaciones
    private void mostrarDisponibilidadHabitaciones() {
        cargarHabitacionesDesdeArchivo();  // Cargar habitaciones desde el archivo

        StringBuilder mensaje = new StringBuilder("Habitaciones disponibles:\n");

        for (Habitacion habitacion : habitaciones) {
            mensaje.append("Identificador: ").append(habitacion.getIdentificador())
                   .append(", Tipo: ").append(habitacion.getTipo().getClass().getSimpleName())
                   .append(", Estado: ").append(habitacion.getEstado().getClass().getSimpleName())
                   .append(", Capacidad: ").append(habitacion.getCapacidad())
                   .append(", Tarifa: ").append(habitacion.getTarifa())
                   .append(", Balcón: ").append(habitacion.isBalcon())
                   .append(", Descripción: ").append(habitacion.getDescripcion())
                   .append("\n");
        }

        JOptionPane.showMessageDialog(frame, mensaje.toString(), "Disponibilidad de Habitaciones", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para cargar habitaciones desde el archivo
    private void cargarHabitacionesDesdeArchivo() {
        String nombreArchivo = "habitaciones.txt";
        habitaciones.clear();  // Limpiar la lista antes de cargar

        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int identificador = Integer.parseInt(datos[0]);
                IHabitacionTipo tipo = new TipoHabitacion(datos[1]);  // Ajustar según tu implementación de IHabitacionTipo
                IHabitacionState estado = new Disponible(); // Ajustar según tu implementación de IHabitacionState
                int capacidad = Integer.parseInt(datos[3]);
                double tarifa = Double.parseDouble(datos[4]);
                boolean balcon = Boolean.parseBoolean(datos[5]);
                String descripcion = datos[6];

                Habitacion habitacion = new Habitacion(identificador, tipo, estado, capacidad, tarifa, balcon, descripcion);
                habitaciones.add(habitacion);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al cargar las habitaciones.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
