package com.golem.events;

import com.golem.core.eventbus.Event;

public final class BandaTocandoEvent implements Event {
    private final String nombreBanda;
    private final String nombreCancion;
    private final int duracionSegundos;
    private final long timestamp;

    public BandaTocandoEvent(String nombreBanda, String nombreCancion, int duracionSegundos) {
        this.nombreBanda = nombreBanda;
        this.nombreCancion = nombreCancion;
        this.duracionSegundos = duracionSegundos;
        this.timestamp = System.currentTimeMillis();
    }

    public String getNombreBanda() { return nombreBanda; }
    public String getNombreCancion() { return nombreCancion; }
    public int getDuracionSegundos() { return duracionSegundos; }
    @Override public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "BandaTocandoEvent{banda='" + nombreBanda + "', cancion='" + nombreCancion + "', duracion=" + duracionSegundos + "s}";
    }
}