package com.eh.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.eh.jumper.R;

/**
 * Created by Zenner on 07/08/2015.
 */
public class Cano {
    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private final Bitmap bp;
    private final Bitmap canoInferior;
    private final Bitmap canoSuperior;
    private int alturaDoCanoSuperior;
    private int posicao;
    private int alturaDoCanoInferior;
    private Tela tela;

    public Cano(Tela tela, int posicao,Context context){
        this.tela = tela;
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO + valorAleatorio();
        this.alturaDoCanoSuperior = 0+ TAMANHO_DO_CANO + valorAleatorio();
        this.posicao = posicao;
        bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        this.canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoInferior, false);
        this.canoSuperior = Bitmap.createScaledBitmap(bp,LARGURA_DO_CANO,this.alturaDoCanoSuperior,false);

    }

    private int valorAleatorio() {
        return (int) (Math.random() * 150);
    }

    public void desenhaNo(Canvas canvas){
        this.desenhaCanoInferiorNo(canvas);
        this.desenhaCanoSuperior(canvas);
    }

    private void desenhaCanoInferiorNo(Canvas canvas){
        //canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), Cores.getCorDoCano());
        canvas.drawBitmap(canoInferior,posicao,alturaDoCanoInferior,null);
    }

    private void desenhaCanoSuperior(Canvas canvas){
        //canvas.drawRect(posicao,0,posicao+LARGURA_DO_CANO, alturaDoCanoSuperior, Cores.getCorDoCano());
        canvas.drawBitmap(canoSuperior,posicao,0,null);
    }

    public void move() {
        posicao -=5;
    }

    public boolean saiuDaTela(){
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro) {
        return this.posicao - passaro.X < passaro.RAIO;
    }

    public boolean temColisaoVerticaoCom(Passaro passaro) {
        return passaro.getAltura() - passaro.RAIO < this.alturaDoCanoSuperior || passaro.getAltura() +passaro.RAIO > this.alturaDoCanoInferior;
    }
}
