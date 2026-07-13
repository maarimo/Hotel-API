package com.maarimo.hotel.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class HospedagemRequest {

    @NotNull(message = "O hóspede é obrigatório.")
    private Long hospedeId;

    @NotNull(message = "O quarto é obrigatório.")
    private Long quartoId;

    @NotNull(message = "A data de entrada é obrigatória.")
    private LocalDate dataEntrada;

    @NotNull(message = "A data de saída é obrigatória.")
    @Future(message = "A data de saída deve ser uma data futura.")
    private LocalDate dataSaida;

    public Long getHospedeId() {
        return hospedeId;
    }

    public void setHospedeId(Long hospedeId) {
        this.hospedeId = hospedeId;
    }

    public Long getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(Long quartoId) {
        this.quartoId = quartoId;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }
}
