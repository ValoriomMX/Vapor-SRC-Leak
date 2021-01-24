package dev.astro.net.utils.chat;

import org.bukkit.inventory.*;
import org.bukkit.*;
import net.minecraft.server.v1_7_R4.*;
import org.bukkit.command.*;

public class Trans extends ChatMessage
{
    public Trans() {
        super("", new Object[0]);
    }
    
    public Trans(final String string, final Object... objects) {
        super(string, objects);
    }
    
    public static Trans fromItemStack(final ItemStack stack) {
        return ChatUtil.fromItemStack(stack);
    }
    
    public IChatBaseComponent f() {
        return (IChatBaseComponent)this.h();
    }
    
    public Trans append(final Object object) {
        return this.append(String.valueOf(object));
    }
    
    public Trans append(final String text) {
        return (Trans)this.a(text);
    }
    
    public Trans append(final IChatBaseComponent node) {
        return (Trans)this.addSibling(node);
    }
    
    public Trans append(final IChatBaseComponent... nodes) {
        for (final IChatBaseComponent node : nodes) {
            this.addSibling(node);
        }
        return this;
    }
    
    public Trans appendItem(final ItemStack stack) {
        return this.append((IChatBaseComponent)ChatUtil.fromItemStack(stack));
    }
    
    public Trans localText(final ItemStack stack) {
        return this.append((IChatBaseComponent)ChatUtil.localFromItem(stack));
    }
    
    public Trans setBold(final boolean bold) {
        this.getChatModifier().setBold(Boolean.valueOf(bold));
        return this;
    }
    
    public Trans setItalic(final boolean italic) {
        this.getChatModifier().setItalic(Boolean.valueOf(italic));
        return this;
    }
    
    public Trans setUnderline(final boolean underline) {
        this.getChatModifier().setUnderline(Boolean.valueOf(underline));
        return this;
    }
    
    public Trans setRandom(final boolean random) {
        this.getChatModifier().setRandom(Boolean.valueOf(random));
        return this;
    }
    
    public Trans setStrikethrough(final boolean strikethrough) {
        this.getChatModifier().setStrikethrough(Boolean.valueOf(strikethrough));
        return this;
    }
    
    public Trans setColor(final ChatColor color) {
        this.getChatModifier().setColor(EnumChatFormat.valueOf(color.name()));
        return this;
    }
    
    public Trans setClick(final ClickAction action, final String value) {
        this.getChatModifier().setChatClickable(new ChatClickable(action.getNMS(), value));
        return this;
    }
    
    public Trans setHover(final HoverAction action, final IChatBaseComponent value) {
        this.getChatModifier().a(new ChatHoverable(action.getNMS(), value));
        return this;
    }
    
    public Trans setHoverText(final String text) {
        return this.setHover(HoverAction.SHOW_TEXT, (IChatBaseComponent)new Text(text));
    }
    
    public Trans reset() {
        ChatUtil.reset((IChatBaseComponent)this);
        return this;
    }
    
    public String toRawText() {
        return this.c();
    }
    
    public void send(final CommandSender sender) {
        ChatUtil.send(sender, (IChatBaseComponent)this);
    }
}
