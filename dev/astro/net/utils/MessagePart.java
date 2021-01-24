package dev.astro.net.utils;

import org.bukkit.*;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.*;

class MessagePart
{
    ChatColor color;
    ChatColor[] styles;
    String clickActionName;
    String clickActionData;
    String hoverActionName;
    String hoverActionData;
    String text;
    
    MessagePart(final String text) {
        this.text = text;
    }
    
    JsonWriter writeJson(final JsonWriter json) {
        try {
            json.beginObject().name("text").value(this.text);
            if (this.color != null) {
                json.name("color").value(this.color.name().toLowerCase());
            }
            if (this.styles != null) {
                ChatColor[] arrayOfChatColor;
                for (int j = (arrayOfChatColor = this.styles).length, i = 0; i < j; ++i) {
                    final ChatColor style = arrayOfChatColor[i];
                    json.name(style.name().toLowerCase()).value(true);
                }
            }
            if (this.clickActionName != null && this.clickActionData != null) {
                json.name("clickEvent").beginObject().name("action").value(this.clickActionName).name("value").value(this.clickActionData).endObject();
            }
            if (this.hoverActionName != null && this.hoverActionData != null) {
                json.name("hoverEvent").beginObject().name("action").value(this.hoverActionName).name("value").value(this.hoverActionData).endObject();
            }
            return json.endObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return json;
        }
    }
}
