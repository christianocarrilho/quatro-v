package org.sparrow.study.quatrov.integrations.handler.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;
import org.sparrow.study.quatrov.integrations.resource.dto.ErrorResponse;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@Provider
public class EntityNotFoundHandler implements ExceptionMapper<IllegalArgumentException> {

    private static final Logger LOG = Logger.getLogger(EntityNotFoundHandler.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(IllegalArgumentException exception) {

        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .error("Not Found")
                .message(exception.getMessage())
                .uriInfo(uriInfo)
                .build();

        MDC.put("traceId", error.getTraceId());

        // Agora o log sai com o TraceID embutido na linha automaticamente!
        LOG.errorf("Erro de Regra de Negócio: %s", exception.getMessage());

        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(error)
                .build();
    }
}