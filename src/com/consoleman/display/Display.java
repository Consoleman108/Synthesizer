package com.consoleman.display;

import com.consoleman.keyboard.Keyboard;
import com.consoleman.oscillator.Oscillator;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public abstract class Display {
    // Проверяем создано окно или нет
    private static boolean created = false;
    // Переменная для рамки для листа (окно)
    private static JFrame window;
    // Метод для создания окна c с параметрами окна и названием
    public static void create(int width, int height, String title){

        // Делаем проверку если окно создано мы ни чего не делаем выходим из функции
        if(created) return;

        // иначе мы создаем окно
        // создаем рамку
        window = new JFrame(title);
        // когда мы нажимаек крестик закрываем всю программу
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        // Окно не меняет размер
        window.setResizable(false);
        // возвращает только внутреннюю часть окна и добавляем туда лист
        //window.getContentPane().add(content);
        // pack изменит размер окна под размер нашего листа
        //window.pack();
        // делаем окно по центру
        // функция меняет позицию в зависимости от другого компонента, у нас его нет
        window.setLocationRelativeTo(null);

        //window.getContentPane().add(addPanel());
        //window.add(jSlider);

        window.add(addPanel());
        //window.add();
        // делаем окно видным
        window.setVisible(true);

        //temp

        //end temp

        created = true;
    }

    // функция уничтожения окна
    public static void destroy(){
        if(!created)
            return;

        window.dispose();
    }

    //  функция изменения заголовка окна
    public static void setTitle(String title){

        window.setTitle(title);
    }

    // функция считывания клавиш
    public static void addInputListener(Keyboard keyboardListener){

        window.add(keyboardListener);
    }


    public static JPanel addPanel(){
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setSize(600,200);
        jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
        //jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.setLocation(5,0);

        // Display slider
        /*JSlider jSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 10);
        jSlider.setPaintTicks(true);
        jSlider.setMajorTickSpacing(100);
        jSlider.setMinorTickSpacing(20);
        jPanel.add(jSlider);*/


        //jPanel.setLayout(null);

        return jPanel;
    }
}

