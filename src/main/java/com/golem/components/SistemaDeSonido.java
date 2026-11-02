package com.golem.components;

import com.golem.core.eventbus.EventBus;
import com.golem.events.BandaTocandoEvent;

public class SistemaDeSonido {
    public SistemaDeSonido(EventBus eventBus) {
        eventBus.suscribir(BandaTocandoEvent.class, this::onBandaTocando);
    }

    private void onBandaTocando(BandaTocandoEvent event) {
        System.out.printf("  🔊 [SONIDO] Ajustando ecualizador y ganancia para: '%s' [%s]%n", 
                event.getNombreCancion(), event.getNombreBanda());
    }
}