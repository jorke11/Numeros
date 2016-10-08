/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Congruential;

import Conexion.Consultas;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author pinedo
 */
public class ScuareMedios extends JFrame {

    JLabel semilla, d, cantidad, console;
    JTextField txtsemilla, txtd, txtcantidad;
    JTextArea txtconsole = new JTextArea("");
    JButton calcular, crearExcel;
    JPanel panel = new JPanel();
    String contenido = "";
    Consultas sql = new Consultas();
    String file = "ejemploExcelJava.xls";
    File archivoXLS = new File("../" + file);

    public void InitForm() {
        this.contenido = "";
        ScuareMedios obj = new ScuareMedios();

        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setTitle("Cuadrados Medios");

        positionElements();
        addElement();

        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj.getAleatory(txtsemilla.getText().toString(), txtd.getText().toString(), txtcantidad.getText().toString());
                crearExcel.setVisible(true);
            }
        });

        crearExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(archivoXLS);
                    crearExcel.setVisible(false);
                } catch (Exception ex) {
                }

            }
        });

    }

    public String calcNumbers(String semilla, String d, int f, Sheet hoja) {

        int num = 0, y0 = 0, longitudX0 = 0, corte = 0;
        String temp = "", sem = "", centro = "", printSemilla = semilla;

        try {
            num = Integer.parseInt(semilla);
            if (semilla.length() > 2) {
                y0 = (int) Math.pow(num, 2);
                longitudX0 = (String.valueOf(y0)).length();
                if (longitudX0 % 2 == 0 && Integer.parseInt(d) % 2 == 0) {
                    semilla = "" + y0;
                } else {
                    corte = longitudX0 - Integer.parseInt(d);
                    semilla = (corte % 2 == 0) ? "" + y0 : "0" + y0;
                }

                sem = semilla;
                corte = (semilla.length() - Integer.parseInt(d)) / 2;
                semilla = semilla.substring(corte, semilla.length() - corte);
                centro = semilla;
                semilla = "0." + semilla;
                sql.Agregar(sem, String.valueOf(y0), centro, semilla);
                HSSFRow fila = (HSSFRow) hoja.createRow(f + 1);
                Cell celda = fila.createCell(0);
                celda.setCellValue(printSemilla);
                celda = fila.createCell(1);
                celda.setCellValue(sem);
                celda = fila.createCell(2);
                celda.setCellValue(centro);
                celda = fila.createCell(3);
                celda.setCellValue(semilla);
                System.out.println("aleatorio " + semilla + "  inicial: " + sem);
                System.out.println("Prueba " + txtconsole.getText());

            } else {
                System.out.println("La semilla debe ser mayor a 3 Digitos");
            }

        } catch (Exception e) {
            System.out.println("Solo se permite numeros, vuelva a intentarlo! " + e);
        }

        return centro;
    }

    public void getAleatory(String semilla, String d, String cantidad) {
        ScuareMedios obj = new ScuareMedios();
        String sem = semilla;

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
            celda.setCellValue("Y");
            celda = fila.createCell(2);
            celda.setCellValue("Xnext");
            celda = fila.createCell(3);
            celda.setCellValue("r");

            for (int i = 0; i < Integer.parseInt(cantidad); i++) {
                sem = obj.calcNumbers(String.valueOf(sem), d, i, hoja);
            }
            libro.write(archivo);
            archivo.close();

        } catch (Exception e) {
        }

    }

    public void addElement() {
        add(semilla);
        add(txtsemilla);
        add(d);
        add(calcular);
        add(crearExcel);
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
        semilla = new JLabel("Semilla");
        txtsemilla = new JTextField("");

        semilla.setBounds(10, 30, 50, 20);
        txtsemilla.setBounds(70, 30, 100, 20);

        d = new JLabel("d:");
        txtd = new JTextField("");

        d.setBounds(10, 60, 60, 20);
        txtd.setBounds(70, 60, 100, 20);

        cantidad = new JLabel("cantidad:");
        txtcantidad = new JTextField("");

        cantidad.setBounds(10, 90, 60, 20);
        txtcantidad.setBounds(70, 90, 100, 20);

        console = new JLabel("Console:");
        console.setBounds(10, 140, 60, 20);

        txtconsole.setBounds(10, 160, 460, 100);

        calcular = new JButton();
        calcular.setBounds(300, 30, 100, 20);
        calcular.setText("Calcular");

        crearExcel = new JButton();
        crearExcel.setBounds(300, 60, 100, 20);
        crearExcel.setText("Exportar");
        crearExcel.setVisible(false);
        panel.setLayout(null);
    }

}
