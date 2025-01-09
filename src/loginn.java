import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class loginn {
    public JPanel PLogg;
    public JTextField textField1;
    public JPasswordField passwordField1;
    public JButton okButton;


    public loginn() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://uh8d0dknxf4ytrkw:YjRAeNhO8S5cWmeCUFOV@bkql20u4byqjrsrriwdd-mysql.services.clever-cloud.com:3306/bkql20u4byqjrsrriwdd";
                String user = "uh8d0dknxf4ytrkw";
                String password = "YjRAeNhO8S5cWmeCUFOV";

                String correo = textField1.getText();
                String contrasenia = new String(passwordField1.getPassword());

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM Usuarios WHERE Usuario = ? AND Contrasenia = ?";
                    try (PreparedStatement BDD = conn.prepareStatement(query)) {
                        BDD.setString(1, correo);
                        BDD.setString(2, contrasenia);

                        try (ResultSet busquedaMySQL = BDD.executeQuery()) {
                            if (busquedaMySQL.next()) {
                                JOptionPane.showMessageDialog(null, "Credenciales correctas, ingresando...");

                                JFrame frame = new JFrame("Menu");
                                frame.setContentPane(new menu().PMenu);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(500, 300);
                                frame.setPreferredSize(new Dimension(300, 300));
                                frame.setLocationRelativeTo(null);
                                frame.pack();
                                frame.setVisible(true);

                            } else {
                                JOptionPane.showMessageDialog(null, "Credenciales incorrectas, intentalo de nuevo");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en Clever Cloud");
                }
            }
        });
    }
}
