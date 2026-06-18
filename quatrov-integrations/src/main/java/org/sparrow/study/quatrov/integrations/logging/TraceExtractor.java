package org.sparrow.study.quatrov.integrations.logging;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.MDC;
import static org.sparrow.study.quatrov.integrations.utils.V4Constants.TRACE_ID_KEY;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@ApplicationScoped
public class TraceExtractor {

    /**
     * 
     * @param traceId
     * @return 
     */
    public String extrairOuGerar(String traceId) {
        if (StringUtil.isNullOrEmpty(traceId)) {
            return Traceable.gerarTraceId();
        }
        return traceId;
    }

    public String extrairOuGerar() {
        return extrairOuGerar(null);
    }

    public String getMDCTraceId() {
        return MDC.get(TRACE_ID_KEY);
    }
}