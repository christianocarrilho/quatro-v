package org.sparrow.study.quatrov.integrations.messaging.inbound.dto;

import java.math.BigDecimal;
import org.sparrow.study.quatrov.integrations.logging.Traceable;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public record PedidoRecord(
        String id, String clienteId, 
        String item, BigDecimal valor, String status, 
        String traceId) implements Traceable {

    @Override
    public String getTraceId() {
        return this.traceId;
    }
}