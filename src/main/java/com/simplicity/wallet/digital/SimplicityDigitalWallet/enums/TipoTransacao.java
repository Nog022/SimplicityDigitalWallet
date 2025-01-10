package com.simplicity.wallet.digital.SimplicityDigitalWallet.enums;

public enum TipoTransacao {
    PIX("pix"),
    DEPOSITO("deposito"),
    SAQUE("saque");

    private final String tipoTransacao;

    TipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }
}
