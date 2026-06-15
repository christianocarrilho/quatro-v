package org.sparrow.study.quatrov.usecase.pedido;

import java.util.Optional;
import java.util.UUID;
import org.sparrow.study.quatrov.core.domain.Pedido;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public interface PedidoRepository {

    void salvar(Pedido pedido);

    Optional<Pedido> buscarPorId(UUID id);
}