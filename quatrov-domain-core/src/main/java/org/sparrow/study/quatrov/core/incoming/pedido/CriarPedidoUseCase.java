package org.sparrow.study.quatrov.core.incoming.pedido;

import java.math.BigDecimal;
import org.sparrow.study.quatrov.core.domain.Pedido;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public interface CriarPedidoUseCase {

    Pedido executar(String clienteId, String item, BigDecimal valor);
}