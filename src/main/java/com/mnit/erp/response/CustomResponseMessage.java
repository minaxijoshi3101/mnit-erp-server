package com.mnit.erp.response;

import com.mnit.erp.util.ResponseMessageType;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponseMessage {
    ResponseMessageType messageType;
    String message;
    List<String> errors;
    Map<String, String> namedErrors;
    Object response;

    public CustomResponseMessage(ResponseMessageType responseMessageType, String message) {
        this.messageType = responseMessageType;
        this.message = message;
    }

    public CustomResponseMessage(ResponseMessageType responseMessageType, String message, Object response) {
        this(responseMessageType, message);
        this.response = response;
    }

}
