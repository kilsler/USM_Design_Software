package library;

public final class Key<T> {
    private final String name;
    private final Class<T> type;

    Key(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Key(" + name + ":" + type.getSimpleName() + ")";
    }
}