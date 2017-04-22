package com.ekey.configs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Random;

/**
 * Created by root on 1/31/17.
 */
public class CustomPassword {

    public String generate() {
        String pass  = "";
        Random r     = new Random();
        int cntchars = 8;

        for (int i = 0; i < cntchars; ++i) {
            char next = 0;
            int range = 10;

            switch(r.nextInt(3)) {
                case 0: {next = '0'; range = 10;} break;
                case 1: {next = 'a'; range = 26;} break;
                case 2: {next = 'A'; range = 26;} break;
            }

            pass += (char)((r.nextInt(range)) + next);
        }

        return pass;
    }


}
