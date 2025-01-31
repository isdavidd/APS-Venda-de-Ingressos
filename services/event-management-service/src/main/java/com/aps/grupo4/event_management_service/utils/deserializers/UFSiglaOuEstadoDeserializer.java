package com.aps.grupo4.event_management_service.utils.deserializers;

import com.aps.grupo4.event_management_service.entity.converter.UFEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class UFSiglaOuEstadoDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String siglaUFOuNomeEstado = p.getText();

        if (siglaUFOuNomeEstado == null || siglaUFOuNomeEstado.trim().isEmpty()) {
            return null;
        }

        siglaUFOuNomeEstado = StringUtils.stripAccents(siglaUFOuNomeEstado.trim().toUpperCase());

        return siglaUFOuNomeEstado;
    }
}
