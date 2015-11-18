package com.eh.jumper.engine;

import android.graphics.Canvas;

/**
 * Created by Zenner on 10/08/2015.
 */
public class Pontuacao {
    private final Som som;
    private int pontos = 0;

    public Pontuacao(Som som){
        this.som = som;
    }
    public void aumenta() {
        pontos++;
        som.play(Som.PONTOS);
    }

    public void desenhaNo(Canvas canvas){
        canvas.drawText(String.valueOf(pontos),100,100,Cores.getCorPontuacao());
    }
}
