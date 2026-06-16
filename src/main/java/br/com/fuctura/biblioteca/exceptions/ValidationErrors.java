package br.com.fuctura.biblioteca.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandardError {

    List<FieldErrors> erros = new ArrayList<>();


    public ValidationErrors(LocalDateTime timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldErrors> getErros(){
        return erros;
    }

    public void addErrors(String field, String defaultMessage ){
        this.erros.add(new FieldErrors(field,defaultMessage));
    }
}
