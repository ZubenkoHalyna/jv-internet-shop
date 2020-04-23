package mate.academy.internetshop;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class Main {
    public static void main(String[] args) {
        Injector injector = Injector.getInstance("mate.academy.internetshop");
        ProductService productService =
                (ProductService) injector.getInstance(ProductService.class);

        productService.create(new Product("Water", new BigDecimal(15)));
        productService.create(new Product("Tea", new BigDecimal(25.5)));
        productService.create(new Product("Coffee", new BigDecimal(50)));

        System.out.println("Created products: ");
        productService.getAll().forEach(System.out::println);

        System.out.println("Product with id 1: ");
        Product product = productService.get(1L);
        System.out.println(product);

        product.setPrice(new BigDecimal(28.5));
        productService.update(product);
        System.out.println("Product with id 1 after update: ");
        System.out.println(productService.get(1L));

        System.out.println("All products before delete: ");
        productService.getAll().forEach(System.out::println);
        System.out.println("Product with id 2 deleted.");
        productService.delete(2L);
        System.out.println("All products after delete: ");
        productService.getAll().forEach(System.out::println);

        UserService userService =
                (UserService) injector.getInstance(UserService.class);

        userService.create(new User("Anonymous", "anonymous", "anonymous"));
        userService.create(new User("Gala", "gala", "qwerty"));
        userService.create(new User("Admin", "admin", "admin"));
        System.out.println("Users created:");
        userService.getAll().forEach(System.out::println);

        User user = userService.get(1L);
        user.setPassword("123");
        userService.update(user);
        System.out.println("User with id 1 after update: ");
        System.out.println(userService.get(1L));

        System.out.println("All users before delete: ");
        System.out.println(userService.getAll());
        System.out.println("User with id 0 deleted.");
        userService.delete(0L);
        System.out.println("All users after delete: ");
        System.out.println(userService.getAll());

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        ShoppingCart shoppingCart = new ShoppingCart(List.of(), user);
        shoppingCartService.addProduct(shoppingCart, product);
        shoppingCartService.addProduct(shoppingCart, product);
        System.out.println("ShoppingCart created: ");
        System.out.println(shoppingCartService.getByUserId(user.getId()));

        System.out.println("Products in ShoppingCart:");
        shoppingCartService.getAllProducts(shoppingCart).forEach(System.out::println);

        shoppingCartService.deleteProduct(shoppingCart, product);
        System.out.println("Products in ShoppingCart after delete:");
        shoppingCartService.getAllProducts(shoppingCart).forEach(System.out::println);

        shoppingCartService.clear(shoppingCart);
        System.out.println("Products in ShoppingCart after clear:");
        shoppingCartService.getAllProducts(shoppingCart).forEach(System.out::println);

        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);

        orderService.completeOrder(List.of(product), user);
        orderService.completeOrder(List.of(product), userService.get(2L));
        System.out.println("Order created: ");
        System.out.println(orderService.getAll());

        System.out.println("Orders for user with id 1:");
        System.out.println(orderService.getUserOrders(user));

        orderService.delete(0L);
        System.out.println("Orders after delete:");
        System.out.println(orderService.getAll());

    }
}
