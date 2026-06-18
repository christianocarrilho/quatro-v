package org.sparrow.study.quatrov.usecase.pedido.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public class PedidoDTO implements Serializable {
    
    private String clienteId;
    private String item;
    private BigDecimal valor;

    public PedidoDTO() {
    }

    public PedidoDTO(String clienteId, String item, BigDecimal valor) {
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