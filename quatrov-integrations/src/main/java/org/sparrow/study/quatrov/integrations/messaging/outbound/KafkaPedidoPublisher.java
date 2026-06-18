package org.sparrow.study.quatrov.integrations.messaging.outbound;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.integrations.logging.TraceExtractor;
import org.sparrow.study.quatrov.integrations.logging.V4Logger;
import org.sparrow.study.quatrov.integrations.messaging.inbound.dto.PedidoRecord;
import org.sparrow.study.quatrov.usecase.pedido.PedidoEventPublisher;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class KafkaPedidoPublisher implements PedidoEventPublisher {

    @Inject
    TraceExtractor traceExtrator;

    @Inject
    @Channel("pedidos-saida")
    Emitter<PedidoRecord> kafkaEmitter; // Enviando a entidade mapeada

    @Inject
    V4Logger LOGGER;

    @Override
    public void publicar(Pedido pedido) {

        String traceId = traceExtrator.getMDCTraceId();

        PedidoRecord request = new PedidoRecord(
                pedido.getId(), pedido.getClienteId(), pedido.getItem(), 
                pedido.getValor(), pedido.getStatus().name(), traceId);

        LOGGER.info(request, "Encaminhando pedido %s para esteira de confecção", pedido.getId());

        kafkaEmitter.send(request);
    }
}