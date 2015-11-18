package com.eh.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.eh.jumper.R;

/**
 * Created by Zenner on 06/08/2015.
 */
public class Game extends SurfaceView  implements Runnable ,View.OnTouchListener{

    private Som som;
    private Bitmap background;
    private boolean isRunning = true;

    private final SurfaceHolder holder = getHolder();

    private int altura =0;
    private int largura = 0;
    private Passaro passaro;
    private Canos canos;
    private Tela tela;
    private long lastTime;
    private Pontuacao pontuacao;
    private Context context;

    public Game(Context context){
        super(context);
        this.context = context;
        this.tela = new Tela(context);
        setOnTouchListener(this);
        this.som = new Som(context);
        inicializaElementos();
        this.lastTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (isRunning){
            if(!holder.getSurface().isValid()) continue;
            Canvas canvas = holder.lockCanvas();
            canvas.drawBitmap(background, 0, 0, null);
            canos.move();
            canos.desenhaNo(canvas);
            passaro.cai();
            passaro.desenhaNo(canvas);
            pontuacao.desenhaNo(canvas);
            canvas.drawText(Integer.toString(this.calculaFPS()), 0, 100, Cores.getCorTexto());
            if(new VerificadorDeColisao(passaro,canos).temColisao()){
                new GameOver(tela).desenhaNo(canvas);
                this.isRunning = false;
            }
            holder.unlockCanvasAndPost(canvas);
        }

    }

    private void inicializaElementos(){
        this.passaro = new Passaro(this.tela,this.context,this.som);
        this.pontuacao = new Pontuacao(this.som);
        this.canos = new Canos(this.tela,this.pontuacao,this.context,this.som);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        this.background = Bitmap.createScaledBitmap(back,tela.getLargura(),tela.getAltura(),false);
    }

    public void cancela() {
        this.isRunning = false;
    }

    public void inicia() {
        this.isRunning = true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float y = event.getY();
        float x = event.getX();
        Log.d("x",String.valueOf(x));
        Log.d("y",String.valueOf(y));
        if(!this.isRunning){
            inicializaElementos();
            this.isRunning = true;
            new Thread(this).start();
        }else {
            this.passaro.pula();
        }
        return false;
    }

    private int calculaFPS(){
        long passado = System.currentTimeMillis() - this.lastTime;
        this.lastTime = System.currentTimeMillis();
        return (int)(1000/passado);
    }
}
