package org.sparrow.study.quatrov.integrations.messaging.inbound;

import org.sparrow.study.quatrov.integrations.messaging.inbound.dto.PedidoRecord;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class PedidoConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoConsumer.class);

    @Incoming("pedidos-entrada") // Conecta com o canal de entrada do Kafka no application.properties
    @RunOnVirtualThread // Executa o processamento do evento usando uma Virtual Thread do Java 21!
    public void consumirPedido(PedidoRecord pedido) throws InterruptedException {

        LOG.info("=== Novo evento recebido do Kafka ===");
        LOG.info("ID do Pedido: {}", pedido.id());
        LOG.info("Cliente: {}", pedido.clienteId());
        LOG.info("Item solicitado: {}", pedido.item());
        LOG.info("Valor: R$ {}", pedido.valor());
        
        // Simulando um processamento pesado/bloqueante (ex: validação de crédito ou envio para a cozinha)
        // Com Virtual Threads, dar um Thread.sleep NÃO trava o servidor! Ele apenas suspende a thread virtual de forma levíssima.
        Thread.sleep(3000); 

        LOG.info("Pedido {} processado com sucesso! Status atualizado para: APROVADO", pedido.id());
        System.out.println("=====================================\n");
    }
}