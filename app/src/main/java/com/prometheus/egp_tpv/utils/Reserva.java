package com.prometheus.egp_tpv.utils;

import java.util.Date;

public class Reserva {
    private String nomePrograma;
    private Date dataHoraExibicao;

    public Reserva(String nomePrograma, Date dataHoraExibicao) {
        this.nomePrograma = nomePrograma;
        this.dataHoraExibicao = dataHoraExibicao;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public Date getDataHoraExibicao() {
        return dataHoraExibicao;
    }
}
