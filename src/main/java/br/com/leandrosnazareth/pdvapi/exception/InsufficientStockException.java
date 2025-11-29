package br.com.leandrosnazareth.pdvapi.exception;

public class InsufficientStockException extends Exception {
    
    public InsufficientStockException(String productName) {
        super("Não foi possível realizar a venda, produto " + productName + " não possui quantidade em estoque o suficiente.");
    }

}
