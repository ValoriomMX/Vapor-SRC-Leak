package dev.astro.net.utils;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.*;
import java.io.*;
import java.util.*;
import org.bukkit.entity.*;

public class FancyMessage
{
    private List<MessagePart> messageParts;
    private String jsonString;
    private boolean dirty;
    private Class<?> nmsChatSerializer;
    private Class<?> nmsTagCompound;
    private Class<?> nmsPacketPlayOutChat;
    private Class<?> nmsAchievement;
    private Class<?> nmsStatistic;
    private Class<?> nmsItemStack;
    private Class<?> obcStatistic;
    private Class<?> obcItemStack;
    
    public FancyMessage(final String firstPartText) {
        this.nmsChatSerializer = Reflection.getNMSClass("ChatSerializer");
        this.nmsTagCompound = Reflection.getNMSClass("NBTTagCompound");
        this.nmsPacketPlayOutChat = Reflection.getNMSClass("PacketPlayOutChat");
        this.nmsAchievement = Reflection.getNMSClass("Achievement");
        this.nmsStatistic = Reflection.getNMSClass("Statistic");
        this.nmsItemStack = Reflection.getNMSClass("ItemStack");
        this.obcStatistic = Reflection.getOBCClass("CraftStatistic");
        this.obcItemStack = Reflection.getOBCClass("inventory.CraftItemStack");
        (this.messageParts = new ArrayList<MessagePart>()).add(new MessagePart(firstPartText));
        this.jsonString = null;
        this.dirty = false;
    }
    
    public FancyMessage color(final ChatColor color) {
        if (!color.isColor()) {
            throw new IllegalArgumentException(String.valueOf(color.name()) + " is not a color");
        }
        this.latest().color = color;
        this.dirty = true;
        return this;
    }
    
    public FancyMessage style(final ChatColor... styles) {
        final ChatColor[] arrayOfChatColor = styles;
        for (int j = styles.length, i = 0; i < j; ++i) {
            final ChatColor style = arrayOfChatColor[i];
            if (!style.isFormat()) {
                throw new IllegalArgumentException(String.valueOf(style.name()) + " is not a style");
            }
        }
        this.latest().styles = styles;
        this.dirty = true;
        return this;
    }
    
    public FancyMessage file(final String path) {
        this.onClick("open_file", path);
        return this;
    }
    
    public FancyMessage link(final String url) {
        this.onClick("open_url", url);
        return this;
    }
    
    public FancyMessage suggest(final String command) {
        this.onClick("suggest_command", command);
        return this;
    }
    
    public FancyMessage command(final String command) {
        this.onClick("run_command", command);
        return this;
    }
    
    public FancyMessage achievementTooltip(final String name) {
        this.onHover("show_achievement", "achievement." + name);
        return this;
    }
    
    public FancyMessage achievementTooltip(final Achievement which) {
        try {
            final Object achievement = Reflection.getMethod(this.obcStatistic, "getNMSAchievement", (Class<?>[])new Class[0]).invoke(null, which);
            return this.achievementTooltip((String)Reflection.getField(this.nmsAchievement, "name").get(achievement));
        }
        catch (Exception e) {
            e.printStackTrace();
            return this;
        }
    }
    
    public FancyMessage statisticTooltip(final Statistic which) {
        final Statistic.Type type = which.getType();
        if (type != Statistic.Type.UNTYPED) {
            throw new IllegalArgumentException("That statistic requires an additional " + type + " parameter!");
        }
        try {
            final Object statistic = Reflection.getMethod(this.obcStatistic, "getNMSStatistic", (Class<?>[])new Class[0]).invoke(null, which);
            return this.achievementTooltip((String)Reflection.getField(this.nmsStatistic, "name").get(statistic));
        }
        catch (Exception e) {
            e.printStackTrace();
            return this;
        }
    }
    
    public FancyMessage statisticTooltip(final Statistic which, final Material item) {
        final Statistic.Type type = which.getType();
        if (type == Statistic.Type.UNTYPED) {
            throw new IllegalArgumentException("That statistic needs no additional parameter!");
        }
        if ((type == Statistic.Type.BLOCK && item.isBlock()) || type == Statistic.Type.ENTITY) {
            throw new IllegalArgumentException("Wrong parameter type for that statistic - needs " + type + "!");
        }
        try {
            final Object statistic = Reflection.getMethod(this.obcStatistic, "getMaterialStatistic", (Class<?>[])new Class[0]).invoke(null, which, item);
            return this.achievementTooltip((String)Reflection.getField(this.nmsStatistic, "name").get(statistic));
        }
        catch (Exception e) {
            e.printStackTrace();
            return this;
        }
    }
    
    public FancyMessage statisticTooltip(final Statistic which, final EntityType entity) {
        final Statistic.Type type = which.getType();
        if (type == Statistic.Type.UNTYPED) {
            throw new IllegalArgumentException("That statistic needs no additional parameter!");
        }
        if (type != Statistic.Type.ENTITY) {
            throw new IllegalArgumentException("Wrong parameter type for that statistic - needs " + type + "!");
        }
        try {
            final Object statistic = Reflection.getMethod(this.obcStatistic, "getEntityStatistic", (Class<?>[])new Class[0]).invoke(null, which, entity);
            return this.achievementTooltip((String)Reflection.getField(this.nmsStatistic, "name").get(statistic));
        }
        catch (Exception e) {
            e.printStackTrace();
            return this;
        }
    }
    
    public FancyMessage itemTooltip(final String itemJSON) {
        this.onHover("show_item", itemJSON);
        return this;
    }
    
    public FancyMessage itemTooltip(final ItemStack itemStack) {
        try {
            final Object nmsItem = Reflection.getMethod(this.obcItemStack, "asNMSCopy", ItemStack.class).invoke(null, itemStack);
            return this.itemTooltip(Reflection.getMethod(this.nmsItemStack, "save", (Class<?>[])new Class[0]).invoke(nmsItem, this.nmsTagCompound.newInstance()).toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            return this;
        }
    }
    
    public FancyMessage tooltip(final String text) {
        return this.tooltip(text.split("\\n"));
    }
    
    public FancyMessage tooltip(final List<String> lines) {
        return this.tooltip((String[])lines.toArray());
    }
    
    public FancyMessage tooltip(final String... lines) {
        if (lines.length == 1) {
            this.onHover("show_text", lines[0]);
        }
        else {
            this.itemTooltip(this.makeMultilineTooltip(lines));
        }
        return this;
    }
    
    public FancyMessage then(final Object obj) {
        this.messageParts.add(new MessagePart(obj.toString()));
        this.dirty = true;
        return this;
    }
    
    public String toJSONString() {
        if (!this.dirty && this.jsonString != null) {
            return this.jsonString;
        }
        final StringWriter string = new StringWriter();
        final JsonWriter json = new JsonWriter((Writer)string);
        try {
            if (this.messageParts.size() == 1) {
                this.latest().writeJson(json);
            }
            else {
                json.beginObject().name("text").value("").name("extra").beginArray();
                for (final MessagePart messagePart : this.messageParts) {
                    messagePart.writeJson(json);
                }
                json.endArray().endObject();
                json.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("invalid message");
        }
        this.jsonString = string.toString();
        this.dirty = false;
        return this.jsonString;
    }
    
    public void send(final Player player) {
        try {
            final Object handle = Reflection.getHandle(player);
            final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
            final Object serialized = Reflection.getMethod(this.nmsChatSerializer, "a", String.class).invoke(null, this.toJSONString());
            final Object packet = this.nmsPacketPlayOutChat.getConstructor(Reflection.getNMSClass("IChatBaseComponent")).newInstance(serialized);
            Reflection.getMethod(connection.getClass(), "sendPacket", (Class<?>[])new Class[0]).invoke(connection, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void send(final Iterable<Player> players) {
        for (final Player player : players) {
            this.send(player);
        }
    }
    
    private MessagePart latest() {
        return this.messageParts.get(this.messageParts.size() - 1);
    }
    
    private String makeMultilineTooltip(final String[] lines) {
        final StringWriter string = new StringWriter();
        final JsonWriter json = new JsonWriter((Writer)string);
        try {
            json.beginObject().name("id").value(1L);
            json.name("tag").beginObject().name("display").beginObject();
            json.name("Name").value("\\u00A7f" + lines[0].replace("\"", "\\\""));
            json.name("Lore").beginArray();
            for (int i = 1; i < lines.length; ++i) {
                final String line = lines[i];
                json.value(line.isEmpty() ? " " : line.replace("\"", "\\\""));
            }
            json.endArray().endObject().endObject().endObject();
            json.close();
        }
        catch (Exception e) {
            throw new RuntimeException("invalid tooltip");
        }
        return string.toString();
    }
    
    public void onClick(final String name, final String data) {
        final MessagePart latest = this.latest();
        latest.clickActionName = name;
        latest.clickActionData = data;
        this.dirty = true;
    }
    
    private void onHover(final String name, final String data) {
        final MessagePart latest = this.latest();
        latest.hoverActionName = name;
        latest.hoverActionData = data;
        this.dirty = true;
    }
}
