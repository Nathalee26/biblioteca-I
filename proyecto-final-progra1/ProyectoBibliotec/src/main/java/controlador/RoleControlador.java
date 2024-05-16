package controlador;

import modelo.Role;
import repositorio.RoleDAO;



public class RoleControlador {
    private RoleDAO roleDAO = new RoleDAO();

   public void ObtenerRoles(){
       Role.listaDeRoles = roleDAO.readAll();
   }
}
