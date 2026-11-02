package com.golem.events;

import com.golem.core.eventbus.Event;

public final class ComidaPreparadaEvent implements Event {
    private final String pedidoId;
    private final String plato;
    private final long timestamp;

    public ComidaPreparadaEvent(String pedidoId, String plato) {
        this.pedidoId = pedidoId;
        this.plato = plato;
        this.timestamp = System.currentTimeMillis();
    }

    public String getPedidoId() { return pedidoId; }
    public String getPlato() { return plato; }
    @Override public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "ComidaPreparadaEvent{pedidoId='" + pedidoId + "', plato='" + plato + "'}";
    }
}