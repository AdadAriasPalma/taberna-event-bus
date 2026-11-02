package com.golem.components;

import com.golem.core.eventbus.EventBus;
import com.golem.events.BebidaServidaEvent;
import com.golem.events.PedidoRealizadoEvent;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Barra {
    private final EventBus eventBus;
    private final Set<String> catalogoBebidas = Set.of("cerveza", "vino", "hidromiel", "ron", "agua");

    public Barra(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.suscribir(PedidoRealizadoEvent.class, this::onPedidoRealizado);
    }

    private void onPedidoRealizado(PedidoRealizadoEvent event) {
        for (String item : event.getItems()) {
            if (catalogoBebidas.contains(item.toLowerCase().trim())) {
                prepararBebidaAsync(event.getMesaId(), item);
            }
        }
    }

    private void prepararBebidaAsync(String mesaId, String bebida) {
        System.out.printf("  -> [BARRA] Preparando '%s' para la %s... (demora 2s)%n", bebida, mesaId);
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.printf("  ✓ [BARRA] '%s' servida con éxito para la %s.%n", bebida, mesaId);
                eventBus.publicar(new BebidaServidaEvent(mesaId, bebida));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}