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
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Exception exception) {
        ErrorResponse error = ErrorResponse.builder()
                .status(500)
                .error("Internal Server Error")
                .message("Transação não pôde ser concluída! Por favor, contate o suporte se persistir o problema.")
                .uriInfo(uriInfo)
                .build();

        MDC.put("traceId", error.getTraceId());

        // Agora o log sai com o TraceID embutido na linha automaticamente!
        LOG.errorf("CRITICAL UNHANDLED EXCEPTION: %s", exception.getMessage());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(error)
                .build();
    }
}