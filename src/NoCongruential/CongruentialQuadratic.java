/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoCongruential;

import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author pinedo
 */
public class CongruentialQuadratic extends JFrame {

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
//        positionElements();
//        addElement();
//        init();
    }
}
