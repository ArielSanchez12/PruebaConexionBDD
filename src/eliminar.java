import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class eliminar {
    private JTextField textField1;
    private JButton eliminarButton;
    private JButton menuButton;
    public JPanel PElim;

    public eliminar() {
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://uh8d0dknxf4ytrkw:YjRAeNhO8S5cWmeCUFOV@bkql20u4byqjrsrriwdd-mysql.services.clever-cloud.com:3306/bkql20u4byqjrsrriwdd";
                String user = "uh8d0dknxf4ytrkw";
                String password = "YjRAeNhO8S5cWmeCUFOV";
                String idJugador = textField1.getText(); //Obtenemos el ID del que se va a eliminar

                if (idJugador.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un ID"); //Para que no quede vacio
                    return;
                }

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "DELETE FROM Jugadores WHERE ID = ?"; //La consulta en SQL que busca el ID
                    try (PreparedStatement BDD = conn.prepareStatement(query)) {
                        BDD.setInt(1, Integer.parseInt(idJugador)); //Establece el ID y lo pasa a int porque asi le puse en la BDD

                        int rowsAffected = BDD.executeUpdate(); //Se hace la consulta
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Jugador eliminado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "No existe ese ID");
                        }

                        textField1.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el jugador");
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Menu"); //Mas de lo mismo
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
