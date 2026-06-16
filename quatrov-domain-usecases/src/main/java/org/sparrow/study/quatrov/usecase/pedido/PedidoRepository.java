package org.sparrow.study.quatrov.usecase.pedido;

import java.util.Optional;
import java.util.UUID;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.core.domain.StatusPedido;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public interface PedidoRepository {

    void incluir(Pedido pedido);

    Pedido atualizarStatus(UUID pedidoId, StatusPedido novoStatus);

    Optional<Pedido> buscarPorId(UUID id);
}