
package Conexion;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author José
 */
public class conexion {
    private static Connection con;
    private Statement statement;
    
    public Connection openConnection(){
    
        if(con==null){
            String url="jdbc:mysql://localhost:3306/devmasters?autoReconnect=true&useSSL=false";
           // String db="devmaster";
            String driver= "com.mysql.jdbc.Driver";
            String user="root";
            String password="";
            
            try{
                Class.forName(driver);
                this.con= (Connection) DriverManager.getConnection(url, user, password);
                System.out.println("Conexión exitosa");
                
            }catch(ClassNotFoundException | SQLException sqle){
                System.out.println("Error en la conexión");
            }
        }
        return con;
}
    /*
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "josecas13";
    private static final String url= "jdbc:mysql://localhost:3306/tiendapc?autoReconnect=true&useSSL=false";*/
}
