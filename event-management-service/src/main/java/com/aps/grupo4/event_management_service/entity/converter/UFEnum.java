package com.aps.grupo4.event_management_service.entity.converter;

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
    TO("Tocantins"),
    XX("Inválido");

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
        return XX.getEstado();
    }

    public static UFEnum getUFFromEstado(String nomeEstado) {
        for (UFEnum uf : UFEnum.values()) {
            if (uf.getEstado().equalsIgnoreCase(nomeEstado)) {
                return uf;
            }
        }
        return XX;
    }
}
