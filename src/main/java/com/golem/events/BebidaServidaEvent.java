package com.golem.events;

import com.golem.core.eventbus.Event;

public final class BebidaServidaEvent implements Event {
    private final String mesaId;
    private final String bebida;
    private final long timestamp;

    public BebidaServidaEvent(String mesaId, String bebida) {
        this.mesaId = mesaId;
        this.bebida = bebida;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMesaId() { return mesaId; }
    public String getBebida() { return bebida; }
    @Override public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "BebidaServidaEvent{mesa='" + mesaId + "', bebida='" + bebida + "'}";
    }
}