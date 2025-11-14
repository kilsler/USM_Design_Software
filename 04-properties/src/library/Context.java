package library;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private final Map<Key<?>, Object> data = new HashMap<>();

    public <T> void set(Key<T> key, T value) {
        if (value != null && !key.getType().isInstance(value)) {
            throw new IllegalArgumentException("Значение не соответствует типу ключа " + key);
        }
        data.put(key, value);
    }

    public <T> T get(Key<T> key) {
        return (T) data.get(key);
    }

    public boolean has(Key<?> key) {
        return data.containsKey(key);
    }
}
