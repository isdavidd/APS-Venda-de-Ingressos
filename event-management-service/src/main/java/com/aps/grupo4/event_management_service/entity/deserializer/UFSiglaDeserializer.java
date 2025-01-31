package com.aps.grupo4.event_management_service.entity.deserializer;

import com.aps.grupo4.event_management_service.entity.converter.UFEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class UFSiglaDeserializer extends JsonDeserializer<UFEnum> {

    @Override
    public UFEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String siglaUF = p.getText();

        if (siglaUF == null || siglaUF.trim().isEmpty()) {
            return null;
        }

        siglaUF = siglaUF.trim().toUpperCase();

        if (UFEnum.isValidUF(siglaUF)) {
            return UFEnum.valueOf(siglaUF);
        }

        return null;
    }
}
