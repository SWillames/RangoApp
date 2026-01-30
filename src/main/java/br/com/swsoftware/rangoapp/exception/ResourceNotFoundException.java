package br.com.swsoftware.rangoapp.exception;

public class ResourceNotFoundException extends RuntimeException{

  public ResourceNotFoundException(String message){
    super(message);
  }

}
