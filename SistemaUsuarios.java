
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SistemaUsuarios extends JFrame {
    private Map<String, Usuario> usuarios;
    private Map<String, Espacio> espacios;
    private Parqueo parqueo;

    private JTextField loginUsuarioField;
    private JPasswordField loginPasswordField;
    
    public SistemaUsuarios() {
        setTitle("Sistema de Usuarios");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        usuarios = new HashMap<>();
        espacios = new HashMap<>();
        parqueo = new Parqueo(50);


        JPanel registroPanel = new JPanel();
        JPanel loginPanel = new JPanel();
        registroPanel.setLayout(new GridLayout(0, 2)); 
        loginPanel.setLayout(new GridLayout(0, 2)); 

        JTextField nombreField = new JTextField(20);
        JTextField apellidosField = new JTextField(20);
        JTextField usuarioField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField estadoField = new JTextField(20);
        JTextField correoField = new JTextField(20);
        JButton registroButton = new JButton("Registrar");

        loginUsuarioField = new JTextField(20);
        loginPasswordField = new JPasswordField(20);
        JButton loginButton = new JButton("Iniciar Sesión");

        JButton switchToLoginButton = new JButton("Iniciar Sesión");
        JButton switchToRegistroButton = new JButton("Registrar");

        

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
        registroPanel.add(new JLabel("")); 
        registroPanel.add(registroButton);
        registroPanel.add(new JLabel("")); 
        registroPanel.add(switchToLoginButton);

        loginPanel.add(new JLabel("Usuario:"));
        loginPanel.add(loginUsuarioField);
        loginPanel.add(new JLabel("Contraseña:"));
        loginPanel.add(loginPasswordField);
        loginPanel.add(new JLabel("")); 
        loginPanel.add(loginButton);
        loginPanel.add(new JLabel("")); 
        loginPanel.add(switchToRegistroButton);

        setLayout(new CardLayout());
        add(registroPanel, "registro");
        add(loginPanel, "login");

        JButton consultarUsuariosButton = new JButton("Consultar Usuarios");
        registroPanel.add(new JLabel("")); 
        registroPanel.add(consultarUsuariosButton);

        JButton agregarEspacioEspecialButton = new JButton("Agregar Espacio Especial");
        loginPanel.add(new JLabel("")); 
        loginPanel.add(agregarEspacioEspecialButton);

        JButton gestionarEspaciosButton = new JButton("Gestionar Espacios");
        
        loginPanel.add(gestionarEspaciosButton);

        JButton finalizarRegistroParqueoButton = new JButton("Finalizar Registro de Parqueo");
        loginPanel.add(finalizarRegistroParqueoButton);

        JButton crearRegistroParqueoButton = new JButton("Crear Registro de Parqueo");
        registroPanel.add(crearRegistroParqueoButton);

        JButton visualizarEspaciosButton = new JButton("Visualizar Espacios");
        loginPanel.add(new JLabel(""));
        loginPanel.add(visualizarEspaciosButton);

        JButton facturaButton = new JButton("Factura");
        loginPanel.add(new JLabel(""));
        loginPanel.add(facturaButton);

        visualizarEspaciosButton.addActionListener((ActionEvent e) -> visualizarEspacios());

        facturaButton.addActionListener((ActionEvent e) -> generarFactura());

        agregarEspacioEspecialButton.addActionListener((ActionEvent e) -> agregarEspacioEspecial());

        consultarUsuariosButton.addActionListener((ActionEvent e) -> consultarUsuarios());

        registroButton.addActionListener((ActionEvent e) -> registrarUsuario(nombreField, apellidosField, usuarioField, passwordField, estadoField, correoField));

        loginButton.addActionListener((ActionEvent e) -> autenticarUsuario());

        switchToLoginButton.addActionListener((ActionEvent e) -> cambiarFormulario("login"));

        switchToRegistroButton.addActionListener((ActionEvent e) -> cambiarFormulario("registro"));

        finalizarRegistroParqueoButton.addActionListener((ActionEvent e) -> finalizarRegistroParqueo());

        gestionarEspaciosButton.addActionListener((ActionEvent e) -> gestionarEspacios());

        crearRegistroParqueoButton.addActionListener((ActionEvent e) -> {
            try {
                crearRegistroParqueo();
            } catch (Exception ex) {
                Logger.getLogger(SistemaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        

        
    }

    private void generarFactura() {
        try {
            String horasStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad de horas:");
            int horas = Integer.parseInt(horasStr);
    
            double montoTotal = calcularTarifa(horas);
            double iva = montoTotal * 0.13; // IVA del 13%
            double totalPagar = montoTotal + iva;
    
            JOptionPane.showMessageDialog(null, "Monto total a pagar: " + totalPagar + " colones", "Factura Generada", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad de horas válida.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

   

    private double calcularTarifa(long horas) {
        return horas * 700; // 700 colones por hora
    }

    public void visualizarEspacios() {
        StringBuilder espaciosInfo = new StringBuilder("Información de Espacios:\n");
    
        for (Espacio espacio : espacios.values()) {
            espaciosInfo.append(espacio.toString()).append("\n");
        }
    
        // Aquí muetra la información en una ventana
        // Ejemplo de cómo mostrar en una ventana:
        JOptionPane.showMessageDialog(null, espaciosInfo.toString(), "Espacios Disponibles", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cambiarFormulario(String formulario) {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), formulario); 
    }

    private void registrarUsuario(JTextField nombreField, JTextField apellidosField, JTextField usuarioField, JPasswordField passwordField, JTextField estadoField, JTextField correoField) {
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();
        String usuario = usuarioField.getText();
        String password = new String(passwordField.getPassword());
        String estado = estadoField.getText();
        String correo = correoField.getText();
    
        // Agrega un número de placa ficticio o proporciona uno desde algún lugar
        String numeroPlaca = "ABC123";  // Ejemplo
    
        
        Usuario nuevoUsuario = new Usuario(nombre, apellidos, usuario, password, estado, correo, numeroPlaca);
        usuarios.put(usuario, nuevoUsuario);
    
        nombreField.setText("");
        apellidosField.setText("");
        usuarioField.setText("");
        passwordField.setText("");
        estadoField.setText("");
        correoField.setText("");
    
        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void autenticarUsuario() {
        String usuario = loginUsuarioField.getText();
        String password = new String(loginPasswordField.getPassword());

        Usuario usuarioAutenticado = usuarios.get(usuario);
        if (usuarioAutenticado != null && usuarioAutenticado.verificarPassword(password)) {
            JOptionPane.showMessageDialog(null, "Sesión iniciada", "Inicio de Sesión Exitoso", JOptionPane.INFORMATION_MESSAGE);
            loginUsuarioField.setText("");
            loginPasswordField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Por favor, inténtelo de nuevo.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            loginPasswordField.setText("");
        }
    }

    private void consultarUsuarios() {
        JFrame listaUsuariosFrame = new JFrame("Lista de Usuarios");
        JTextArea listaUsuariosTextArea = new JTextArea();
        listaUsuariosTextArea.setEditable(false);

        for (Usuario usuario : usuarios.values()) {
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

    private void agregarEspacioEspecial() {
        String tipo = JOptionPane.showInputDialog(null, "Tipo de parqueo especial:");
        try {
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Capacidad del parqueo especial:"));
            Espacio nuevoEspacio = new Espacio(espacios.size() + 1, tipo, capacidad);
            espacios.put(tipo, nuevoEspacio);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una capacidad válida.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearRegistroParqueo() throws Exception {
        String numeroPlaca = JOptionPane.showInputDialog(null, "Número de Placa:");
        String nombreUsuario = JOptionPane.showInputDialog(null, "Nombre de Usuario:");
        Usuario cliente = usuarios.get(nombreUsuario);
        Espacio espacio = espacios.get("Espacio 1"); 
    
        if (cliente != null && espacio != null) {
            espacio.ocuparEspacio(cliente);
            
            // Obtener el registro asociado al espacio
            RegistroParqueo registro = parqueo.getRegistroParqueoPorEspacio(espacio);
            
            // Si no hay registro, crear uno nuevo
            if (registro == null) {
                LocalDateTime horaIngreso = LocalDateTime.now();
                registro = parqueo.crearRegistroParqueo(numeroPlaca, cliente, espacio, horaIngreso);
            }
            
            JOptionPane.showMessageDialog(null, "Registro de parqueo creado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay espacios disponibles o el usuario no está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizarRegistroParqueo() {
        int idEspacio = Integer.parseInt(JOptionPane.showInputDialog(null, "ID del Espacio Ocupado:"));
        RegistroParqueo registro = obtenerRegistroPorEspacio(idEspacio);
        if (registro != null) {
            registro.finalizarRegistro();
            Factura factura = registro.generarFactura();
            if (factura != null) {
                JOptionPane.showMessageDialog(null, "Factura generada:\n" + factura.toString(), "Factura Generada", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Registro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private RegistroParqueo obtenerRegistroPorEspacio(int idEspacio) {
        for (RegistroParqueo registro : parqueo.getRegistros()) {
            if (registro.getEspacio().getIdentificador() == idEspacio) {
                return registro;
            }
        }
        return null;
    }

    private RegistroParqueo buscarRegistroPorEspacio(Espacio espacio) {
        for (RegistroParqueo registro : parqueo.getRegistros()) {
            if (registro.getEspacio() == espacio) {
                return registro;
            }
        }
        return null;
    }

    public void obtenerInfoEspacio(Espacio espacio) {
        System.out.println("Información del Espacio:");

        try {
            if (espacio != null) {
                System.out.println("Identificador: " + espacio.getIdentificador());
                System.out.println("Tipo: " + espacio.getTipo());
                System.out.println("Capacidad: " + espacio.getCapacidad());
                System.out.println("Espacios disponibles: " + espacio.getEspaciosDisponibles());

                RegistroParqueo registro = buscarRegistroPorEspacio(espacio);

                if (registro != null) {
                    System.out.println("Estado: Ocupado");
                    System.out.println("Nombre del usuario: " + registro.getCliente().getNombre());
                    System.out.println("Número de placa: " + registro.getNumeroPlaca());
                    System.out.println("Tiempo transcurrido: " + calcularTiempoTranscurrido(registro.getHoraIngreso()));
                } else {
                    System.out.println("Estado: Disponible");
                }
            } else {
                System.out.println("Espacio no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener información del espacio: " + e.getMessage());
        }
    }

    private String calcularTiempoTranscurrido(LocalDateTime horaIngreso) {
        LocalDateTime horaActual = LocalDateTime.now();
        long minutosTranscurridos = ChronoUnit.MINUTES.between(horaIngreso, horaActual);
        long horas = minutosTranscurridos / 60;
        long minutos = minutosTranscurridos % 60;
        return horas + " horas y " + minutos + " minutos";
    }

    

    private void gestionarEspacios() {
        JFrame gestionarEspaciosFrame = new JFrame("Gestionar Espacios");
        gestionarEspaciosFrame.setLayout(new GridLayout(0, 2));

        JTextField idEspacioField = new JTextField(5);
        JTextField tipoEspacioField = new JTextField(20);
        JTextField capacidadEspacioField = new JTextField(5);
        JButton agregarEspacioButton = new JButton("Agregar Espacio");

                gestionarEspaciosFrame.add(new JLabel("ID del Espacio:"));
        gestionarEspaciosFrame.add(idEspacioField);
        gestionarEspaciosFrame.add(new JLabel("Tipo del Espacio:"));
        gestionarEspaciosFrame.add(tipoEspacioField);
        gestionarEspaciosFrame.add(new JLabel("Capacidad del Espacio:"));
        gestionarEspaciosFrame.add(capacidadEspacioField);
        gestionarEspaciosFrame.add(new JLabel("")); 
        gestionarEspaciosFrame.add(agregarEspacioButton);

        agregarEspacioButton.addActionListener((ActionEvent e) -> {
            try {
                int idEspacio = Integer.parseInt(idEspacioField.getText());
                String tipoEspacio = tipoEspacioField.getText();
                int capacidadEspacio = Integer.parseInt(capacidadEspacioField.getText());

                Espacio nuevoEspacio = new Espacio(idEspacio, tipoEspacio, capacidadEspacio);
                espacios.put(tipoEspacio, nuevoEspacio);

                idEspacioField.setText("");
                tipoEspacioField.setText("");
                capacidadEspacioField.setText("");

                JOptionPane.showMessageDialog(null, "Espacio agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese datos válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        });

        gestionarEspaciosFrame.setSize(400, 200);
        gestionarEspaciosFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaUsuarios().setVisible(true));
    }
    
}




