package password;

import domain.Roles.Usuario;
import exception.ContraseniaInvalidaException;

public class ValidadorNoContieneUsuario implements ValidadorContrasenia {

    public void validar(Usuario unUsuario) {
        if (unUsuario.contraseniaContieneUsuario()){
            throw new ContraseniaInvalidaException("La contrasenia es demasiado corta");
        }
    }

}