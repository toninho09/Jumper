package com.eh.jumper.engine;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Zenner on 11/08/2015.
 */
public class GameOver {
    private final Tela tela;
    private Paint vermelho = Cores.getCorGameOver();
    public GameOver(Tela tela) {
        this.tela = tela;
    }

    public void desenhaNo(Canvas canvas) {
        String gameOver = "Game Over";
        canvas.drawText(gameOver,centralizaTexto(gameOver),tela.getAltura()/2,vermelho);
    }

    private int centralizaTexto(String texto){
        Rect limiteDoTexto = new Rect();
        vermelho.getTextBounds(texto,0,texto.length(),limiteDoTexto);
        int tamanhoHorizontal = (limiteDoTexto.right - limiteDoTexto.left) / 2;
        return (tela.getLargura() /2) - tamanhoHorizontal;
    }
}
