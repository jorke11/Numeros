/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoCongruential;

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

public class LinealAlgorithm extends JFrame {

    JLabel x0, a, c, m, cantidad, console;
    JTextField txtx0, txta, txtc, txtm, txtcantidad;
    JTextArea txtconsole = new JTextArea("");
    JButton calcular, exportar;
    JPanel panel = new JPanel();
    String contenido = "";

    String file = "ALgorimoLineal.xls";
    File archivoXLS = new File("../" + file);

    public void initForm() {
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setTitle("Algoritmo Lineal!");
        positionElements();
        addElement();
        init();
    }

    public void init() {

        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcNumbers(txtx0.getText(), txta.getText(), txtc.getText(), txtm.getText(), txtcantidad.getText());
                exportar.setVisible(true);
                txtconsole.setText("Se creo archivo " + file);
            }
        });
        exportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(archivoXLS);
                    exportar.setVisible(false);
                } catch (Exception ex) {
                }
            }
        });
    }

    public void calcNumbers(String x0, String a, String c, String m, String cantidad) {
        int y = 0;
        double random;

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
            celda.setCellValue("X");
            celda = fila.createCell(1);
            celda.setCellValue("r");

            for (int i = 0; i < Integer.parseInt(cantidad); i++) {
                fila = (HSSFRow) hoja.createRow(i + 1);
                celda = fila.createCell(0);
                celda.setCellValue(x0);
                x0 = String.valueOf(((Integer.parseInt(a) * Float.parseFloat(x0)) + Integer.parseInt(c)) % Integer.parseInt(m));
                random = (Float.parseFloat(x0) / (Integer.parseInt(m) - 1));
                System.out.println("rando " + random);
                celda = fila.createCell(1);
                celda.setCellValue(random);
            }
            
            libro.write(archivo);
            archivo.close();
        } catch (IOException e) {
        }

    }

    public void positionElements() {
        x0 = new JLabel("X0");
        txtx0 = new JTextField("");

        x0.setBounds(10, 30, 80, 20);
        txtx0.setBounds(70, 30, 100, 20);

        a = new JLabel("a");
        txta = new JTextField("");

        a.setBounds(10, 60, 80, 20);
        txta.setBounds(70, 60, 100, 20);

        c = new JLabel("c");
        txtc = new JTextField("");

        c.setBounds(10, 90, 80, 20);
        txtc.setBounds(70, 90, 100, 20);

        m = new JLabel("M");
        txtm = new JTextField("");

        m.setBounds(10, 120, 80, 20);
        txtm.setBounds(70, 120, 100, 20);

        cantidad = new JLabel("cantidad:");
        txtcantidad = new JTextField("");

        cantidad.setBounds(10, 150, 80, 20);
        txtcantidad.setBounds(70, 150, 100, 20);

        console = new JLabel("Console:");
        console.setBounds(10, 190, 80, 20);

        txtconsole.setBounds(10, 210, 460, 70);

        calcular = new JButton();
        calcular.setBounds(300, 30, 100, 20);
        calcular.setText("Calcular");

        exportar = new JButton();
        exportar.setBounds(300, 60, 100, 20);
        exportar.setText("Exportar");
        exportar.setVisible(false);

        panel.setLayout(null);
    }

    public void addElement() {
        add(x0);
        add(txtx0);
        add(a);
        add(txta);
        add(c);
        add(txtc);
        add(m);
        add(txtm);
        add(console);
        add(calcular);
        add(exportar);
        add(cantidad);
        add(txtcantidad);

        add(txtconsole);
        add(panel);
        setSize(500, 350);
        setVisible(true);
    }
}
