package org.sparrow.study.quatrov.integrations.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.integrations.orm.entity.pedido.PedidoEntity;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface PedidoMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "clienteId", source = "clienteId")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "valor", source = "valor")
    @Mapping(target = "status", source = "status")
    PedidoEntity toEntity(Pedido dominio);

    // Converte do Banco (JPA) de volta para o Domínio se você precisar consultar
    Pedido toDomain(PedidoEntity entity);
}
