/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author pinedo
 */
public class MultiplierConstant extends JFrame {

    JLabel x0, constante, d, cantidad, console;
    JTextField txtx0, txtconstante, txtd, txtcantidad;
    JTextArea txtconsole = new JTextArea("");
    JButton calcular, exportar;
    JPanel panel = new JPanel();
    String contenido = "";

    String file = "MutiplicadorConstante.xls";
    File archivoXLS = new File("../" + file);

    public void initForm() {
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setTitle("Multiplicador constante!");
        positionElements();
        addElement();
        init();
    }

    public void init() {

        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcNumbers(txtx0.getText(), txtconstante.getText(), txtcantidad.getText(), txtd.getText());
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

    public void calcNumbers(String x0, String constanteante, String _cant, String d) {
        int cant = Integer.parseInt(_cant), largonum = 0, corte = 0;
        String y = "", nextx0 = "", random = "";

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
            celda.setCellValue("y");
            celda = fila.createCell(1);
            celda.setCellValue("x");
            celda = fila.createCell(2);
            celda.setCellValue("r");

            for (int i = 0; i < cant; i++) {
                y = "" + Integer.parseInt(constanteante) * Integer.parseInt(x0);
                largonum = y.length();

                corte = largonum - Integer.parseInt(d);

                if (corte % 2 != 0) {
                    y = "0" + y;
                    corte++;
                }
                corte = corte / 2;
                nextx0 = y.substring(corte, y.length());

                x0 = nextx0.substring(0, nextx0.length() - corte);
                random = "0." + x0;

                fila = (HSSFRow) hoja.createRow(i + 1);
                celda = fila.createCell(0);
                celda.setCellValue(y);
                celda = fila.createCell(1);
                celda.setCellValue(x0);
                celda = fila.createCell(2);
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

        constante = new JLabel("Constante");
        txtconstante = new JTextField("");

        constante.setBounds(10, 60, 80, 20);
        txtconstante.setBounds(70, 60, 100, 20);

        d = new JLabel("d:");
        txtd = new JTextField("");

        d.setBounds(10, 90, 80, 20);
        txtd.setBounds(70, 90, 100, 20);

        cantidad = new JLabel("cantidad:");
        txtcantidad = new JTextField("");

        cantidad.setBounds(10, 120, 80, 20);
        txtcantidad.setBounds(70, 120, 100, 20);

        console = new JLabel("Console:");
        console.setBounds(10, 170, 80, 20);

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

    public void addElement() {
        add(x0);
        add(txtx0);
        add(constante);
        add(txtconstante);
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

}
