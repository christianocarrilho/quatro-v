package org.sparrow.study.quatrov.usecase.pedido.impl;

import jakarta.inject.Inject;
import java.util.UUID;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.core.domain.StatusPedido;
import org.sparrow.study.quatrov.usecase.pedido.AtualizarStatusPedidoUseCase;
import org.sparrow.study.quatrov.usecase.pedido.PedidoEventPublisher;
import org.sparrow.study.quatrov.usecase.pedido.PedidoRepository;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public class AtualizarStatusPedidoService implements AtualizarStatusPedidoUseCase {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    PedidoEventPublisher pedidoEventPublisher;

    @Override
    public void processarAlteracaoStatus(UUID pedidoId, StatusPedido novoStatus) {

        Pedido pedido = pedidoRepository.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + pedidoId));

        pedido.mudarStatusPara(novoStatus);

        pedidoRepository.salvar(pedido);
        pedidoEventPublisher.publicar(pedido); // Avisa o Kafka que o pedido foi cancelado
    }
}
