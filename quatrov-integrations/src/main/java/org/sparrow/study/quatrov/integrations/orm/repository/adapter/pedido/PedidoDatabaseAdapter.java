package org.sparrow.study.quatrov.integrations.orm.repository.adapter.pedido;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.core.domain.StatusPedido;
import org.sparrow.study.quatrov.integrations.logging.V4Logger;
import org.sparrow.study.quatrov.integrations.orm.entity.pedido.PedidoEntity;
import org.sparrow.study.quatrov.integrations.orm.repository.pedido.PedidoPanacheRepository;
import org.sparrow.study.quatrov.usecase.pedido.PedidoRepository;
import org.sparrow.study.quatrov.integrations.mapper.PedidoDbMapper;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class PedidoDatabaseAdapter implements PedidoRepository {

    @Inject
    PedidoPanacheRepository repository;

    @Inject
    PedidoDbMapper pedidoMapper;

    @Inject
    V4Logger LOGGER;

    @Override
    @Transactional
    public void incluir(Pedido pedido) {

        // 1. Converte Domínio Puro -> Entidade Panache
        PedidoEntity entity = pedidoMapper.toEntity(pedido);

        // 2. Salva usando o poder do Panache
        repository.persist(entity);
    }

    @Override
    @ActivateRequestContext
    public Pedido buscarPorId(UUID id) {

        PedidoEntity entity = getPedidoEntityById(id);
        return pedidoMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void atualizarStatus(UUID pedidoId, StatusPedido novoStatus) {

        repository.getEntityManager()
                .createNativeQuery("UPDATE PEDIDOS SET status = :status WHERE id = :pedidoId")
                .setParameter("status", novoStatus.name())
                .setParameter("pedidoId", pedidoId.toString())
                .executeUpdate();

        LOGGER.info("Status do pedido %s para %s", pedidoId.toString(), novoStatus);
    }

    private PedidoEntity getPedidoEntityById(UUID id) {
        return repository.findByIdOptional(id.toString())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
    }
}