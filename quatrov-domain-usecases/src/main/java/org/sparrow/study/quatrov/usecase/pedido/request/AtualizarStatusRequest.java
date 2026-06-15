package org.sparrow.study.quatrov.usecase.pedido.request;

import org.sparrow.study.quatrov.core.domain.StatusPedido;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public record AtualizarStatusRequest(StatusPedido status) {}