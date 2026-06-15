package org.sparrow.study.quatrov.integrations.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.integrations.mapper.PedidoMapper;
import org.sparrow.study.quatrov.integrations.orm.entity.pedido.PedidoEntity;
import org.sparrow.study.quatrov.integrations.orm.repository.pedido.PedidoPanacheRepository;
import org.sparrow.study.quatrov.usecase.pedido.PedidoRepository;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class PedidoDatabaseAdapter implements PedidoRepository {

    @Inject
    PedidoPanacheRepository panacheRepository;

    @Inject
    PedidoMapper pedidoMapper;

    @Override
    @Transactional
    public void salvar(Pedido pedido) {

        // 1. Converte Domínio Puro -> Entidade Panache
        PedidoEntity entity = pedidoMapper.toEntity(pedido);

        // 2. Salva usando o poder do Panache
        panacheRepository.persist(entity);
    }
}