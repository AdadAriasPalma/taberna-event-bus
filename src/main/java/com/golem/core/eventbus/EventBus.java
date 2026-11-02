package com.golem.core.eventbus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implementación robusta, thread-safe y genérica del Event Bus.
 * Permite suscripciones dinámicas tipadas y publicación concurrente.
 */
public class EventBus {
    private final Map<Class<? extends Event>, List<Subscriber<?>>> subscribers = new ConcurrentHashMap<>();
    private final ExecutorService asyncExecutor = Executors.newCachedThreadPool();

    /**
     * Registra un suscriptor para un tipo específico de evento.
     */
    public synchronized <T extends Event> void suscribir(Class<T> eventType, Subscriber<T> subscriber) {
        if (eventType == null || subscriber == null) {
            throw new IllegalArgumentException("El tipo de evento y el suscriptor no pueden ser nulos.");
        }
        subscribers.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(subscriber);
    }

    /**
     * Elimina un suscriptor previamente registrado.
     */
    public synchronized <T extends Event> void desuscribir(Class<T> eventType, Subscriber<T> subscriber) {
        if (eventType == null || subscriber == null) return;
        List<Subscriber<?>> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            eventSubscribers.remove(subscriber);
            if (eventSubscribers.isEmpty()) {
                subscribers.remove(eventType);
            }
        }
    }

    /**
     * Notifica a todos los suscriptores registrados para la clase concreta del evento.
     */
    @SuppressWarnings("unchecked")
    public void publicar(Event event) {
        if (event == null) return;
        List<Subscriber<?>> eventSubscribers = subscribers.get(event.getClass());
        if (eventSubscribers != null && !eventSubscribers.isEmpty()) {
            for (Subscriber<?> sub : eventSubscribers) {
                try {
                    ((Subscriber<Event>) sub).onEvent(event);
                } catch (Exception e) {
                    System.err.printf("[ERROR EVENT BUS] Error en suscriptor: %s%n", e.getMessage());
                }
            }
        }
    }

    /**
     * Publica el evento de forma asíncrona mediante un pool de hilos.
     */
    public void publicarAsync(Event event) {
        asyncExecutor.submit(() -> publicar(event));
    }

    public void shutdown() {
        asyncExecutor.shutdown();
    }
}