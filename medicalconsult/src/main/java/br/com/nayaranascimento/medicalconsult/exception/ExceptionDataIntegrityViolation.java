package br.com.nayaranascimento.medicalconsult.exception;

public class ExceptionDataIntegrityViolation extends RuntimeException {
    public ExceptionDataIntegrityViolation(String message){
        super(message);
    }
}
