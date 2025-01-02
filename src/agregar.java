import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class agregar {
    private JTextField textID;
    private JTextField textNombre;
    private JTextField textPosicion;
    private JTextField textEquipo;
    private JTextField textEdad;
    private JButton okButton;
    private JButton menuButton;
    public JPanel PAgg;
    private JLabel LBLID;
    private JLabel LBLPOSICION;
    private JLabel LBLEQUIPO;
    private JLabel LBLEDAD;
    private JLabel LBLNOMBRE;

    public agregar() {
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
                //Clever cloud da estos datos para la conexion
                String url = "jdbc:mysql://uh8d0dknxf4ytrkw:YjRAeNhO8S5cWmeCUFOV@bkql20u4byqjrsrriwdd-mysql.services.clever-cloud.com:3306/bkql20u4byqjrsrriwdd";
                String user = "uh8d0dknxf4ytrkw";
                String password = "YjRAeNhO8S5cWmeCUFOV";

                String id = textID.getText().trim();
                String nombre = textNombre.getText().trim(); //Recoger los datos de las campos en agregar jugador+
                String posicion = textPosicion.getText().trim();
                String equipo = textEquipo.getText().trim();
                String edadTexto = textEdad.getText().trim();


                int edad; // para convertir la edad a int (porque asi le puse en la bdd)
                try { //Pero para evitar esto se puede cambiar en ld bdd
                    edad = Integer.parseInt(edadTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese una edad correcta");
                    return;
                }

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "INSERT INTO Jugadores (id, nombre, posicion, equipo, edad) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement BDD = conn.prepareStatement(query)) {
                        BDD.setString(1, id); //Aqui se agregan los valores que pongamos
                        BDD.setString(2, nombre);
                        BDD.setString(3, posicion);
                        BDD.setString(4, equipo);
                        BDD.setInt(5, edad);
                        BDD.executeUpdate(); //Para ejecutar el comando SQL
                        JOptionPane.showMessageDialog(null, "Jugador agregado correctamente.");
                        textID.setText(""); //Luego limpiamos otra vez los campos
                        textNombre.setText("");
                        textPosicion.setText("");
                        textEquipo.setText("");
                        textEdad.setText("");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al agregar jugador");
                }
            }
        });
    }
}
