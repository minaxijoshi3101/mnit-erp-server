package com.mnit.erp.exceptions.response;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Collections;

@XmlRootElement
public class ErrorResponse {

    private Collection<String> errors;

    public ErrorResponse() {
    }

    public ErrorResponse(Collection<String> errors) {
        this.errors = errors;
    }

    public ErrorResponse(String error) {
        this(Collections.singletonList(error));
    }

    public Collection<String> getErrors() {
        return errors;
    }
    public void setErrors(Collection<String> errors) {
        this.errors = errors;
    }

}