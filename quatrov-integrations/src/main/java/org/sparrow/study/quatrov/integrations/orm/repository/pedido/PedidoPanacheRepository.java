package org.sparrow.study.quatrov.integrations.orm.repository.pedido;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.sparrow.study.quatrov.integrations.orm.entity.pedido.PedidoEntity;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class PedidoPanacheRepository implements PanacheRepositoryBase<PedidoEntity, String> {
}