package com.mnit.erp.exceptions;

import javax.servlet.http.HttpServletResponse;

public class AccessDeniedException extends ServiceException {
    {
        this.httpResponseStatusCode = HttpServletResponse.SC_FORBIDDEN;
    }
    public AccessDeniedException(String msg) {
        super(msg);
    }
}
