package com.mnit.erp.content.exception;

import com.mnit.erp.exceptions.ServiceException;

public class MenuParentRequiredException extends ServiceException {

    public MenuParentRequiredException(String msg){
        super(msg);
    }

}
