package org.sparrow.study.quatrov.integrations.orm.entity.pedido;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Christiano H Carrilho Lopes da Silva <csilva@detran.ms.gov.br>
 */
@Entity
@Table(name = "PEDIDOS")
public class PedidoEntity {

    @Id
    private String id; // ID vem pronto do domínio (UUID)
    private String clienteId;
    private String item;
    private BigDecimal valor;
    private String status;

    // Construtor padrão obrigatório pelo Hibernate
    public PedidoEntity() {
    }

    // Getters e Setters (que o MapStruct vai usar para converter)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.clienteId);
        hash = 37 * hash + Objects.hashCode(this.item);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PedidoEntity other = (PedidoEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.clienteId, other.clienteId)) {
            return false;
        }
        return Objects.equals(this.item, other.item);
    }

    @Override
    public String toString() {
        return "PedidoEntity{" + "id=" + id + ", clienteId=" + clienteId + 
                ", item=" + item + ", valor=" + valor + ", status=" + status + '}';
    }
}