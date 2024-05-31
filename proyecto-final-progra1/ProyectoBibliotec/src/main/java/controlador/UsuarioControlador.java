package controlador;

import modelo.Usuario;
import repositorio.UsuarioDAO;

import java.util.List;

public class UsuarioControlador extends AbstractCrud<Usuario, Integer> {

    private UsuarioDAO repository = new UsuarioDAO();

    @Override
    public List<Usuario> executeReadAll() {
        return repository.readAll();
    }
    //tiene lista dinamica=cache(almacena datos temporales) la información, recuperacion de datos en memoria. 
    //crece a mayor dato 
    //no es necesario recurrir a base ya que lo tenemos cache 
    @Override
    public Usuario executeCreate(Usuario entidad) {
        repository.save(entidad);
        return entidad;
    }
    //retornamos usuario para saber que roll tiene
    

    @Override
    public Usuario executeRead(Integer id) {
        return Usuario.listaDeUsuarios.stream()
                .filter(u->u.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean executeUpdate(Integer integer) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void executeDelete(Integer id) {
        repository.delete(id);
    }

    public boolean registrarUsuario(Usuario usuario) {

        Usuario existeUsuario = Usuario.listaDeUsuarios.stream()
                .filter(u -> u.getEmail().equals(usuario.getEmail()))
                .findFirst()
                .orElse(null);

        if (existeUsuario != null) {
            return false;
        }

        executeCreate(usuario);
        return true;
    }

    public Usuario login(String email, String password) {
        return Usuario.listaDeUsuarios.stream()
                                     //mayusculas y minusculas con el dato que ya tenemos 
                .filter(u -> u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean actualizarUsuario(Usuario usuario) {

        Usuario existeUsuario = Usuario.listaDeUsuarios.stream()
                .filter(u -> u.getEmail().equals(usuario.getEmail()))
                .findFirst() 
                .orElse(null);
        //se encuentra el primero o returna null

        if (existeUsuario != null) {
            return false;
        }
        repository.update(usuario);
        return true;
    }
}
//todo lo static le pertenece a la clase no al objeto 
//en el string pasan todos los datos y filter para seleccionar la informacion que necesitamos.  
