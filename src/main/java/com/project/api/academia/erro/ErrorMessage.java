package com.project.api.academia.erro;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
public class ErrorMessage {

    private String path;
    private String method;
    private String statusText;
    private String message;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    public ErrorMessage(HttpServletRequest request, HttpStatus httpStatus, String mensage){
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = httpStatus.value();
        this.statusText = httpStatus.getReasonPhrase();
        this.message = mensage;
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus httpStatus, String mensage, BindingResult bindingResult){
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = httpStatus.value();
        this.statusText = httpStatus.getReasonPhrase();
        this.message = mensage;
        addErrors(bindingResult);
    }

    private void addErrors(BindingResult bindingResult) {
        this.errors = new HashMap<>();
        for(FieldError fieldError: bindingResult.getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
