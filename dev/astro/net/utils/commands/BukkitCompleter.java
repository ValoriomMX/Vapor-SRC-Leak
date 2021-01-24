package dev.astro.net.utils.commands;

import org.bukkit.command.*;
import java.util.*;
import java.lang.reflect.*;

public class BukkitCompleter implements TabCompleter
{
    private Map<String, Map.Entry<Method, Object>> completers;
    
    public BukkitCompleter() {
        this.completers = new HashMap<String, Map.Entry<Method, Object>>();
    }
    
    public void addCompleter(final String label, final Method m, final Object obj) {
        this.completers.put(label, new AbstractMap.SimpleEntry<Method, Object>(m, obj));
    }
    
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String label, final String[] args) {
        for (int i = args.length; i >= 0; --i) {
            final StringBuffer buffer = new StringBuffer();
            buffer.append(label.toLowerCase());
            for (int x = 0; x < i; ++x) {
                if (!args[x].equals("") && !args[x].equals(" ")) {
                    buffer.append("." + args[x].toLowerCase());
                }
            }
            final String cmdLabel = buffer.toString();
            if (this.completers.containsKey(cmdLabel)) {
                final Map.Entry<Method, Object> entry = this.completers.get(cmdLabel);
                try {
                    return (List<String>)entry.getKey().invoke(entry.getValue(), new CommandArgs(sender, command, label, args, cmdLabel.split("\\.").length - 1));
                }
                catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
                catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return null;
    }
}
