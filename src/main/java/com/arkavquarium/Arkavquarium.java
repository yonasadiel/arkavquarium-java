package com.arkavquarium;

import System.nanoTime;
import com.arkavquarium.controllers.Aquarium;

public class Arkavquarium {

    public Arkavquarium() { }

    public static void main(String[] args) {
        long start = System.nanoTime();
        Aquarium aquarium = new Aquarium();
        boolean running = true;
        long prevTime = System.nanoTime() - start;

        while (running) {
            long now = System.nanoTime() - start;
            long secSinceLast = now - prevTime;
            prevTime = now;

            running = aquarium.main(new Long(secSinceLast).doubleValue() / 1000000000);
        }
    }
}