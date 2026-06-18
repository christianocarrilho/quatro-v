package org.sparrow.study.quatrov.integrations.logging;

import java.util.UUID;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public interface Traceable {

    String getTraceId();

    /**
     *
     * @return
     */
    static String gerarTraceId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}