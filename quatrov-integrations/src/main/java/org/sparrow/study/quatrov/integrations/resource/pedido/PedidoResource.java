package org.sparrow.study.quatrov.integrations.resource.pedido;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sparrow.study.quatrov.core.domain.Pedido;
import org.sparrow.study.quatrov.usecase.pedido.CriarPedidoUseCase;
import org.sparrow.study.quatrov.usecase.pedido.dto.PedidoDTO;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    CriarPedidoUseCase criarPedidoUseCase;

    @POST
    @RunOnVirtualThread
    public Response criarPedido(PedidoDTO request) {
        PedidoDTO pedidoProcessado = criarPedidoUseCase.executar(
                request.getClienteId(),
                request.getItem(),
                request.getValor()
        );

        return Response.status(Response.Status.ACCEPTED).entity(pedidoProcessado).build();
    }
}
