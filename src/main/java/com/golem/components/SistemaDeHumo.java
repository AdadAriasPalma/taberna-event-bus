package com.golem.components;

import com.golem.core.eventbus.EventBus;
import com.golem.events.BandaTocandoEvent;

public class SistemaDeHumo {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public SistemaDeHumo(EventBus eventBus) {
        eventBus.suscribir(BandaTocandoEvent.class, this::onCancion);
    }

    private void onCancion(BandaTocandoEvent event) {
        if (event.getNombreCancion().equalsIgnoreCase("Through the Fire and Flames")) {
            System.out.printf("%s  💨🔥 [SISTEMA DE HUMO] ¡CANCIÓN ÉPICA DETECTADA! Disparando ráfagas de CO2 y pirotecnia fría. %s%n",
                    ANSI_RED, ANSI_RESET);
        }
    }
}