package com.golem;

import com.golem.components.*;
import com.golem.core.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("======================================================================");
        System.out.println("   INICIANDO SISTEMA: LA TABERNA DEL GÓLEM ALQUÍMICO (EVENT BUS)");
        System.out.println("======================================================================\n");

        // 1. Instanciación del núcleo de comunicación
        EventBus bus = new EventBus();

        // 2. Inicialización de componentes desacoplados
        SistemaDePedidos pedidos = new SistemaDePedidos(bus);
        new Barra(bus);
        new Cocina(bus);
        new SistemaDeSonido(bus);
        new PanelLED(bus);

        // 3. Demostración de Principio Open/Closed: Incorporamos el Sistema de Humo
        // sin tocar la clase Banda, SistemaDeSonido o el EventBus.
        new SistemaDeHumo(bus);

        Banda bandaDijkstra = new Banda(bus, "Los Seguidores de Dijkstra");
        Banda bandaDragonForce = new Banda(bus, "DragonForce");

        System.out.println("[INFO] Todos los subsistemas conectados al EventBus con éxito.\n");

        // ------------------------------------------------------------------
        // ESCENARIO 1: PEDIDO CONCURRENTE EN MESA
        // ------------------------------------------------------------------
        pedidos.realizarPedido("Mesa 5", "PED-101", List.of("Cerveza", "Hamburguesa"));

        // Pausa breve para simular tiempo de consumo
        TimeUnit.SECONDS.sleep(1);

        // ------------------------------------------------------------------
        // ESCENARIO 2: ACTUACIÓN MUSICAL ESTÁNDAR
        // ------------------------------------------------------------------
        bandaDijkstra.tocarCancion("El Algoritmo del Amor", 180);

        // Pausa para dar tiempo a la entrega de la comida (5s en total)
        TimeUnit.SECONDS.sleep(5);

        // ------------------------------------------------------------------
        // ESCENARIO 3: SEGUNDO PEDIDO Y CANCIÓN ÉPICA (DISPARO DE HUMO)
        // ------------------------------------------------------------------
        System.out.println("\n--- NUEVA RONDA EN LA TABERNA ---");
        pedidos.realizarPedido("Mesa 2", "PED-102", List.of("Hidromiel"));

        bandaDragonForce.tocarCancion("Through the Fire and Flames", 420);

        // Espera de finalización de hilos asíncronos de la barra
        TimeUnit.SECONDS.sleep(3);

        System.out.println("\n======================================================================");
        System.out.println("   SIMULACIÓN FINALIZADA CON ÉXITO: 100% DESACOPLAMIENTO LOGRADO");
        System.out.println("======================================================================");

        bus.shutdown();
    }
}