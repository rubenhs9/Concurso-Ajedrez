package Main;

import DAO.GestorBD;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        try{
            Connection conn = GestorBD.conectarBDD();
            System.out.println("Conexion EXITOSA con la base de datos");
            GestorBD.cerrarConexion(conn);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR AL CONECTARSE A BD");
        }
    }
    
}
