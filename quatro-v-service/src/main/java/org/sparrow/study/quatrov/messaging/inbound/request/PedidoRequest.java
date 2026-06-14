package org.sparrow.study.quatrov.messaging.inbound.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public class PedidoRequest implements Serializable {
    
    private String clienteId;
    private String item;
    private BigDecimal valor;

    public PedidoRequest() {
    }

    public PedidoRequest(String clienteId, String item, BigDecimal valor) {
        this.clienteId = clienteId;
        this.item = item;
        this.valor = valor;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}