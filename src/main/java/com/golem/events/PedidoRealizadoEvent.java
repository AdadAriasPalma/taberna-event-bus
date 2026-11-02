package com.golem.events;

import com.golem.core.eventbus.Event;
import java.util.Collections;
import java.util.List;

public final class PedidoRealizadoEvent implements Event {
    private final String mesaId;
    private final String pedidoId;
    private final List<String> items;
    private final long timestamp;

    public PedidoRealizadoEvent(String mesaId, String pedidoId, List<String> items) {
        this.mesaId = mesaId;
        this.pedidoId = pedidoId;
        this.items = (items != null) ? List.copyOf(items) : Collections.emptyList();
        this.timestamp = System.currentTimeMillis();
    }

    public String getMesaId() { return mesaId; }
    public String getPedidoId() { return pedidoId; }
    public List<String> getItems() { return items; }
    @Override public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "PedidoRealizadoEvent{mesa='" + mesaId + "', id='" + pedidoId + "', items=" + items + '}';
    }
}