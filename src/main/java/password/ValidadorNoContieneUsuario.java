package password;

import domain.Roles.Usuario;
import exception.ContraseniaInvalidaException;

public class ValidadorNoContieneUsuario implements ValidadorContrasenia {

    public void validar(String usuario, String contrasenia) {
        if (contrasenia.contains(usuario)) {
            throw new ContraseniaInvalidaException("La contrasenia contiene al usuario");
        }
    }

}