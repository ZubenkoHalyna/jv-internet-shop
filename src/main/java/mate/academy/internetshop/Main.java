package mate.academy.internetshop;

import java.math.BigDecimal;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class Main {
    public static void main(String[] args) {
        Injector injector = Injector.getInstance("mate.academy.internetshop");
        ProductService service =
                (ProductService) injector.getInstance(ProductService.class);

        service.create(new Product("Water", new BigDecimal(15)));
        service.create(new Product("Tea", new BigDecimal(25.5)));
        service.create(new Product("Coffee", new BigDecimal(50)));

        System.out.println("Created products: ");
        service.getAll().forEach(System.out::println);

        System.out.println("Product with id 1: ");
        Product product = service.get(1L);
        System.out.println(product);

        product.setPrice(new BigDecimal(28.5));
        service.update(product);
        System.out.println("Product with id 1 after update: ");
        System.out.println(service.get(1L));

        System.out.println("All products before delete: ");
        service.getAll().forEach(System.out::println);
        System.out.println("Product with id 1 deleted.");
        service.delete(1L);
        System.out.println("All products after delete: ");
        service.getAll().forEach(System.out::println);
    }
}
