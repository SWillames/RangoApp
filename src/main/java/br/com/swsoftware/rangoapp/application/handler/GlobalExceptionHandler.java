package br.com.swsoftware.rangoapp.application.handler;

import br.com.swsoftware.rangoapp.exception.BusinessException;
import br.com.swsoftware.rangoapp.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ProblemDetail handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

    problemDetail.setTitle("Recurso não encontrado");
    problemDetail.setDetail(ex.getMessage());
    problemDetail.setType(URI.create("https://rangoapp/errors/not-found"));
    problemDetail.setInstance(URI.create(request.getRequestURI()));

    return problemDetail;
  }

  @ExceptionHandler(BusinessException.class)
  public ProblemDetail handleBusiness(BusinessException ex, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

    problemDetail.setTitle("Erro de regra de negócio");
    problemDetail.setDetail(ex.getMessage());
    problemDetail.setType(
        URI.create("https://rangoapp/errors/business")
    );
    problemDetail.setInstance(URI.create(request.getRequestURI()));

    return problemDetail;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handlerValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

    problemDetail.setTitle("Erro de Validação");
    problemDetail.setType(URI.create("https://rangoapp/errors/validation"));
    problemDetail.setInstance(URI.create(request.getRequestURI()));

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    problemDetail.setProperty("errors", errors);

    return problemDetail;
  }
}
