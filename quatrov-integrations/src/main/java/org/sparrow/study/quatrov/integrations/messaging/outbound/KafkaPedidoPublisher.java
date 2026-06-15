package org.sparrow.study.quatrov.integrations.messaging.outbound;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.usecase.pedido.PedidoEventPublisher;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class KafkaPedidoPublisher implements PedidoEventPublisher {

    @Inject
    @Channel("pedidos-saida")
    Emitter<Pedido> kafkaEmitter; // Enviando a entidade mapeada

    @Override
    public void publicar(Pedido pedido) {
        kafkaEmitter.send(pedido);
    }
}