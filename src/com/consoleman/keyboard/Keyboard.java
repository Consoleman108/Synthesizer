package com.consoleman.keyboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Keyboard extends JComponent {
    private boolean[] map; // массив всех клавиш

    public Keyboard(){
        map = new boolean[256];

        // пробегаем по всем клавишам
        for (int i = 0; i < map.length; i++){
            // код клавиши который нас сейчас интересует
            final int KEY_CODE = i; // если мы используем что-то внутри ананимуса класса он должен быть final

            // считываем кнопки только когда окно в фокусе  |        нажатие кнопки по ASCII коду которая 0           | имя на нажатие каждой кнопки
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i,0, false), i * 2);
            // что будет происходить когда мы нажиаем на кнопку
            getActionMap().put(i * 2, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = true;
                }
            });


            // считываем кнопки только когда окно в фокусе  |        отпускание кнопки по ASCII коду которая 0           | имя на отпускание каждой кнопки
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i,0, true), i * 2 + 1);
            // что будет происходить когда мы нажиаем на кнопку
            getActionMap().put(i * 2 + 1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = false;
                }
            });
        }
    }

    // функция копируем массив всех колавиш
    public boolean[] getMap(){
        return Arrays.copyOf(map, map.length);
    }

    // получаем клавишу
    public boolean getKey(int keyCode){
        return map[keyCode];
    }

    /*public void keyListener()
    {
        JComponent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                System.out.println("Key is press");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //audioThread.StopPlayback();
                //audioThread.close();
                System.out.println("Key is release");
            }
        });
    }*/
}
