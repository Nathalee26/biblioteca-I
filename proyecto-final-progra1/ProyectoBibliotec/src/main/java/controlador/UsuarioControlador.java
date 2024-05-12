/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Usuario;
import repositorio.UsuarioDAO;

import java.util.List;

public class UsuarioControlador extends AbstractCrud<Usuario, Integer>{
    
    private UsuarioDAO repository  = UsuarioDAO();
    
    @Override
    public List<Usuario> executeReadAll(){
        return repository.readAll();
    }
    @Override
    public Usuario executeCreate(Usuario entidad){
        repository.save(entidad);
        return entidad;
    }
    @Override
    public Usuario executeRead(Integer id){
        return Usuario.listDeUsuarios.stram()
                .filter(u->.getId()==id)
                .findFirst()
                .orElse(null);
    }
    @Override
    public void executeDelete(Integer id){
        repository.delete(id);
    }
    
    public boolean registrarUsuario(Usuario usuario){
        Usuario existeUsuario = Usuario.listaDeUsuarios.stream()
                .filter (u -> u.getEmail().equals(usuario.getEmail()))
                .findFirst()
                .orElse(null);
        if(existeUsuario !=null){
            return false;
        }
        
        executeCreate(usuario);
        return true;           
    }
    public Usuario login(String email, String password){
        return Usuario.listaDeUsuarios.stream()
                .filter(u -> u.getEmail().equals(email)&& u.getPassword().equals(password))
                .findFirst()
                .orElse(null);   
        if (existeUsuario !=null){
            return false;
        }
    public boolean actualizarUsuario(Usuario usuario){
        
        Usuario existeUsuario = Usuario.listaDeUsuarios.stream()
                .filter(u -> u.getEmail().equals(usuario.getEmail()))
                .findFirst()
                .orElse(null)
        if (existeUsuario !=null){
            return false;
        }
    }
        repository.update(usuario);
        return true; 
    }
}




