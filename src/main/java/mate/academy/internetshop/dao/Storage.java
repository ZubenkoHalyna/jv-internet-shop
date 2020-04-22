package mate.academy.internetshop.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Item;

public class Storage {
    public static final List<Item> items = new ArrayList<>();
    private static long itemsCount = 0;

    public static Item addNewItem(Item item) {
        item.setId(itemsCount);
        itemsCount++;
        items.add(item);
        return item;
    }
}
