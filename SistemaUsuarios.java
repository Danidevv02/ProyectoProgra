/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaUsuarios extends JFrame {
    private Usuario[] usuarios = new Usuario[100];
    private int usuariosRegistrados = 0;
    
    // Nuevo: Arreglo de espacios
    private Espacio[] espacios = new Espacio[100];
    private int espaciosRegistrados = 0;

    private NecesidadesEspeciales necesidadesEspeciales;


    private JTextField loginUsuarioField;
    private JPasswordField loginPasswordField;

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
        loginUsuarioField = new JTextField(20);
        loginPasswordField = new JPasswordField(20);
        JButton loginButton = new JButton("Iniciar Sesión");

        // Botón para cambiar al formulario de inicio de sesión desde el registro
        JButton switchToLoginButton = new JButton("Iniciar Sesión");

        // Botón para cambiar al formulario de registro desde el inicio de sesión
        JButton switchToRegistroButton = new JButton("Registrar");
        
        //Inicializar la clase para manejar necesidades especiales
        necesidadesEspeciales = new NecesidadesEspeciales(espacios, espaciosRegistrados);

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

        // Botón para consultar usuarios
        JButton consultarUsuariosButton = new JButton("Consultar Usuarios");
        registroPanel.add(new JLabel("")); // Espacio en blanco
        registroPanel.add(consultarUsuariosButton);

         //Acción al presionar el botón para agregar espacio especial
         JButton agregarEspacioEspecialButton = new JButton("Agregar Espacio Especial");
         loginPanel.add(new JLabel("")); // Espacio en blanco
         loginPanel.add(agregarEspacioEspecialButton);

        // Acción al presionar el botón de consultar usuarios
        consultarUsuariosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar la lista de usuarios registrados en una nueva ventana o diálogo
                JFrame listaUsuariosFrame = new JFrame("Lista de Usuarios");
                JTextArea listaUsuariosTextArea = new JTextArea();
                listaUsuariosTextArea.setEditable(false);

                for (int i = 0; i < usuariosRegistrados; i++) {
                    Usuario usuario = usuarios[i];
                    listaUsuariosTextArea.append("Nombre: " + usuario.getNombre() + "\n");
                    listaUsuariosTextArea.append("Apellidos: " + usuario.getApellidos() + "\n");
                    listaUsuariosTextArea.append("Usuario: " + usuario.getUsuario() + "\n");
                    listaUsuariosTextArea.append("Estado: " + usuario.getEstado() + "\n");
                    listaUsuariosTextArea.append("Correo: " + usuario.getCorreo() + "\n");
                    listaUsuariosTextArea.append("----------------------------------------\n");
                }

                JScrollPane scrollPane = new JScrollPane(listaUsuariosTextArea);
                listaUsuariosFrame.add(scrollPane);
                listaUsuariosFrame.setSize(400, 300);
                listaUsuariosFrame.setVisible(true);
            }
        });

        // Acción al presionar el botón de registro
        registroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Capturar los datos del usuario y registrarlos
                String nombre = nombreField.getText();
                String apellidos = apellidosField.getText();
                String nuevoUsuario = usuarioField.getText();
                String newPassword = new String(passwordField.getPassword());
                String estado = estadoField.getText();
                String correo = correoField.getText();

                // Crear un nuevo usuario
                Usuario nuevoUsuarioObj = new Usuario(nombre, apellidos, nuevoUsuario, newPassword, estado, correo);

                // Agregar el nuevo usuario al array de usuarios
                if (usuariosRegistrados < usuarios.length) {
                    usuarios[usuariosRegistrados] = nuevoUsuarioObj;
                    usuariosRegistrados++;

                    // Limpia los campos después del registro
                    nombreField.setText("");
                    apellidosField.setText("");
                    usuarioField.setText("");
                    passwordField.setText("");
                    estadoField.setText("");
                    correoField.setText("");
                } else {
                    // Manejar el caso de superar la capacidad máxima de usuarios
                    JOptionPane.showMessageDialog(null, "Se ha alcanzado la capacidad máxima de usuarios.",
                            "Capacidad Máxima Alcanzada", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Acción al presionar el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Capturar los datos del inicio de sesión
                String usuario = loginUsuarioField.getText();
                String password = new String(loginPasswordField.getPassword());

                // Intentar autenticar al usuario
                Usuario usuarioAutenticado = autenticarUsuario(usuario, password);

                if (usuarioAutenticado != null) {
                    // Usuario autenticado
                    // Muestra un mensaje de "Sesión iniciada"
                    JOptionPane.showMessageDialog(null, "Sesión iniciada", "Inicio de Sesión Exitoso", JOptionPane.INFORMATION_MESSAGE);
        
                    loginUsuarioField.setText("");
                    loginPasswordField.setText("");
                } else {
                    // Manejo del caso de credenciales incorrectas
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Por favor, inténtelo de nuevo.",
                            "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                    loginPasswordField.setText("");
                }
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
        
        // Acción al presionar el botón para gestionar espacios
        JButton gestionarEspaciosButton = new JButton("Gestionar Espacios");
        loginPanel.add(new JLabel("")); // Espacio en blanco
        loginPanel.add(gestionarEspaciosButton);

        // Acción al presionar el botón para gestionar espacios
        gestionarEspaciosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestra un formulario para gestionar espacios
                gestionarEspacios();
            }
        });   

        // Acción al presionar el botón para agregar espacio especial
        
        agregarEspacioEspecialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestra un formulario para agregar espacio especial
                agregarEspacioEspecial();
            }
        });
    }

    // Método para autenticar un usuario
        private Usuario autenticarUsuario(String usuario, String password) {
            for (int i = 0; i < usuariosRegistrados; i++) {
            if (usuarios[i].getUsuario().equals(usuario)) {
                if (usuarios[i].verificarPassword(password)) {
                    return usuarios[i];
                }
            }
        }
        return null;
    }
    // Método para gestionar espacios especiales
    private void agregarEspacioEspecial() {
        // Mostrar formulario para registrar parqueo especial
        String tipo = JOptionPane.showInputDialog(null, "Tipo de parqueo especial:");
        try {
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Capacidad del parqueo especial:"));
            necesidadesEspeciales.registrarParqueoNecesidadesEspeciales(tipo, capacidad);
        } catch (NumberFormatException ex) {
            // Manejar el caso de entrada no válida
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una capacidad válida.",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Método para gestionar espacios
    private void gestionarEspacios() {
        JFrame gestionarEspaciosFrame = new JFrame("Gestionar Espacios");
        gestionarEspaciosFrame.setLayout(new GridLayout(0, 2));

        // Componentes para gestionar espacios
        JTextField idEspacioField = new JTextField(5);
        JTextField tipoEspacioField = new JTextField(20);
        JTextField capacidadEspacioField = new JTextField(5);
        JButton agregarEspacioButton = new JButton("Agregar Espacio");

        // Agregar componentes al formulario de gestión de espacios
        gestionarEspaciosFrame.add(new JLabel("ID del Espacio:"));
        gestionarEspaciosFrame.add(idEspacioField);
        gestionarEspaciosFrame.add(new JLabel("Tipo del Espacio:"));
        gestionarEspaciosFrame.add(tipoEspacioField);
        gestionarEspaciosFrame.add(new JLabel("Capacidad del Espacio:"));
        gestionarEspaciosFrame.add(capacidadEspacioField);
        gestionarEspaciosFrame.add(new JLabel("")); // Espacio en blanco
        gestionarEspaciosFrame.add(agregarEspacioButton);

        // Acción al presionar el botón para agregar espacio
        agregarEspacioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Capturar los datos del nuevo espacio
                try {
                    int idEspacio = Integer.parseInt(idEspacioField.getText());
                    String tipoEspacio = tipoEspacioField.getText();
                    int capacidadEspacio = Integer.parseInt(capacidadEspacioField.getText());

                    // Crear un nuevo espacio
                    Espacio nuevoEspacio = new Espacio(idEspacio, tipoEspacio, capacidadEspacio);

                    // Agregar el nuevo espacio al array de espacios
                    if (espaciosRegistrados < espacios.length) {
                        espacios[espaciosRegistrados] = nuevoEspacio;
                        espaciosRegistrados++;

                        // Limpia los campos después de agregar el espacio
                        idEspacioField.setText("");
                        tipoEspacioField.setText("");
                        capacidadEspacioField.setText("");

                        // Muestra un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "Espacio agregado correctamente",
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Manejar el caso de superar la capacidad máxima de espacios
                        JOptionPane.showMessageDialog(null, "Se ha alcanzado la capacidad máxima de espacios.",
                                "Capacidad Máxima Alcanzada", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Manejar el caso de entrada no válida
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese datos válidos.",
                            "Error de entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gestionarEspaciosFrame.setSize(400, 200);
        gestionarEspaciosFrame.setVisible(true);
    }
        
        
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SistemaUsuarios().setVisible(true);
            }
        });
    }
}


