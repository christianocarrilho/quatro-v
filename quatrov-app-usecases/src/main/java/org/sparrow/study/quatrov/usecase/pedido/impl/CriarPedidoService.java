package org.sparrow.study.quatrov.usecase.pedido.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.usecase.pedido.PedidoEventPublisher;
import org.sparrow.study.quatrov.usecase.pedido.CriarPedidoUseCase;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class CriarPedidoService implements CriarPedidoUseCase {

    @Inject
    PedidoEventPublisher eventPublisher;

    @Override
    public Pedido executar(String clienteId, String item, java.math.BigDecimal valor) {
        Pedido novoPedido = new Pedido(clienteId, item, valor);

        // Regra de negócio orquestrada pelo Core

        return novoPedido;
    }
}
