package Conexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

    static Connection cn;

    public static Connection Enlace(Connection cn) throws SQLException {
        String ruta = "C:\\Users\\pinedo\\Documents\\NetBeansProjects\\Simulacion\\simulacion.sqlite";
        try {
            Class.forName("org.sqlite.JDBC");
            cn = DriverManager.getConnection("jdbc:sqlite:" + ruta);

        } catch (Exception e) {
            System.out.println("error de conexion: "+ e);
        }
        return cn;
    }
}
