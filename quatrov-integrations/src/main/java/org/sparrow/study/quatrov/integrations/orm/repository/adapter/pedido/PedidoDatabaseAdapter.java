package org.sparrow.study.quatrov.integrations.orm.repository.adapter.pedido;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.core.domain.StatusPedido;
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
    public void incluir(Pedido pedido) {

        // 1. Converte Domínio Puro -> Entidade Panache
        PedidoEntity entity = pedidoMapper.toEntity(pedido);

        // 2. Salva usando o poder do Panache
        panacheRepository.persist(entity);
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {

        PedidoEntity entity = panacheRepository.findById(id.toString());
        Pedido pedido = null;

        if (entity == null) {
            return Optional.ofNullable(pedido);
        }
        pedido = pedidoMapper.toDomain(entity);

        return Optional.of(pedido);
    }

    @Override
    @Transactional
    public Pedido atualizarStatus(UUID pedidoId, StatusPedido novoStatus) {

        String id = pedidoId.toString();

        PedidoEntity entityExistente = panacheRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));

        Pedido pedido = pedidoMapper.toDomain(entityExistente);
        pedido.mudarStatusPara(novoStatus);
        entityExistente.setStatus(pedido.getStatus().toString());

        return pedido;
    }
}