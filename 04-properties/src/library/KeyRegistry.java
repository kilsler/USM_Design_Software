package library;

import java.util.HashMap;
import java.util.Map;

public class KeyRegistry {
    private static final Map<String, Class<?>> registry = new HashMap<>();

    public static synchronized <T> Key<T> register(String name, Class<T> type) {
        if (registry.containsKey(name)) {
            Class<?> existingType = registry.get(name);
            if (!existingType.equals(type)) {
                throw new IllegalArgumentException("Ключ '" + name +
                        "' уже зарегистрирован с другим типом: " + existingType.getSimpleName());
            }
        } else {
            registry.put(name, type);
        }
        return new Key<>(name, type);
    }
}
