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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class CongruentialMultiplier extends JFrame {

    JLabel x0, g, k, cantidad, console;
    JTextField txtx0, txtk, txtg, txtcantidad;
    JTextArea txtconsole = new JTextArea("");
    JButton calcular, exportar;
    JPanel panel = new JPanel();
    String contenido = "";

    String file = "MultiplicadorCongruencial.xls";
    File archivoXLS = new File("../" + file);

    public void initForm() {
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setTitle("Multiplicador congruencial");
        positionElements();
        addElement();
        init();
    }

    public void init() {

        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ingreo");
                calcNumbers(txtx0.getText(), txtg.getText(), txtk.getText(), txtcantidad.getText());
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

    public void calcNumbers(String x0, String g, String k, String cantidad) {
        int y = 0, a = 0;
        double random, modulo;
        
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
                a = Integer.parseInt(g) + (8 * Integer.parseInt(k));
                modulo = Math.pow(2, Integer.parseInt(g));
                System.out.println("Operacion (" + x0 + " x " + a + ") mod " + modulo);
                x0 = String.valueOf(((a * Float.parseFloat(x0))) % modulo);
                random = (Float.parseFloat(x0) / (modulo - 1));

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

        g = new JLabel("g");
        txtg = new JTextField("");

        g.setBounds(10, 60, 80, 20);
        txtg.setBounds(70, 60, 100, 20);

        k = new JLabel("k");
        txtk = new JTextField("");

        k.setBounds(10, 90, 80, 20);
        txtk.setBounds(70, 90, 100, 20);

        cantidad = new JLabel("cantidad:");
        txtcantidad = new JTextField("");

        cantidad.setBounds(10, 120, 80, 20);
        txtcantidad.setBounds(70, 120, 100, 20);

        console = new JLabel("Console:");
        console.setBounds(10, 160, 80, 20);
        txtconsole.setBounds(10, 190, 460, 70);

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
        add(g);
        add(txtg);
        add(k);
        add(txtk);
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
