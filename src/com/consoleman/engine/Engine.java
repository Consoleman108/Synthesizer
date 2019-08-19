package com.consoleman.engine;

import com.consoleman.display.Display;
import com.consoleman.keyboard.Keyboard;
import com.consoleman.oscillator.Oscillator;

import java.awt.event.KeyEvent;
import java.nio.ByteBuffer;

import static com.consoleman.display.Display.*;
import static com.consoleman.oscillator.Oscillator.SINE_PACKET_SIZE;

public class Engine implements Runnable{

    // определяем константы
    public static final int    WIDTH           = 800;
    public static final int    HEIGHT          = 600;
    public static final String TITLE           = "Synthesizer";


    private boolean running;                                         // проверка запущена игра или нет
    private Thread audioThread;
    private Keyboard keyboard = new Keyboard();
    private Oscillator oscillator = new Oscillator(440);

    public Engine(){
        running = false;                            // синтезатор еще не запущен

        Display.create( WIDTH, HEIGHT, TITLE);      // создаем окно
        Display.addInputListener(keyboard);         // для считывания клафиш
        oscillator.sampleInit();
    }

    // функция запуска игры (причем запустить данную функцию может только один поток)
    public synchronized void start(){
        if(running) return; // если игра запущенна ни чего запускать не наддо

        // иначе говорим что игра запускаеться
        running = true;
        // создаем поток
        audioThread = new Thread(this);
        // запускаем поток (метод run)
        audioThread.start();
    }

    // функция для остановки игры (причем отсановить данную функцию может только один поток)
    public synchronized void stop(){
        // если игра не запущена не чего и останавливать
        if(!running) return;

        // иначе если игра зупущена останавливаем ее
        running = false;
        // обрабатываем исключение
        try {
            // ждем завершения потока
            audioThread.join();
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        cleanUp();
    }

    // тут будет считаться вся физикаБ вся геометрия, все математические расчеты, все движения, все позиции
    private void update(){
        if(keyboard.getKey(KeyEvent.VK_UP)){
            oscillator.generateSample();
            System.out.println("UP");
        }
        if(keyboard.getKey(KeyEvent.VK_DOWN)){
            System.out.println("Down");
        }
        if(keyboard.getKey(KeyEvent.VK_LEFT)){
            System.out.println("Left");
        }
        if(keyboard.getKey(KeyEvent.VK_RIGHT)){
            System.out.println("Right");
        }
        System.out.print("");
    }

    // после того как вся математика будет обсчитана будем отрисовывать сцены в игре
   /* private void render(){
        System.out.println("Render");
    }*/

    // главная функция ядро которая будет держать цикл
    //@Override
    public void run(){
        while (running)
        {
            update();
        }
    }

    // любые ресурсы которые мы будем создавать которые необходимо закрывать будут тут
    private void cleanUp(){
        // уничтожаем окно после того как закроем игру
        destroy();
    }
}
