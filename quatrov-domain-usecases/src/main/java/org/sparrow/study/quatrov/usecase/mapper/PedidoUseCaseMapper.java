package org.sparrow.study.quatrov.usecase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.usecase.pedido.dto.PedidoDTO;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface PedidoUseCaseMapper {

    @Mapping(target = "clienteId", source = "clienteId")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "valor", source = "valor")
    PedidoDTO toDTO(Pedido domain);
}