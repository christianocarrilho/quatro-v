package org.sparrow.study.quatrov.integrations.messaging.inbound;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class PedidoStatusConsumer {

    private static final Logger LOG = Logger.getLogger(PedidoStatusConsumer.class);

    @Incoming("pedido-status-in") // Escuta o canal novo de transições
    public void consumirMudancaStatus(PedidoRecord record) {

        // Gera o Trace ID assíncrono para o Loki pegar
        String asyncTraceId = "asnc-" + UUID.randomUUID().toString().substring(0, 4);
        MDC.put("traceId", asyncTraceId);

        LOG.infof("🎰 [KAFKA AUDIT] Evento capturado no tópico 'pedido-status' -> ID: %s | Novo Status: %s",
                record.id(), record.status());

        MDC.clear();
    }
}
