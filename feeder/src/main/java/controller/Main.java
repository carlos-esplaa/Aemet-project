package controller;

import controller.Controller;
import model.AemetWeatherException;

import java.util.Timer;
import java.util.TimerTask;


public class Main {
        public static void main(String[] args) throws AemetWeatherException {
            Timer timer;
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run()
                {
                    Controller controller = new Controller();
                    try {
                        controller.controller();
                    } catch (AemetWeatherException e) {
                        throw new RuntimeException(e);
                    }

                }
            };
            timer.schedule(task, 0, 60*60*1000);
        }

}
