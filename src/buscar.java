import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class buscar {
    private JTextField textIDB;
    private JButton okButton;
    private JButton menuButton;
    public JPanel PBs;
    private JLabel LBLResultado;

    public buscar() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Mas de lo mismo
                String url = "jdbc:mysql://uh8d0dknxf4ytrkw:YjRAeNhO8S5cWmeCUFOV@bkql20u4byqjrsrriwdd-mysql.services.clever-cloud.com:3306/bkql20u4byqjrsrriwdd";
                String user = "uh8d0dknxf4ytrkw";
                String password = "YjRAeNhO8S5cWmeCUFOV";
                String idBuscar = textIDB.getText();

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    //La consulta con SQL
                    String query = "SELECT * FROM Jugadores WHERE ID = ?";
                    try (PreparedStatement BDD = conn.prepareStatement(query)) {
                        BDD.setString(1, idBuscar);

                        try (ResultSet resultado = BDD.executeQuery()) {
                            if (resultado.next()) { //Se verifica que se encuentra el resultado y lo guarda en una variable
                                StringBuilder detalles = new StringBuilder("<html>");
                                detalles.append("ID: ").append(resultado.getInt("ID"))
                                        .append("Nombre: ").append(resultado.getString("Nombre"))
                                        .append("Posición: ").append(resultado.getString("Posicion"))
                                        .append("Equipo: ").append(resultado.getString("Equipo"))
                                        .append("Edad: ").append(resultado.getInt("Edad"));
                                detalles.append("</html>");
                                LBLResultado.setText(detalles.toString()); //Se muestra

                            } else {
                                LBLResultado.setText("Jugador no encontrado");
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar jugador");
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Para volver al menu
                JFrame frame = new JFrame("Menu");
                frame.setContentPane(new menu().PMenu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(500, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
