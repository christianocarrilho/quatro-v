package org.sparrow.study.quatrov.core.domain;

import java.util.Set;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public enum StatusPedido {

    RECEBIDO,
    PAGO,
    EM_PREPARACAO,
    PRONTO_PARA_ENTREGA,
    SAIU_PARA_ENTREGA,
    ENTREGUE,
    CANCELADO;

    public boolean podeTransicionarPara(StatusPedido novoStatus) {
        return switch (this) {
            case RECEBIDO -> Set.of(PAGO, CANCELADO).contains(novoStatus);
            case PAGO -> Set.of(EM_PREPARACAO, CANCELADO).contains(novoStatus);
            case EM_PREPARACAO -> Set.of(PRONTO_PARA_ENTREGA, CANCELADO).contains(novoStatus);
            case PRONTO_PARA_ENTREGA -> Set.of(SAIU_PARA_ENTREGA, CANCELADO).contains(novoStatus);
            case SAIU_PARA_ENTREGA -> Set.of(ENTREGUE).contains(novoStatus); // Não cancela mais aqui
            case ENTREGUE, CANCELADO -> false; // Estados finais, não vão para lugar nenhum
        };
    }
}