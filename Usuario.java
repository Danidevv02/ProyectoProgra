package proyectoprogra;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String usuario;
    private String passwordHash;
    private String estado;
    private String correo;
    private Espacio espacioOcupado;

    public Usuario(String nombre, String apellidos, String usuario, String password, String estado, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        setPassword(password);
        this.estado = estado;
        this.correo = correo;
    }
    
    private void setPassword(String password) {
        if (verificarPassword(password)) {
            passwordHash = hashPassword(password);
        } else {
            // Manejar contraseña no válida, lanzar una excepción o mostrar un mensaje de error
        }
    }
    
    public boolean verificarPassword(String password) {
        String hashedPassword = hashPassword(password);
        return passwordHash.equals(hashedPassword);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEstado() {
        return estado;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    

    private String hashPassword(String password) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);

        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
  }
    public void desocuparEspacio() {
    if (espacioOcupado instanceof Espacio) {
        Espacio espacio = (Espacio) espacioOcupado;
        espacio.desocupar();
        espacioOcupado = null;
    } else {
        System.out.println("No hay espacio ocupado para desocupar.");
    }
}
}

