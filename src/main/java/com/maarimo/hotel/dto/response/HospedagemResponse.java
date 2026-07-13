package com.maarimo.hotel.dto.response;

import com.maarimo.hotel.entity.StatusHospedagem;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HospedagemResponse {

    private Long id;
    private String hospede;
    private Integer quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private BigDecimal valorTotal;
    private StatusHospedagem status;

    public Long getId() {
        return id;
    }

    public String getHospede() {
        return hospede;
    }

    public Integer getQuarto() {
        return quarto;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public StatusHospedagem getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHospede(String hospede) {
        this.hospede = hospede;
    }

    public void setQuarto(Integer quarto) {
        this.quarto = quarto;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(StatusHospedagem status) {
        this.status = status;
    }
}