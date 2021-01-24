package dev.astro.net.utils;

import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.craftbukkit.v1_7_R4.inventory.*;

public class ActionMessage
{
    private List<AMText> Text;
    
    public ActionMessage() {
        this.Text = new ArrayList<AMText>();
    }
    
    public AMText addText(final String Message) {
        final AMText Text = new AMText(Color.translate(Message));
        this.Text.add(Text);
        return Text;
    }
    
    public String getFormattedMessage() {
        String Chat = "[\"\",";
        for (final AMText Text : this.Text) {
            Chat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(Chat)))) + Text.getFormattedMessage() + ",";
        }
        Chat = Chat.substring(0, Chat.length() - 1);
        Chat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(Chat)))) + "]";
        return Chat;
    }
    
    public enum ClickableType
    {
        RunCommand("RunCommand", 0, "RunCommand", 0, "RunCommand", 0, "RunCommand", 0, "run_command"), 
        SuggestCommand("SuggestCommand", 1, "SuggestCommand", 1, "SuggestCommand", 1, "SuggestCommand", 1, "suggest_command"), 
        OpenURL("OpenURL", 2, "OpenURL", 2, "OpenURL", 2, "OpenURL", 2, "open_url");
        
        public String Action;
        
        private ClickableType(final String s4, final int n4, final String s3, final int n3, final String s2, final int n2, final String s, final int n, final String Action) {
            this.Action = Action;
        }
    }
    
    public class AMText
    {
        private String Message;
        private Map<String, Map.Entry<String, String>> Modifiers;
        
        public AMText(final String Text) {
            this.Message = "";
            this.Modifiers = new HashMap<String, Map.Entry<String, String>>();
            this.Message = Text;
        }
        
        public String getMessage() {
            return this.Message;
        }
        
        public String getFormattedMessage() {
            String Chat = "{\"text\":\"" + this.Message + "\"";
            for (final String Event : this.Modifiers.keySet()) {
                final Map.Entry<String, String> Modifier = this.Modifiers.get(Event);
                Chat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(Chat)))) + ",\"" + Event + "\":{\"action\":\"" + Modifier.getKey() + "\",\"value\":" + Modifier.getValue() + "}";
            }
            Chat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(Chat)))) + "}";
            return Chat;
        }
        
        public AMText addHoverText(final String... Text) {
            String Value = "";
            if (Text.length == 1) {
                Value = "{\"text\":\"" + Text[0] + "\"}";
            }
            else {
                Value = "{\"text\":\"\",\"extra\":[";
                for (final String Message : Text) {
                    Value = String.valueOf(String.valueOf(String.valueOf(String.valueOf(Value)))) + "{\"text\":\"" + Message + "\"},";
                }
                Value = Value.substring(0, Value.length() - 1);
                Value = String.valueOf(String.valueOf(String.valueOf(String.valueOf(Value)))) + "]}";
            }
            final Object Values = new AbstractMap.SimpleEntry("show_text", Value);
            this.Modifiers.put("hoverEvent", (Map.Entry<String, String>)Values);
            return this;
        }
        
        public AMText addHoverItem(final ItemStack Item) {
            final String Value = CraftItemStack.asNMSCopy(Item).getTag().toString();
            final Map.Entry<String, String> Values = new AbstractMap.SimpleEntry<String, String>("show_item", Value);
            this.Modifiers.put("hoverEvent", Values);
            return this;
        }
        
        public AMText setClickEvent(final ClickableType Type, final String Value) {
            final String Key = Type.Action;
            final Map.Entry<String, String> Values = new AbstractMap.SimpleEntry<String, String>(Key, "\"" + Value + "\"");
            this.Modifiers.put("clickEvent", Values);
            return this;
        }
    }
}
