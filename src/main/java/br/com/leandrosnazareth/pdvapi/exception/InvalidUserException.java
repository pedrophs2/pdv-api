package br.com.leandrosnazareth.pdvapi.exception;

public class InvalidUserException extends Exception {

    public InvalidUserException() {
        super("Usuário inválido para a operação.");
    }

}
