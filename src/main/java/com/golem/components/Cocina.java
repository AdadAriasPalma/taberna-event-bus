package com.golem.components;

import com.golem.core.eventbus.EventBus;
import com.golem.events.ComidaPreparadaEvent;
import com.golem.events.PedidoRealizadoEvent;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Cocina {
    private final EventBus eventBus;
    private final Set<String> catalogoComida = Set.of("hamburguesa", "pizza", "costillas", "papas", "alitas");

    public Cocina(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.suscribir(PedidoRealizadoEvent.class, this::onPedidoRealizado);
    }

    private void onPedidoRealizado(PedidoRealizadoEvent event) {
        for (String item : event.getItems()) {
            if (catalogoComida.contains(item.toLowerCase().trim())) {
                cocinarPlatoAsync(event.getPedidoId(), item);
            }
        }
    }

    private void cocinarPlatoAsync(String pedidoId, String plato) {
        System.out.printf("  -> [COCINA] Encendiendo fogones para '%s' (Pedido: %s)... (demora 5s)%n", plato, pedidoId);
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.printf("  ✓ [COCINA] '%s' emplatada y lista para el pedido %s.%n", plato, pedidoId);
                eventBus.publicar(new ComidaPreparadaEvent(pedidoId, plato));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}