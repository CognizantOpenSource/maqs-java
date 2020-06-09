/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.cucumber;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Scenario context.
 */
public class ScenarioContext {

    /**
     * Field context
     */
    private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<>();

    public static final String JMAQS_HOLDER = "BASE_JMAQS_TEST";

    /**
     * Get object.
     *
     * @param name the name
     * @return the object
     */
    public static Object get(final String name) {
        return getMap().get(name);
    }

    /**
     * Get local thread map.
     *
     * @return the object
     */
    private static Map<String, Object> getMap() {
        if (context.get() == null) {
            context.set(new HashMap());
        }
        return context.get();
    }

    /**
     * Get t.
     *
     * @param <T>  the type parameter
     * @param type the type
     * @return the t
     */
    public static <T> T get(final Class<T> type) {
        final Object obj = get(type.toString());
        return (T) obj;
    }

    /**
     * Remove.
     *
     * @param <T>  the type parameter
     * @param type the type
     */
    public static <T> void remove(final Class<T> type) {
        getMap().remove(type.toString());
    }


    /**
     * Remove an object by name.
     *
     * @param name The name of the object to remove
     */
    public static void remove(String name) {
        getMap().remove(name);
    }

    /**
     * Get t.
     *
     * @param <T>  the type parameter
     * @param name the name
     * @param type the type
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(final String name, final Class<T> type) {
        final Object obj = get(name);
        return (T) obj;
    }

    /**
     * Put.
     *
     * @param name the name
     * @param obj  the obj
     */
    public static void put(final String name, final Object obj) {
        getMap().put(name, obj);
    }

    /**
     * Put.
     *
     * @param <T>  the type parameter
     * @param type the type
     * @param obj  the obj
     */
    public static <T> void put(final Class<T> type, final Object obj) {
        getMap().put(type.toString(), obj);
    }

    /**
     * Put.
     *
     * @param <T>    the type parameter
     * @param object the object
     * @param type   the type
     */
    public static <T> void put(final Object object, final Class<T> type) {
        put(type, object);
    }
}
