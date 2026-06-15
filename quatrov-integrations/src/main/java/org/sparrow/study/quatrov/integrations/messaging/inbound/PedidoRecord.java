package org.sparrow.study.quatrov.integrations.messaging.inbound;

import java.math.BigDecimal;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public record PedidoRecord(
    String id,
    String clienteId,
    String item,
    BigDecimal valor,
    String status
) {}