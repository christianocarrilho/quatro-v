package org.sparrow.study.quatrov.core.outgoing.pedido;

import org.sparrow.study.quatrov.core.domain.Pedido;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public interface PedidoEventPublisher {

    void publicar(Pedido pedido);
}