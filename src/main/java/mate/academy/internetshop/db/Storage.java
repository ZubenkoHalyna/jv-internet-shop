package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static long productsCount = 0;
    private static long ordersCount = 0;
    private static long usersCount = 0;
    private static long shoppingCartsCount = 0;

    public static Product addNewProduct(Product product) {
        product.setId(productsCount);
        productsCount++;
        products.add(product);
        return product;
    }

    public static Order addNewOrder(Order order) {
        order.setId(ordersCount);
        ordersCount++;
        orders.add(order);
        return order;
    }

    public static User addNewUser(User user) {
        user.setId(usersCount);
        usersCount++;
        users.add(user);
        return user;
    }

    public static ShoppingCart addNewShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(shoppingCartsCount);
        shoppingCartsCount++;
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }
}
