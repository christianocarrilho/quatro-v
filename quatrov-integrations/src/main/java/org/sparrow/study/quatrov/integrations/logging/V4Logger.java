package org.sparrow.study.quatrov.integrations.logging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.slf4j.MDC;
import static org.sparrow.study.quatrov.integrations.utils.V4Constants.TRACE_ID_KEY;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class V4Logger {

    private static final Logger LOG = Logger.getLogger(V4Logger.class);

    @Inject
    TraceExtractor traceExtractor;

    /**
     * Log de Informação que extrai o TraceID do objeto e o propaga na Thread automaticamente.
     * 
     * @param contexto
     * @param mensagem
     * @param msgParams
     */
    public void info(Traceable contexto, String mensagem, Object... msgParams) {
        processarContexto(contexto);
        LOG.info(formatarMensagem(mensagem, msgParams));
    }

    /**
     * Método simples para logs internos que não possuem um objeto Traceable (reaproveita o MDC atual).
     * 
     * @param mensagem
     * @param msgParams
     */
    public void info(String mensagem, Object... msgParams) {
        garantirTraceExistente();
        LOG.info(formatarMensagem(mensagem, msgParams));
    }

    /**
     * 
     * @param contexto
     * @param mensagem
     * @param msgParams 
     */
    public void debug(Traceable contexto, String mensagem, Object... msgParams) {
        processarContexto(contexto);
        LOG.debug(formatarMensagem(mensagem, msgParams));
    }

    /**
     * 
     * @param mensagem
     * @param msgParams 
     */
    public void debug(String mensagem, Object... msgParams) {
        garantirTraceExistente();
        LOG.debug(formatarMensagem(mensagem, msgParams));
    }

    /**
     * Log de Erro que extrai o TraceID do objeto e captura a Exception.
     * 
     * @param contexto
     * @param mensagem
     * @param ex
     * @param msgParams
     */
    public void error(Traceable contexto, String mensagem, Throwable ex, Object... msgParams) {
        processarContexto(contexto);
        LOG.error(formatarMensagem(mensagem, msgParams), ex);
    }

    /**
     * 
     * @param mensagem
     * @param ex
     * @param msgParams 
     */
    public void error(String mensagem, Throwable ex, Object... msgParams) {
        garantirTraceExistente();
        LOG.error(formatarMensagem(mensagem, msgParams), ex);
    }
    
    /**
     * 
     * @param mensagem
     * @param msgParams
     * @return 
     */
    private String formatarMensagem(String mensagem, Object... msgParams) {
        if (msgParams != null && msgParams.length > 0 && msgParams[0] != null) {
            mensagem = String.format(mensagem, msgParams);
        }
        return mensagem;
    }

    private void processarContexto(Traceable contexto) {
        String traceIdOriginal = (contexto != null) ? contexto.getTraceId() : null;
        String traceIdFinal = traceExtractor.extrairOuGerar(traceIdOriginal);
        MDC.put(TRACE_ID_KEY, traceIdFinal);
    }

    private void garantirTraceExistente() {
        if (MDC.get(TRACE_ID_KEY) == null) {
            MDC.put(TRACE_ID_KEY, traceExtractor.extrairOuGerar());
        }
    }
}
