import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class ver {
    public JButton menuButton;
    public JLabel Resultados;
    public JPanel PVer;
    private JButton okButton;

    public ver() {
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://uh8d0dknxf4ytrkw:YjRAeNhO8S5cWmeCUFOV@bkql20u4byqjrsrriwdd-mysql.services.clever-cloud.com:3306/bkql20u4byqjrsrriwdd";
                String user = "uh8d0dknxf4ytrkw";
                String password = "YjRAeNhO8S5cWmeCUFOV";

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM Jugadores";
                    try (PreparedStatement BDD = conn.prepareStatement(query);
                         ResultSet resultado = BDD.executeQuery()) {

                        StringBuilder resultados = new StringBuilder("<html>");
                        while (resultado.next()) {
                            resultados.append("ID: ").append(resultado.getInt("ID")) //Almacenar los datos en una variable
                                    .append(", Nombre: ").append(resultado.getString("Nombre"))
                                    .append(", Posición: ").append(resultado.getString("Posicion"))
                                    .append(", Equipo: ").append(resultado.getString("Equipo"))
                                    .append(", Edad: ").append(resultado.getInt("Edad"))
                                    .append("<br>");
                        }
                        resultados.append("</html>");
                        Resultados.setText(resultados.toString()); //Mostrar los datos
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en Clever Cloud");
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
