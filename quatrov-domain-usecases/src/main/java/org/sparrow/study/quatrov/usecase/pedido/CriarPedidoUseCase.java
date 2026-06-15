package org.sparrow.study.quatrov.usecase.pedido;

import java.math.BigDecimal;
import org.sparrow.study.quatrov.usecase.pedido.dto.PedidoDTO;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public interface CriarPedidoUseCase {

    PedidoDTO executar(String clienteId, String item, BigDecimal valor);
}