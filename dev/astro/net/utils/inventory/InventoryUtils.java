package dev.astro.net.utils.inventory;

import com.google.common.base.*;
import org.bukkit.event.inventory.*;
import java.util.*;
import org.yaml.snakeyaml.external.biz.base64Coder.*;
import org.bukkit.util.io.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.io.*;

public class InventoryUtils
{
    public static int DEFAULT_INVENTORY_WIDTH;
    public static int MINIMUM_INVENTORY_HEIGHT;
    public static int MINIMUM_INVENTORY_SIZE;
    public static int MAXIMUM_INVENTORY_HEIGHT;
    public static int MAXIMUM_INVENTORY_SIZE;
    public static int MAXIMUM_SINGLE_CHEST_SIZE;
    public static int MAXIMUM_DOUBLE_CHEST_SIZE;
    
    static {
        InventoryUtils.DEFAULT_INVENTORY_WIDTH = 9;
        InventoryUtils.MINIMUM_INVENTORY_HEIGHT = 1;
        InventoryUtils.MINIMUM_INVENTORY_SIZE = 9;
        InventoryUtils.MAXIMUM_INVENTORY_HEIGHT = 6;
        InventoryUtils.MAXIMUM_INVENTORY_SIZE = 54;
        InventoryUtils.MAXIMUM_SINGLE_CHEST_SIZE = 27;
        InventoryUtils.MAXIMUM_DOUBLE_CHEST_SIZE = 54;
    }
    
    public static ItemStack[] deepClone(final ItemStack[] origin) {
        Preconditions.checkNotNull((Object)origin, (Object)"Origin cannot be null");
        final ItemStack[] cloned = new ItemStack[origin.length];
        for (int i = 0; i < origin.length; ++i) {
            final ItemStack next = origin[i];
            cloned[i] = ((next == null) ? null : next.clone());
        }
        return cloned;
    }
    
    public static int getSafestInventorySize(final int initialSize) {
        return (initialSize + 8) / 9 * 9;
    }
    
    public static void removeItem(final Inventory inventory, final Material type, final short data, final int quantity) {
        final ItemStack[] contents = inventory.getContents();
        final boolean compareDamage = type.getMaxDurability() == 0;
        for (int i = quantity; i > 0; --i) {
            final ItemStack[] array;
            final int length = (array = contents).length;
            int j = 0;
            while (j < length) {
                final ItemStack content = array[j];
                if (content != null && content.getType() == type && (!compareDamage || content.getData().getData() == data)) {
                    if (content.getAmount() <= 1) {
                        inventory.removeItem(new ItemStack[] { content });
                        break;
                    }
                    content.setAmount(content.getAmount() - 1);
                    break;
                }
                else {
                    ++j;
                }
            }
        }
    }
    
    public static int countAmount(final Inventory inventory, final Material type, final short data) {
        final ItemStack[] contents = inventory.getContents();
        final boolean compareDamage = type.getMaxDurability() == 0;
        int counter = 0;
        ItemStack[] array;
        for (int length = (array = contents).length, i = 0; i < length; ++i) {
            final ItemStack item = array[i];
            if (item != null && item.getType() == type && (!compareDamage || item.getData().getData() == data)) {
                counter += item.getAmount();
            }
        }
        return counter;
    }
    
    public static boolean isEmpty(final Inventory inventory) {
        return isEmpty(inventory, true);
    }
    
    public static boolean isEmpty(final Inventory inventory, final boolean checkArmour) {
        boolean result = true;
        ItemStack[] contents5;
        final ItemStack[] contents4 = contents5 = inventory.getContents();
        ItemStack[] array;
        for (int length = (array = contents4).length, i = 0; i < length; ++i) {
            final ItemStack content = array[i];
            if (content != null && content.getType() != Material.AIR) {
                result = false;
                break;
            }
        }
        if (!result) {
            return false;
        }
        if (checkArmour && inventory instanceof PlayerInventory) {
            final ItemStack[] armorContents = contents5 = ((PlayerInventory)inventory).getArmorContents();
            ItemStack[] array2;
            for (int length2 = (array2 = armorContents).length, j = 0; j < length2; ++j) {
                final ItemStack content2 = array2[j];
                if (content2 != null && content2.getType() != Material.AIR) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
    
    public static boolean clickedTopInventory(final InventoryDragEvent event) {
        final InventoryView view = event.getView();
        final Inventory topInventory = view.getTopInventory();
        if (topInventory == null) {
            return false;
        }
        boolean result = false;
        final Set<Map.Entry<Integer, ItemStack>> entrySet = event.getNewItems().entrySet();
        final int size = topInventory.getSize();
        for (final Map.Entry<Integer, ItemStack> entry : entrySet) {
            if (entry.getKey() < size) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public static String[] playerInventoryToBase64(final PlayerInventory playerInventory) throws IllegalStateException {
        final String content = toBase64((Inventory)playerInventory);
        final String armor = itemStackArrayToBase64(playerInventory.getArmorContents());
        return new String[] { content, armor };
    }
    
    public static String itemStackArrayToBase64(final ItemStack[] items) throws IllegalStateException {
        try {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream((OutputStream)outputStream);
            dataOutput.writeInt(items.length);
            for (final ItemStack item : items) {
                dataOutput.writeObject((Object)item);
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }
    
    public static String toBase64(final Inventory inventory) throws IllegalStateException {
        try {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream((OutputStream)outputStream);
            dataOutput.writeInt(inventory.getSize());
            for (int i = 0; i < inventory.getSize(); ++i) {
                dataOutput.writeObject((Object)inventory.getItem(i));
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }
    
    public static Inventory fromBase64(final String data) throws IOException {
        try {
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            final BukkitObjectInputStream dataInput = new BukkitObjectInputStream((InputStream)inputStream);
            final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, dataInput.readInt());
            for (int i = 0; i < inventory.getSize(); ++i) {
                inventory.setItem(i, (ItemStack)dataInput.readObject());
            }
            dataInput.close();
            return inventory;
        }
        catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
    
    public static ItemStack[] itemStackArrayFromBase64(final String data) throws IOException {
        try {
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            final BukkitObjectInputStream dataInput = new BukkitObjectInputStream((InputStream)inputStream);
            final ItemStack[] items = new ItemStack[dataInput.readInt()];
            for (int i = 0; i < items.length; ++i) {
                items[i] = (ItemStack)dataInput.readObject();
            }
            dataInput.close();
            return items;
        }
        catch (ClassNotFoundException ex) {
            throw new IOException("Unable to decode class type.", ex);
        }
    }
}
