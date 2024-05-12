/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Prestamo;
import repositorio.PrestamoDAO;
import java.sql.Date;
import java.util.List;



public class PrestamoController extends AbstractCrud<Prestamo, Integer>{
    
        private PrestamoDAO prestamoDao = new PrestamoDAO(){
        
        @Override
        public List<Prestamo> executeReadAll(){
            return prestaomDao.readALL();
        }
        @Override
        public Prestamo executeCreate(Prestamo entidad){
        prestamoDAO.save(entidad);
            return entidad;
        }
        @Override
        public Prestamo executeRead(Integer integer){
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean executeUpdate(Integer integer){
            throw new UnsupportedOperationException();
        }
        @Override
        public void executeDelete(Integer integer){
            throw new UnsupportedOperationException();
        }
        public boolean verificarPrestamosTardios(int isbn, int usuarioID){
            return Prestamo.listaDePrestamos.stream()
                    .anyMatch(prestamo-> prestamo.getUsuarioID()==usuarioID && prestamo.getLibroISBN()==isbn)
                            && prestamo.getFechaVecimiento().before(new Date(System.currentTimeMillis()))
                            && prestamo.getFechaDevolucion()==null);
            }
        public void actualizarDevoluvion (int id){
            prestamoDao.actualizarDevolucion(id);
        }
        }
