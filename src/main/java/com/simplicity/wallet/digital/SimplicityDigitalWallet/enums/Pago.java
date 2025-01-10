package com.simplicity.wallet.digital.SimplicityDigitalWallet.enums;

public enum Pago {
    TRUE("true"),
    FALSE("false");

    private final String pagamento;

    Pago(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getPagamento() {
        return pagamento;
    }
}
