package com.golem.components;

import com.golem.core.eventbus.EventBus;
import com.golem.events.BandaTocandoEvent;

public class Banda {
    private final EventBus eventBus;
    private final String nombreBanda;

    public Banda(EventBus eventBus, String nombreBanda) {
        this.eventBus = eventBus;
        this.nombreBanda = nombreBanda;
    }

    public void tocarCancion(String nombreCancion, int duracionSegundos) {
        System.out.printf("%n[ESCENARIO] 🎸 %s sube al escenario e inicia: '%s' (%ds)%n", 
                nombreBanda, nombreCancion, duracionSegundos);
        eventBus.publicar(new BandaTocandoEvent(nombreBanda, nombreCancion, duracionSegundos));
    }
}