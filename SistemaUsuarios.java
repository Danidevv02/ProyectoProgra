import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaUsuarios extends JFrame {
    private Usuario[] usuarios = new Usuario[100]; // Array para almacenar usuarios, solo se pueden 100 usuarios
    private int usuariosRegistrados = 0; // Contador de usuarios registrados

    public SistemaUsuarios() {
        setTitle("Sistema de Usuarios");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear paneles para registro e inicio de sesión
        JPanel registroPanel = new JPanel();
        JPanel loginPanel = new JPanel();
        registroPanel.setLayout(new GridLayout(0, 2)); // Dos columnas
        loginPanel.setLayout(new GridLayout(0, 2)); // Dos columnas

        // Componentes para el registro de usuarios
        JTextField nombreField = new JTextField(20);
        JTextField apellidosField = new JTextField(20);
        JTextField usuarioField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField estadoField = new JTextField(20);
        JTextField correoField = new JTextField(20);
        JButton registroButton = new JButton("Registrar");

        // Componentes para el inicio de sesión
        JTextField loginUsuarioField = new JTextField(20);
        JPasswordField loginPasswordField = new JPasswordField(20);
        JButton loginButton = new JButton("Iniciar Sesión");

        // Botón para cambiar al formulario de inicio de sesión desde el registro
        JButton switchToLoginButton = new JButton("Iniciar Sesión");

        // Botón para cambiar al formulario de registro desde el inicio de sesión
        JButton switchToRegistroButton = new JButton("Registrar");

        // Agregar componentes al panel de registro
        registroPanel.add(new JLabel("Nombre:"));
        registroPanel.add(nombreField);
        registroPanel.add(new JLabel("Apellidos:"));
        registroPanel.add(apellidosField);
        registroPanel.add(new JLabel("Usuario:"));
        registroPanel.add(usuarioField);
        registroPanel.add(new JLabel("Contraseña:"));
        registroPanel.add(passwordField);
        registroPanel.add(new JLabel("Estado (Activo/Inactivo):"));
        registroPanel.add(estadoField);
        registroPanel.add(new JLabel("Correo:"));
        registroPanel.add(correoField);
        registroPanel.add(new JLabel("")); // Espacio en blanco para alinear el botón
        registroPanel.add(registroButton);
        registroPanel.add(new JLabel("")); // Espacio en blanco
        registroPanel.add(switchToLoginButton);        

        // Agregar componentes al panel de inicio de sesión
        loginPanel.add(new JLabel("Usuario:"));
        loginPanel.add(loginUsuarioField);
        loginPanel.add(new JLabel("Contraseña:"));
        loginPanel.add(loginPasswordField);
        loginPanel.add(new JLabel("")); // Espacio en blanco para alinear el botón
        loginPanel.add(loginButton);
        loginPanel.add(new JLabel("")); // Espacio en blanco
        loginPanel.add(switchToRegistroButton);

        // Agregar los paneles al marco con el CardLayout
        setLayout(new CardLayout());
        add(registroPanel, "registro");
        add(loginPanel, "login");

        // Acción al presionar el botón de registro
        registroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Capturar los datos del usuario y registrarlos

                // Limpia los campos después del registro
                nombreField.setText("");
                apellidosField.setText("");
                usuarioField.setText("");
                passwordField.setText("");
                estadoField.setText("");
                correoField.setText("");
            }
        });

        // Acción al presionar el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Capturar los datos del inicio de sesión
                // Verificar las credenciales del usuario y realizar la autenticación

                // Limpia los campos después del inicio de sesión
                loginUsuarioField.setText("");
                loginPasswordField.setText("");
            }
        });

        // Acción al presionar el botón para cambiar al formulario de inicio de sesión desde el registro
        switchToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) getContentPane().getLayout();
                layout.show(getContentPane(), "login"); // Mostrar el panel de inicio de sesión
            }
        });

        // Acción al presionar el botón para cambiar al formulario de registro desde el inicio de sesión
        switchToRegistroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) getContentPane().getLayout();
                layout.show(getContentPane(), "registro"); // Mostrar el panel de registro
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SistemaUsuarios().setVisible(true);
            }
        });
    }
}




