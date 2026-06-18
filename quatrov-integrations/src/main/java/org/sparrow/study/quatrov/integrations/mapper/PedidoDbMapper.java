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
public interface PedidoDbMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "clienteId", source = "clienteId")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "valor", source = "valor")
    @Mapping(target = "status", source = "status")
    PedidoEntity toEntity(Pedido dominio);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "clienteId", source = "clienteId")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "valor", source = "valor")
    @Mapping(target = "status", source = "status") 
    Pedido toDomain(PedidoEntity entity);
}