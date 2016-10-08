package NoCongruential;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CongruentialAdditive extends JFrame {

    JLabel m, cantidad, console;
    JTextField txtm, txtcantidad;
    JTextArea txtconsole = new JTextArea("");
    JButton calcular, exportar;
    JPanel panel = new JPanel();
    String contenido = "";

    String file = "CongruencialAditivo.xls";
    File archivoXLS = new File("../" + file);

    public void initForm() {
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setTitle("Congruencia Aditivo");
        positionElements();
        addElement();
        init();
    }

    public void init() {
        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aleatorios(5);
                calcNumbers(aleatorios(5), txtm.getText());
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

    public void calcNumbers(int[] numbers, String m) {
        Double random;
        for (int i = 0; i < numbers.length; i++) {
            x0 = (((Integer.parseInt(a) * x0)) % Math.pow(2, Integer.parseInt(m)));
            random = (x0 / (Integer.parseInt(m) - 1));
        }

    }

    public int[] aleatorios(int cantidad) {
        int num[] = new int[cantidad];
        String txtc = "";
        for (int i = 0; i < cantidad; i++) {
            num[i] = (int) (Math.random() * 100 + 1);
            txtc += (txtc == "") ? "" : "\n";
            txtc += num[i];
        }
        txtconsole.setText(txtc);

        return num;
    }

    public void positionElements() {
        m = new JLabel("M");
        txtm = new JTextField("");

        m.setBounds(10, 30, 80, 20);
        txtm.setBounds(70, 30, 100, 20);

        cantidad = new JLabel("cantidad:");
        txtcantidad = new JTextField("");

        cantidad.setBounds(10, 60, 80, 20);
        txtcantidad.setBounds(70, 60, 100, 20);

        console = new JLabel("Console:");
        console.setBounds(10, 90, 80, 20);

        txtconsole.setBounds(10, 110, 370, 100);

        calcular = new JButton();
        calcular.setBounds(250, 30, 100, 20);
        calcular.setText("Calcular");

        exportar = new JButton();
        exportar.setBounds(300, 60, 100, 20);
        exportar.setText("Exportar");
        exportar.setVisible(false);

        panel.setLayout(null);
    }

    public void addElement() {
        add(m);
        add(txtm);
        add(console);
        add(calcular);
        add(exportar);
        add(cantidad);
        add(txtcantidad);

        add(txtconsole);
        add(panel);
        setSize(400, 300);
        setVisible(true);
    }
}
