package ru.nivanov;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Created by Nikolay Ivanov on 19.07.2017.
 */
class NonBlockCache {

    private final Map<Integer, Model> map;

    /**
     * Constructor.
     */
    NonBlockCache() {
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * Add method.
     * @param key ..
     * @param model ..
     */
    public void add(Integer key, Model model) {
        this.map.put(key, model);
    }

    /**
     * Update model name method.
     * @param key ..
     * @param name ..
     */
    public void update(Integer key, String name) {

        int version = this.map.get(key).getVersion();

        this.map.computeIfPresent(key, new BiFunction<Integer, Model, Model>() {
            @Override
            public Model apply(Integer integer, Model model) {
                integer = version;
                model = map.get(key);
                if (validate(integer, model)) {
                    model.setName(name);
                    model.incrementVersion();
                } else {
                    throw new OptimisticException("version mismatch");

                }
                return model;
            }
        });

    }

    /**
     * Delete mapping.
     * @param key ..
     */
    public void delete(Integer key) {
        if (key != null && this.map.containsKey(key)) {
            this.map.remove(key);
        }
    }

    /**
     * Validate data.
     * @param value ..
     * @param model ..
     * @return ..
     */
    private boolean validate(Integer value, Model model) {
        return model.getVersion() == value;

    }

}
