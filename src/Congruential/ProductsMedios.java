package Congruential;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ProductsMedios extends JFrame {
    
    JLabel x0, x1, d, cantidad, console;
    JTextField txtx0, txtx1, txtd, txtcantidad;
    JTextArea txtconsole = new JTextArea("");
    JButton calcular, exportar;
    JPanel panel = new JPanel();
    String contenido = "";
    
    String file = "ProductosMedios.xls";
    File archivoXLS = new File("../" + file);
    
    public void initForm() {
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setTitle("Productos medios");
        positionElements();
        addElement();
        init();
    }
    
    public void init() {
        
        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcNumbers(txtx0.getText(), txtx1.getText(), txtcantidad.getText(), txtd.getText());
                exportar.setVisible(true);
            }
        });
        exportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(archivoXLS);
                    exportar.setVisible(false);
                    txtconsole.setText("Archivo creado " + file);
                } catch (Exception ex) {
                }
            }
        });
    }
    
    public void calcNumbers(String x0, String x1, String _cant, String _d) {
        
        int cant = Integer.parseInt(_cant), largonum = 0, corte = 0;
        String y = "", nextx1 = "", random = "";
        
        try {
            if (archivoXLS.exists()) {
                archivoXLS.delete();
            }
            archivoXLS.createNewFile();
            Workbook libro = new HSSFWorkbook();
            
            FileOutputStream archivo = new FileOutputStream(archivoXLS);
            Sheet hoja = libro.createSheet("Mi hoja de trabajo 1");
            
            HSSFRow fila = (HSSFRow) hoja.createRow(0);
            Cell celda = fila.createCell(0);
            celda.setCellValue("X0");
            celda = fila.createCell(1);
            celda.setCellValue("X1");
            celda = fila.createCell(2);
            celda.setCellValue("r");
            
            for (int i = 0; i < cant; i++) {
                y = "" + Integer.parseInt(x0) * Integer.parseInt(x1);
                largonum = y.length();
                
                corte = largonum - Integer.parseInt(_d);
                
                if (corte % 2 != 0) {
                    y = "0" + y;
                    corte++;
                }
                corte = corte / 2;
                nextx1 = y.substring(corte, y.length());
                x0 = x1;
                x1 = nextx1.substring(0, nextx1.length() - corte);
                random = "0." + x1;
                System.out.println("random " + random);
                
                fila = (HSSFRow) hoja.createRow(i + 1);
                celda = fila.createCell(0);
                celda.setCellValue(x0);
                celda = fila.createCell(1);
                celda.setCellValue(x1);
                celda = fila.createCell(2);
                celda.setCellValue(random);
            }
            
            libro.write(archivo);
            archivo.close();
            
        } catch (IOException e) {
        }
        
    }
    
    public void addElement() {
        add(x0);
        add(txtx0);
        add(x1);
        add(txtx1);
        add(d);
        add(calcular);
        add(exportar);
        add(txtd);
        add(cantidad);
        add(txtcantidad);
        add(console);
        add(txtconsole);
        add(panel);
        setSize(500, 350);
        setVisible(true);
    }
    
    public void positionElements() {
        x0 = new JLabel("X0");
        txtx0 = new JTextField("");
        
        x0.setBounds(10, 30, 50, 20);
        txtx0.setBounds(70, 30, 100, 20);
        
        x1 = new JLabel("X1");
        txtx1 = new JTextField("");
        
        x1.setBounds(10, 60, 50, 20);
        txtx1.setBounds(70, 60, 100, 20);
        
        d = new JLabel("d:");
        txtd = new JTextField("");
        
        d.setBounds(10, 90, 60, 20);
        txtd.setBounds(70, 90, 100, 20);
        
        cantidad = new JLabel("cantidad:");
        txtcantidad = new JTextField("");
        
        cantidad.setBounds(10, 120, 60, 20);
        txtcantidad.setBounds(70, 120, 100, 20);
        
        console = new JLabel("Console:");
        console.setBounds(10, 170, 60, 20);
        
        txtconsole.setBounds(10, 190, 460, 100);
        
        calcular = new JButton();
        calcular.setBounds(300, 30, 100, 20);
        calcular.setText("Calcular");
        
        exportar = new JButton();
        exportar.setBounds(300, 60, 100, 20);
        exportar.setText("Exportar");
        exportar.setVisible(false);
        
        panel.setLayout(null);
    }
    
}
