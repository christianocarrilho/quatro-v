package org.sparrow.study.quatrov.usecase.pedido.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.core.domain.StatusPedido;
import org.sparrow.study.quatrov.usecase.pedido.AtualizarStatusPedidoUseCase;
import org.sparrow.study.quatrov.usecase.pedido.PedidoRepository;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class AtualizarStatusPedidoService implements AtualizarStatusPedidoUseCase {

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    public void processarAlteracaoStatus(UUID pedidoId, StatusPedido novoStatus) {

        Pedido pedido = pedidoRepository.buscarPorId(pedidoId);
        if (pedido.getStatus().podeTransicionarPara(novoStatus)) {
            pedidoRepository.atualizarStatus(pedidoId, novoStatus);
        }
    }
}
