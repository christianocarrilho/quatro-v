package org.sparrow.study.quatrov.core.domain;

import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public class Pedido {

    private final String id;
    private final String clienteId;
    private final String item;
    private final BigDecimal valor;
    private String status;

    // Construtor para criar um novo pedido vindo do mundo externo
    public Pedido(String clienteId, String item, BigDecimal valor) {
        this.id = UUID.randomUUID().toString();
        this.clienteId = clienteId;
        this.item = item;
        this.valor = valor;
        this.status = "RECEBIDO";
    }

    // Lógica de negócio pura
    public void aprovar() {
        if (this.valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Pedido com valor inválido não pode ser aprovado.");
        }
        this.status = "APROVADO";
    }

    // Getters básicos (Imutabilidade)
    public String getId() {
        return id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void validar() {

    }
}