package com.eh.jumper.engine;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Zenner on 07/08/2015.
 */
public class Canos {
    private final Context context;
    private final Som som;
    private Tela tela;
    private List<Cano> canos = new ArrayList<Cano>();
    private static final int QUANTIDADE_DE_CANOS = 5 ;
    private static final int DISTANCIA_ENTRE_CANOS = 250;
    private Pontuacao pontuacao;

    public Canos(Tela tela,Pontuacao pontuacao,Context context,Som som){
        this.context = context;
        this.som = som;
        int posicaoInicial = 200;
        this.pontuacao = pontuacao;
        this.tela = tela;
        for(int i =0; i <QUANTIDADE_DE_CANOS ; i++){
            posicaoInicial +=DISTANCIA_ENTRE_CANOS;
            canos.add(new Cano(tela,posicaoInicial,context));
        }
    }

    public void desenhaNo(Canvas canvas) {
        for(Cano cano : canos){
            cano.desenhaNo(canvas);
        }
    }

    public void move() {
        ListIterator<Cano> iterator = canos.listIterator();
        while(iterator.hasNext()){
            Cano cano = (Cano) iterator.next();
            cano.move();
            if(cano.saiuDaTela()){
                pontuacao.aumenta();
                iterator.remove();
                iterator.add(new Cano(tela,getMaximo()+DISTANCIA_ENTRE_CANOS,this.context));
            }
        }
    }

    private int getMaximo() {
        int maximo = 0;
        for(Cano cano : canos){
            maximo = Math.max(cano.getPosicao(),maximo);
        }
        return maximo;
    }

    public boolean temColisaoCom(Passaro passaro) {
        for (Cano cano : canos){
            if(cano.temColisaoHorizontalCom(passaro) && cano.temColisaoVerticaoCom(passaro)){
                som.play(Som.COLISAO);
                return true;
            }
        }
        return false;
    }
}
