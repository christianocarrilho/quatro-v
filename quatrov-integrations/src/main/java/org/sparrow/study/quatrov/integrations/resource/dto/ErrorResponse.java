package org.sparrow.study.quatrov.integrations.resource.dto;

import jakarta.ws.rs.core.UriInfo;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ErrorResponse {

    private final OffsetDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final String traceId;

    // Construtor privado: obriga o uso do Builder
    private ErrorResponse(Builder builder) {
        this.timestamp = OffsetDateTime.now(); // Gerado automaticamente no momento do erro
        this.traceId = UUID.randomUUID().toString().substring(0, 8); // Id automático
        this.status = builder.status;
        this.error = builder.error;
        this.message = builder.message;
        this.path = builder.uriInfo != null ? builder.uriInfo.getPath() : "unknown";
    }

    // Ponto de entrada do padrão
    public static Builder builder() {
        return new Builder();
    }

    // A classe estática Builder (Inner Class)
    public static class Builder {
        private int status;
        private String error;
        private String message;
        private UriInfo uriInfo;

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder uriInfo(UriInfo uriInfo) {
            this.uriInfo = uriInfo;
            return this;
        }

        // Consolida a criação
        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

    // Getters para o Jackson (Omitidos aqui para economizar espaço)
    public OffsetDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public String getTraceId() { return traceId; }
}