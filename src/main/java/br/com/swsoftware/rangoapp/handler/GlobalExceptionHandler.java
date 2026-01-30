package br.com.swsoftware.rangoapp.handler;

import br.com.swsoftware.rangoapp.exception.BusinessException;
import br.com.swsoftware.rangoapp.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ProblemDetail handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

    problemDetail.setTitle("Recurso não encontrado");
    problemDetail.setDetail(ex.getMessage());
    problemDetail.setType(java.net.URI.create("https://rangoapp/errors/not-found"));
    problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

    return problemDetail;
  }

  @ExceptionHandler(BusinessException.class)
  public ProblemDetail handleBusiness(BusinessException ex, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

    problemDetail.setTitle("Erro de regra de negócio");
    problemDetail.setDetail(ex.getMessage());
    problemDetail.setType(
        java.net.URI.create("https://rangoapp/errors/business")
    );
    problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

    return problemDetail;
  }
}
