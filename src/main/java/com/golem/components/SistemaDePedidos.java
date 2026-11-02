package com.golem.components;

import com.golem.core.eventbus.EventBus;
import com.golem.events.PedidoRealizadoEvent;
import java.util.List;

public class SistemaDePedidos {
    private final EventBus eventBus;

    public SistemaDePedidos(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void realizarPedido(String mesaId, String pedidoId, List<String> items) {
        System.out.printf("[SISTEMA PEDIDOS] Recibiendo orden de %s (Pedido: %s): %s%n", mesaId, pedidoId, items);
        eventBus.publicar(new PedidoRealizadoEvent(mesaId, pedidoId, items));
    }
}