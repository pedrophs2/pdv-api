package br.com.leandrosnazareth.pdvapi.domain.model;

public enum Payment {

    DINHEIRO("Dinheiro"),
    DEBITO("Cartão de Débito"),
    CREDITO("Cartão de Crédito"),
    PIX("Pix"),
    VALE_ALIMENTACAO("Vale Alimentação"),
    CHEQUE("Cheque"),
    FIADO("Fiado");

    private String name;

    public String getDescricao() {
        return name;
    }

    private Payment(String name) {
        this.name = name;
    }
}
