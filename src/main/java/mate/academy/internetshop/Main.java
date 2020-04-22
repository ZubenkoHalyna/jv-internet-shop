package mate.academy.internetshop;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

public class Main {
    public static void main(String[] args) {
        ItemService service =
                (ItemService) Injector.getInstance("mate.academy.internetshop")
                .getInstance(ItemService.class);

        service.create(new Item("Water", 15.0, 5));
        service.create(new Item("Tea", 25.0, 1));
        service.create(new Item("Coffee", 50.0, 10));

        System.out.println("Created items: ");
        service.getAll().forEach(System.out::println);

        System.out.println("Item with id 1: ");
        Item item = service.get(1L);
        System.out.println(item);

        item.setCount(0);
        service.update(item);
        System.out.println("Item with id 1 after update: ");
        System.out.println(service.get(1L));

        System.out.println("Available items: ");
        service.getAllAvailable().forEach(System.out::println);

        System.out.println("All items before delete: ");
        service.getAll().forEach(System.out::println);
        System.out.println("Item with id 1 deleted.");
        service.delete(1L);
        System.out.println("All items after delete: ");
        service.getAll().forEach(System.out::println);
    }
}
