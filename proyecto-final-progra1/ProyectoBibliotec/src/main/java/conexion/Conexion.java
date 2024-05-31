/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;



//static no se pueda manipular 
public class Conexion {
    //Son 3 constantes para conectar base de datos
    
    public static final String URL = "jdbc:postgresql://ep-nameless-term-a5tt6qru.us-east-2.aws.neon.tech:5432/Progra1";
    public static final String USER = "Progra1_owner";
    public static final String PASSWORD = "IdYgf5eaMcB9";
    //metodo de instancia = 
    //metodo de clase. y llama nuestra conexion 
     public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
   //driver es propiedad del import  
//todas las cosas que lleven .java son clases 