package com.aps.grupo4.event_management_service.entity.converter;


import org.apache.commons.lang3.StringUtils;

public enum UFEnum {
    AC("Acre"),
    AL("Alagoas"),
    AP("Amapá"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceará"),
    DF("Distrito Federal"),
    ES("Espírito Santo"),
    GO("Goiás"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Pará"),
    PB("Paraíba"),
    PR("Paraná"),
    PE("Pernambuco"),
    PI("Piauí"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondônia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("São Paulo"),
    SE("Sergipe"),
    TO("Tocantins");

    private String estado;

    UFEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public static String getEstadoFromUF(String siglaUF) {
        for (UFEnum uf : UFEnum.values()) {
            if (uf.name().equalsIgnoreCase(siglaUF)) {
                return uf.getEstado();
            }
        }
        return null;
    }

    public static UFEnum getUFFromEstado(String nomeEstado) {
        for (UFEnum uf : UFEnum.values()) {
            if (uf.getEstado().equalsIgnoreCase(nomeEstado)) {
                return uf;
            }
        }
        return null;
    }

    public static boolean isValidUF(String siglaUF) {

        if (siglaUF == null) {
            return false;
        }

        for (UFEnum uf : UFEnum.values()) {
            if (uf.name().equalsIgnoreCase(siglaUF)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEstado(String estado) {

        if (estado == null) {
            return false;
        }

        for (UFEnum uf : UFEnum.values()) {
            if (StringUtils.stripAccents(uf.getEstado()).equalsIgnoreCase(StringUtils.stripAccents(estado))) {
                return true;
            }
        }
        return false;
    }
}
