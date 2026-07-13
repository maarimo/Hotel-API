package com.maarimo.hotel.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class QuartoRequest {

    @NotNull(message = "O número do quarto é obrigatório.")
    private Integer numero;

    @NotBlank(message = "O tipo do quarto é obrigatório.")
    private String tipo;

    @NotNull(message = "O valor da diária é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor da diária deve ser maior que zero.")
    private BigDecimal valorDiaria;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
