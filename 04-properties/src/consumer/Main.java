import library.Context;
import library.KeyRegistry;
import library.Key;

public static final Key<User> USER_KEY = KeyRegistry.register("user", User.class);

public static class User {
    private final String name;
    public User(String name) { this.name = name; }
    public String getName() { return name; }
}

public static class Product {
    private final String title;
    public Product(String title) { this.title = title; }
    public String getTitle() { return title; }
}

void  main() {

    Key<User> USER_KEY = KeyRegistry.register("user", User.class);
    Key<Product> PRODUCT_KEY = KeyRegistry.register("product", Product.class);

    Key<Consumer<Context>> USER_OPERATION_KEY =
            KeyRegistry.register("user_operation", (Class<Consumer<Context>>) (Class<?>) Consumer.class);

    try {
        Key<String> BAD_KEY = KeyRegistry.register("user", String.class);
    } catch (IllegalArgumentException e) {
        System.out.println("Ошибка: " + e.getMessage());
    }
    Key<Consumer<Context>> PRODUCT_OPERATION_KEY =
            KeyRegistry.register("product_operation", (Class<Consumer<Context>>) (Class<?>) Consumer.class);

    Context ctx = new Context();

    ctx.set(USER_KEY, new User("Alice"));
    ctx.set(PRODUCT_KEY, new Product("Phone"));

    ctx.set(USER_OPERATION_KEY, (Consumer<Context>) c -> {
        User u = c.get(USER_KEY);
        System.out.println("Привет, " + u.getName() + "!");
    });

    ctx.set(PRODUCT_OPERATION_KEY, (Consumer<Context>) c -> {
        Product p = c.get(PRODUCT_KEY);
        System.out.println("Новый продукт: " + p.getTitle());
    });

    Consumer<Context> userOp = ctx.get(USER_OPERATION_KEY);
    userOp.accept(ctx);

    Consumer<Context> productOp = ctx.get(PRODUCT_OPERATION_KEY);
    productOp.accept(ctx);

    System.out.println("User: " + ctx.get(USER_KEY).getName());
    System.out.println("Product: " + ctx.get(PRODUCT_KEY).getTitle());

}

