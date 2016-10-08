package Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class Consultas {

    static Connection cn;
    static Statement st;
    static ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();

    public DefaultTableModel lista() {
        try {
            Statement s = cn.createStatement();
            String sql = "Select * from scuare";
            rs = s.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();

            int col = rsmd.getColumnCount();
            for (int i = 0; i < col; i++) {
                model.addColumn(rsmd.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] file = new Object[col];
                for (int i = 0; i < col; i++) {
                    file[i] = rs.getObject(i + 1);
                }
                model.addRow(file);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return model;
    }

    public void Agregar(String x, String y, String semilla, String aleatorio) {
        try {
            cn = Conectar.Enlace(cn);
            Statement s = cn.createStatement();
            String sql = "INSERT INTO scuare(x,y,semilla,aleatorio) values(\"" + x + "\",\"" + y + "\",\"" + semilla + "\",\"" + aleatorio + "\")";
            s.executeUpdate(sql);
            s.close();
            cn.close();
            System.out.println("Guardado");
        } catch (Exception e) {
            System.out.println("error agregar " + e);
        }
    }

}
