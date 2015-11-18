package com.eh.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.eh.jumper.R;

/**
 * Created by Zenner on 07/08/2015.
 */
public class Passaro {
    private static final Paint vermelho = Cores.getCorDoPassaro();

    public final int X = 100;
    public static final int RAIO = 50;
    private final Tela tela;
    private final Bitmap passaro;
    private final Som som;
    private Bitmap bp;

    private int altura;
    public Passaro(Tela tela , Context context,Som som){
        this.som = som;
        this.tela = tela;
        this.altura = 200;
        bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.passaro);
        this.passaro = Bitmap.createScaledBitmap(bp,RAIO *2 , RAIO *2 , false);

    }

    public void desenhaNo(Canvas canvas){
        canvas.drawBitmap(this.passaro,X-RAIO,altura - RAIO,null);
    }

    public void cai() {
        boolean chegouNoChao = altura + RAIO > tela.getAltura();
        if(!chegouNoChao){
            this.altura += 5;
        }
    }

    public void pula(){
        this.som.play(Som.PULO);
        if ((this.altura - 150) > RAIO) {
            this.altura -= 150;
        }else{
            this.altura = RAIO;
        }
    }

    public int getAltura() {
        return altura;
    }
}
