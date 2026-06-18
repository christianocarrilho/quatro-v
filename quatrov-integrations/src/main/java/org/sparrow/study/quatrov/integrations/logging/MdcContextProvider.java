package org.sparrow.study.quatrov.integrations.logging;

import java.util.Map;
import org.eclipse.microprofile.context.spi.ThreadContextProvider;
import org.eclipse.microprofile.context.spi.ThreadContextSnapshot;
import org.slf4j.MDC;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
public class MdcContextProvider implements ThreadContextProvider {

    @Override
    public ThreadContextSnapshot currentContext(Map<String, String> props) {

        // 1. Tira uma "foto" do MDC atual da Thread que está enviando a tarefa
        Map<String, String> contextoPai = MDC.getCopyOfContextMap();
        
        return () -> {

            // 2. Antes da Thread do pool rodar a tarefa, injeta a foto nela
            Map<String, String> contextoAnterior = MDC.getCopyOfContextMap();
            if (contextoPai != null) {
                MDC.setContextMap(contextoPai);
            } else {
                MDC.clear();
            }
            
            // 3. Essa é a mágica: O Quarkus roda essa Lambda QUANDO A THREAD TERMINA
            return () -> {
                // Restaura o estado anterior (limpando o MDC para a Thread voltar pura pro pool)
                if (contextoAnterior != null) {
                    MDC.setContextMap(contextoAnterior);
                } else {
                    MDC.clear();
                }
            };
        };
    }

    @Override
    public ThreadContextSnapshot clearedContext(Map<String, String> props) {
        return () -> {
            MDC.clear();
            return MDC::clear;
        };
    }

    @Override
    public String getThreadContextType() {
        return "SLF4J_MDC"; // Nome do nosso grampo
    }
}
