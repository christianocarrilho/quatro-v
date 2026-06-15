package org.sparrow.study.quatrov.integrations.messaging.inbound.deserializer;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.sparrow.study.quatrov.integrations.messaging.inbound.PedidoRecord;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public class PedidoDeserializer extends ObjectMapperDeserializer<PedidoRecord> {

    public PedidoDeserializer() {
        super(PedidoRecord.class);
    }
}