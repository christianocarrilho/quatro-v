package org.sparrow.study.quatrov.usecase.pedido.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.usecase.pedido.PedidoEventPublisher;
import org.sparrow.study.quatrov.usecase.pedido.CriarPedidoUseCase;
import org.sparrow.study.quatrov.usecase.pedido.PedidoRepository;
import org.sparrow.study.quatrov.usecase.pedido.dto.PedidoDTO;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class CriarPedidoService implements CriarPedidoUseCase {

    @Inject
    PedidoRepository repositoryPort;
    
    @Inject
    PedidoEventPublisher eventPublisher;

    @Override
    public PedidoDTO executar(String clienteId, String item, java.math.BigDecimal valor) {

        Pedido novoPedido = Pedido.criarNovo(clienteId, item, valor);

        // grava no H2
        repositoryPort.incluir(novoPedido);

        // envia para Kafka
        eventPublisher.publicar(novoPedido);

        return PedidoDTO.toDto(novoPedido);
    }
}
