package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.FacadeHoteleria;

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
    }

    // Método para mostrar el formulario de carga de huésped
    private void mostrarFormularioCargaHuesped() {
        // Aquí implementas la lógica para mostrar el formulario de carga de huésped
        JPanel panelFormulario = new JPanel();
        panelFormulario.add(new JLabel("Formulario de carga de huésped"));
        // Aquí agregas los componentes necesarios para el formulario
        this.add(panelFormulario);
        this.revalidate();
        this.repaint();
    }
}
