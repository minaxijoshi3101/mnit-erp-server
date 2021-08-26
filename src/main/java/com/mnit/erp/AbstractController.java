package com.mnit.erp;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.response.CustomResponseMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

public class AbstractController {

    @ExceptionHandler(ServiceException.class)
    public CustomResponseMessage handleServiceException(ServiceException serviceException, HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(serviceException.getHttpResponseStatusCode());
        return new CustomResponseMessage(serviceException.getResponseMessageType(), serviceException.getMessage(), serviceException.getErrors(), serviceException.getNamedErrors(), null);
    }
    
}
