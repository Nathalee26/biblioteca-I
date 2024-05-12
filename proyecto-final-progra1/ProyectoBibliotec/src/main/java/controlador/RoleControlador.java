/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Role;
import Repositorio.RoleDAO;

public class RoleControlador {
    private RoleDAO roleDAO = new RoleDAO();
    
    public void ObtenerRoles(){
        Role.listaDeRoles = roleDAO.readAll();
    }
    
}
