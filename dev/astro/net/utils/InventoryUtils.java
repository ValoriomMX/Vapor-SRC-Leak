package dev.astro.net.utils;

import org.bukkit.inventory.*;
import org.yaml.snakeyaml.external.biz.base64Coder.*;
import org.bukkit.util.io.*;
import java.io.*;

public class InventoryUtils
{
    public static String itemStackArrayToBase64(final ItemStack[] items) throws IllegalStateException {
        try {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream((OutputStream)outputStream);
            dataOutput.writeInt(items.length);
            for (int i = 0; i < items.length; ++i) {
                dataOutput.writeObject((Object)items[i]);
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
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
        catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
    
    public static int getInventorySize(final int listSize) {
        if (listSize <= 9) {
            return 9;
        }
        if (listSize > 9 && listSize <= 18) {
            return 18;
        }
        if (listSize > 18 && listSize <= 27) {
            return 27;
        }
        if (listSize > 27 && listSize <= 36) {
            return 36;
        }
        if (listSize > 36 && listSize <= 45) {
            return 45;
        }
        if (listSize > 45 && listSize <= 54) {
            return 54;
        }
        return 9;
    }
}
