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
    private StatusPedido status;

    public Pedido(String clienteId, String item, BigDecimal valor) {
        this.id = UUID.randomUUID().toString();
        this.clienteId = clienteId;
        this.item = item;
        this.valor = valor;
        this.status = StatusPedido.RECEBIDO;
    }

    public String getId() {
        return id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getItem() {
        return item;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void mudarStatusPara(StatusPedido novoStatus) {
        if (!this.status.podeTransicionarPara(novoStatus)) {
            throw new IllegalStateException(
                    String.format("Transição de status inválida: Não é permitido mudar de %s para %s.",
                            this.status, novoStatus)
            );
        }

        // Se a transição for permitida, o domínio executa a alteração interna
        this.status = novoStatus;
    }
}