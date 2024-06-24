package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.FacadeHoteleria;

public class ViewWeb extends JPanel {
  FacadeHoteleria facade;

  public ViewWeb(FacadeHoteleria facade) {
    this.facade = facade;
    this.add(new JLabel("View Web"));
    this.setVisible(true);
  }
}
