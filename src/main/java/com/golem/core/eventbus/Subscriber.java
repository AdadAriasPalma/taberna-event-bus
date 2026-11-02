package com.golem.core.eventbus;

/**
 * Interfaz funcional para los suscriptores tipados del EventBus.
 * @param <T> Tipo específico de evento que procesa.
 */
@FunctionalInterface
public interface Subscriber<T extends Event> {
    void onEvent(T event);
}