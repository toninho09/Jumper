package com.eh.jumper.engine;

/**
 * Created by Zenner on 10/08/2015.
 */
public class VerificadorDeColisao {
    private final Passaro passaro;
    private final Canos canos;

    public VerificadorDeColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao() {
        return  canos.temColisaoCom(passaro);
    }
}
