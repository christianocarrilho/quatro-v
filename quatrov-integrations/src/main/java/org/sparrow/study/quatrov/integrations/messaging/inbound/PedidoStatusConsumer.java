package org.sparrow.study.quatrov.integrations.messaging.inbound;

import org.sparrow.study.quatrov.integrations.messaging.inbound.dto.PedidoRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.sparrow.study.quatrov.core.domain.StatusPedido;
import org.sparrow.study.quatrov.integrations.logging.V4Logger;
import org.sparrow.study.quatrov.usecase.pedido.AtualizarStatusPedidoUseCase;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class PedidoStatusConsumer {

    @Inject
    AtualizarStatusPedidoUseCase atualizarStatusUseCase;

    @Inject
    V4Logger LOG;

    @Incoming("pedido-status-in") // Escuta o canal novo de transições
    public void consumirMudancaStatus(PedidoRecord record) {

        LOG.info(record, "🎰 [KAFKA] Evento de alteração de status do pedido %s", record.id());

        UUID pedidoId = UUID.fromString(record.id());
        StatusPedido novoStatus = StatusPedido.valueOf(record.status());

        atualizarStatusUseCase.processarAlteracaoStatus(pedidoId, novoStatus);
    }
}