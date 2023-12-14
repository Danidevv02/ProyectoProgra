
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

//Atributos de la clase
public class Usuario {
    private String nombre;
    private String apellidos;
    private String usuario;
    private String passwordHash;
    private String estado;
    private String correo;
    private String numeroPlaca;  // Nueva propiedad para el número de placa
    private Espacio espacioOcupado;
    
    // Constructor para crear un nuevo usuario
    public Usuario(String nombre, String apellidos, String usuario, String password, String estado, String correo, String numeroPlaca) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        setPassword(password);
        this.estado = estado;
        this.correo = correo;
        this.numeroPlaca = numeroPlaca;
    }
    
    //Verificar que la contraseña no sea nula o vacía
    private void setPassword(String password) {
        if (password != null && !password.isEmpty()) {
            this.passwordHash = hashPassword(password);
        } else {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }
    }
    
    // Verificar si la contraseña proporcionada es correcta
    public boolean verificarPassword(String password) {
        return passwordHash.equals(hashPassword(password));
    }

    public void desocuparEspacio() throws Exception {
        if (espacioOcupado != null) {
            espacioOcupado.desocupar();
            espacioOcupado = null;
        } else {
            throw new IllegalStateException("No hay espacio ocupado para desocupar.");
        }
    }

    public LocalDateTime getHoraIngresoEspacioOcupado() {
        if (espacioOcupado != null) {
            RegistroParqueo registro = buscarRegistroPorEspacio(espacioOcupado);
            if (registro != null) {
                return registro.getHoraIngreso();
            }
        }
        // Devolver null o lanzar una excepción
        return null;
    }

    private RegistroParqueo buscarRegistroPorEspacio(Espacio espacioOcupado2) {
        return null;
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
            throw new RuntimeException(e);
        }
    }

    // Getters

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

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public Espacio getEspacioOcupado() {
        return espacioOcupado;
    }

    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public void setEspacioOcupado(Espacio espacioOcupado) {
        this.espacioOcupado = espacioOcupado;
    }
}


