package com.consoleman.engine;

import com.consoleman.display.Display;
import com.consoleman.keyboard.Keyboard;
import com.consoleman.time.Time;

import java.awt.event.KeyEvent;

public class Engine implements Runnable{

    // определяем константы
    public static final int    WIDTH           = 800;
    public static final int    HEIGHT          = 600;
    public static final String TITLE           = "Synthesizer";


    private boolean            running;                                         // проверка запущена игра или нет
    private Thread             gameThread;
    Keyboard                   keyboard;

    public Engine(){
        running = false;                            // синтезатор еще не запущен

        Display.create( WIDTH, HEIGHT, TITLE);      //  создаем окно
    }

    // функция запуска игры (причем запустить данную функцию может только один поток)
    public synchronized void start(){
        if(running) return; // если игра запущенна ни чего запускать не наддо

        // иначе говорим что игра запускаеться
        running = true;
        // создаем поток
        gameThread = new Thread(this);
        // запускаем поток (метод run)
        gameThread.start();
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
            gameThread.join();
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        cleanUp();
    }

    // тут будет считаться вся физикаБ вся геометрия, все математические расчеты, все движения, все позиции
    private void update(){
        //delta += 0.02f;
        // двигаемся вверх
        /*if(Keyboard.getKey(KeyEvent.VK_UP))
            y -= speed;
        // двигаемся вниз
        if(Keyboard.getKey(KeyEvent.VK_DOWN))
            y += speed;
        // влево
        if(Keyboard.getKey(KeyEvent.VK_LEFT))
            x -= speed;
        // вправо
        if(Keyboard.getKey(KeyEvent.VK_RIGHT))
            x += speed;*/
        if(keyboard.getKey(KeyEvent.VK_A))
            System.out.println("A");

    }

    // после того как вся математика будет обсчитана будем отрисовывать сцены в игре
    private void render(){
        // отщищаем
        /*Display.clear();
        graphics.setColor(Color.WHITE);
        //graphics.fillOval((int) (x + Math.abs(Math.cos(delta))),(int) (y + (Math.sin(delta) * 100)), (int) (radius * 2 + (Math.cos(delta) * 100)),(int) (radius * 2+ (Math.cos(delta) * 100)));
        graphics.fillOval((int) x,(int) (y + (Math.sin(delta) * 100)), (int) (radius * 2),(int) (radius * 2));*/

    }

    // главная функция ядро которая будет держать цикл
    @Override
    public void run(){
      /*  // счетчики
        int fps  = 0; // fps
        int upd  = 0; // обновлений
        int updl = 0; // сколько раз мы пытались догнать наши обновления обновление циклов

        long count = 0;

        // количество аптедйтов которое нам нужно сделать (количетсво раз которое функцию должа бежать)
        float delta = 0;

        // сохраняем время
        long lastTime = Time.get();

        while (running) {
            // текущее время
            long now = Time.get();
            // количество времени которое прошло с предыдущей итерации цикла
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            // переменная чтоб не отрисовывать сцену без изменений
            boolean render = false;
            //  каждая ровная единица delta говорит нам о том что необходимо сделать UPDATE
            delta += (elapsedTime / UPDATE_INTERVAL);
            // делаем UPDATE если мы пропустили сколько-то то все мы их тут будем делать
            while (delta > 1) {
                update();
                upd++;
                // вычитаем 1
                delta--;
                // если render уже не первый раз бежит
                if (render) {
                    updl++;
                } else {
                    // говорим что необходимо перерисовать сцену
                    render = true;
                }
            }

            // если необходимто что-то перерисовать
            if (render) {
                // то вызываем функцию render
                render();
                fps++;
            }
            // если изменений не было приостанавливаем поток чтоб дать возможность другим потокам поработать
            else {
                try {
                    Thread.sleep(IDEL_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // после прошествия секунды
            if (count >= Time.SECOND) {
                Display.setTitle(TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }*/


    }

    // любые ресурсы которые мы будем создавать которые необходимо закрывать будут тут
    private void cleanUp(){
        // уничтожаем окно после того как закроем игру
        Display.destroy();
    }
}
