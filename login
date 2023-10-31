package proyectoprogra;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioAuthenticator {
    private Usuario[] usuarios = new Usuario[100];
    private int usuariosRegistrados = 0;

    public Usuario autenticarUsuario(String usuario, String password) {
        for (int i = 0; i < usuariosRegistrados; i++) {
            if (usuarios[i].getUsuario().equals(usuario)) {
                
                if (verificarPassword(usuarios[i], password)) {
                    return usuarios[i];
                }
            }
        }
        return null; 
    }

    private boolean verificarPassword(Usuario usuario, String password) {
       String hashedPassword = usuario.getPassword();
      return usuario.getPassword().equals(hashPassword(password));
    }
