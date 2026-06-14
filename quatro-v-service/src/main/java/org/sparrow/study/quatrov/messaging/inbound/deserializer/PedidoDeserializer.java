package org.sparrow.study.quatrov.messaging.inbound.deserializer;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.sparrow.study.quatrov.core.domain.Pedido;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public class PedidoDeserializer extends ObjectMapperDeserializer<Pedido> {
    public PedidoDeserializer() {
        super(Pedido.class);
    }
}