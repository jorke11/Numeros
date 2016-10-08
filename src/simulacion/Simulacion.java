/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import Congruential.*;
import NoCongruential.CongruentialAdditive;
import NoCongruential.CongruentialMultiplier;
import NoCongruential.CongruentialQuadratic;
import NoCongruential.LinealAlgorithm;
import com.sun.rowset.internal.Row;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pinedo
 */
public class Simulacion extends JFrame implements MouseListener {

    JLabel title, p1, p2, p3, p4, p5, p6, p7;

    public Simulacion() {
        title = new JLabel("Inicio");
        p1 = new JLabel("1. Algoritmo de cuadrados medios");
        p2 = new JLabel("2. Algoritmo de productos medios");
        p3 = new JLabel("3. Algoritmo de multiplicador constante");
        p4 = new JLabel("4. Algoritmo Linear");
        p5 = new JLabel("5. Congruencial Multiplicativo");
        p6 = new JLabel("6. Congruencial Aditivo");
        p7 = new JLabel("7. Congruencial Cuadratico");

        p1.setName("point1");
        p2.setName("point2");
        p3.setName("point3");
        p4.setName("point4");
        p5.setName("point5");
        p6.setName("point6");
        p7.setName("point7");
        title.setBounds(10, 1, 80, 20);
        p1.setBounds(10, 30, 300, 20);
        p2.setBounds(10, 60, 300, 20);
        p3.setBounds(10, 90, 300, 20);
        p4.setBounds(10, 120, 300, 20);
        p5.setBounds(10, 150, 300, 20);
        p6.setBounds(10, 180, 300, 20);
        p7.setBounds(10, 210, 300, 20);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        p1.addMouseListener(this);
        p2.addMouseListener(this);
        p3.addMouseListener(this);
        p4.addMouseListener(this);
        p5.addMouseListener(this);
        p6.addMouseListener(this);
        p7.addMouseListener(this);
        setTitle("Menu Principal");

        add(title);
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
        add(p7);
        add(panel);
        setSize(350, 280);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Simulacion s = new Simulacion();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "point1": {
                setVisible(false);
                ScuareMedios obj = new ScuareMedios();
                obj.InitForm();
                break;
            }
            case "point2": {
                setVisible(false);
                ProductsMedios obj = new ProductsMedios();
                obj.initForm();
                break;
            }
            case "point3": {
                setVisible(false);
                MultiplierConstant obj = new MultiplierConstant();
                obj.initForm();
                break;
            }
            case "point4": {
                setVisible(false);
                LinealAlgorithm obj = new LinealAlgorithm();
                obj.initForm();
                break;
            }
            case "point5": {
                setVisible(false);
                CongruentialMultiplier obj = new CongruentialMultiplier();
                obj.initForm();
                break;
            }
            case "point6": {
                setVisible(false);
                CongruentialAdditive obj = new CongruentialAdditive();
                obj.initForm();
                break;
            }
            case "point7": {
//                setVisible(false);
//                CongruentialQuadratic obj = new CongruentialQuadratic();
//                obj.initForm();
                break;
            }
        }

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(DEFAULT_CURSOR));
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
