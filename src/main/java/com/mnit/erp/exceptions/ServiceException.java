package com.mnit.erp.exceptions;

import com.mnit.erp.util.ResponseMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    Integer httpResponseStatusCode;
    ResponseMessageType responseMessageType;
    String message;
    List<String> errors;
    Map<String, String> namedErrors;
    {
        this.responseMessageType = ResponseMessageType.ERROR;
        this.httpResponseStatusCode = HttpServletResponse.SC_BAD_REQUEST;
    }

    public ServiceException(){
        this.message = "Server could not understand your request!";
    }

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(ResponseMessageType responseMessageType, String message){
        super(message);
        this.responseMessageType = responseMessageType;
        this.message = message;
    }

    public ServiceException(ResponseMessageType responseMessageType, String message, List<String> errors){
        this(responseMessageType, message);
        this.errors = errors;
    }

    public ServiceException(ResponseMessageType responseMessageType, String message, Map<String, String> namedErrors){
        this(responseMessageType, message);
        this.namedErrors = namedErrors;
    }

    public ServiceException(ResponseMessageType responseMessageType, String message, List<String> errors, Map<String, String> namedErrors){
        this(responseMessageType, message);
        this.errors = errors;
        this.namedErrors = namedErrors;
    }

}
