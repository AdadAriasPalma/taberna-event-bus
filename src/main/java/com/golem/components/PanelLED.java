package com.golem.components;

import com.golem.core.eventbus.EventBus;
import com.golem.events.BandaTocandoEvent;
import com.golem.events.BebidaServidaEvent;
import com.golem.events.ComidaPreparadaEvent;
import com.golem.events.PedidoRealizadoEvent;

public class PanelLED {
    // Códigos ANSI para salida colorida en consola
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RESET = "\u001B[0m";

    public PanelLED(EventBus eventBus) {
        eventBus.suscribir(PedidoRealizadoEvent.class, this::onPedido);
        eventBus.suscribir(BebidaServidaEvent.class, this::onBebida);
        eventBus.suscribir(ComidaPreparadaEvent.class, this::onComida);
        eventBus.suscribir(BandaTocandoEvent.class, this::onMusica);
    }

    private void onPedido(PedidoRealizadoEvent e) {
        System.out.printf("%s[PANEL LED] 🔔 ¡Nuevo pedido #%s recibido para la %s!%s%n", 
                ANSI_YELLOW, e.getPedidoId(), e.getMesaId(), ANSI_RESET);
    }

    private void onBebida(BebidaServidaEvent e) {
        System.out.printf("%s[PANEL LED] 🍺 ¡%s lista y servida en la %s!%s%n", 
                ANSI_CYAN, e.getBebida().toUpperCase(), e.getMesaId(), ANSI_RESET);
    }

    private void onComida(ComidaPreparadaEvent e) {
        System.out.printf("%s[PANEL LED] 🍔 ¡%s lista para entregar (Pedido %s)!%s%n", 
                ANSI_GREEN, e.getPlato().toUpperCase(), e.getPedidoId(), ANSI_RESET);
    }

    private void onMusica(BandaTocandoEvent e) {
        System.out.printf("%s[PANEL LED] 🎶 EN VIVO: %s tocando \"%s\" 🎶%s%n", 
                ANSI_PURPLE, e.getNombreBanda(), e.getNombreCancion(), ANSI_RESET);
    }
}