package com.project.api.academia.erro;

import com.project.api.academia.exception.ClienteNotFound;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> errorMensageMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                                    HttpServletRequest request,
                                                                                    BindingResult bindingResult){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválido(s)", bindingResult));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> dataIntegrityViolationExceptionException(DataIntegrityViolationException exception, HttpServletRequest request){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, "Cliente já cadastrado com email ou telefone informado."));
    }

    @ExceptionHandler(ClienteNotFound.class)
    public ResponseEntity<ErrorMessage> clienteNotFound(RuntimeException exception, HttpServletRequest request){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorMessage> noResourceFoundException(NoResourceFoundException exception, HttpServletRequest request){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorMessage> InvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exception, HttpServletRequest request){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Formato de Id inválido"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, HttpServletRequest request){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Os dados fornecidos na solicitação são inválidos. Verifique os parâmetros fornecidos e tente novamente."));
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorMessage> MissingPathVariableException(MissingPathVariableException exception, HttpServletRequest request){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "O caminho de variável está ausente na solicitação. Verifique os parâmetros fornecidos e tente novamente."));
    }
}
