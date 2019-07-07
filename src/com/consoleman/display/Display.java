package com.consoleman.display;

import com.consoleman.keyboard.Keyboard;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        // делаем окно видным
        window.setVisible(true);

        /*window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    // Для завершение процесса иначе после закрытия процесс останется
        window.setSize(800, 600);                            // Размер окна
        window.setResizable(false);                                        // Окно без возможности изменения размера
        window.setLayout(null);                                            // Без использования менеджера расположения Layout элементов
        //setLocation(400, 400);                                    // Положение окна с заданными координатами
        window.setLocationRelativeTo(null);                                // Положение окана посередине
        window.setVisible(true);*/

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
    public static void addInputListener(Keyboard inputListener){
        window.add(inputListener);
    }
}

