package dev.astro.net.utils.commands;

import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import java.lang.reflect.*;
import org.bukkit.help.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.command.*;

public class CommandFramework implements CommandExecutor
{
    private Map<String, Map.Entry<Method, Object>> commandMap;
    private CommandMap map;
    private Plugin plugin;
    
    public CommandFramework(final Plugin plugin) {
        this.commandMap = new HashMap<String, Map.Entry<Method, Object>>();
        this.plugin = plugin;
        if (plugin.getServer().getPluginManager() instanceof SimplePluginManager) {
            final SimplePluginManager manager = (SimplePluginManager)plugin.getServer().getPluginManager();
            try {
                final Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);
                this.map = (CommandMap)field.get(manager);
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (SecurityException e2) {
                e2.printStackTrace();
            }
            catch (IllegalAccessException e3) {
                e3.printStackTrace();
            }
            catch (NoSuchFieldException e4) {
                e4.printStackTrace();
            }
        }
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        return this.handleCommand(sender, cmd, label, args);
    }
    
    public boolean handleCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        int i = args.length;
        while (i >= 0) {
            final StringBuffer buffer = new StringBuffer();
            buffer.append(label.toLowerCase());
            for (int x = 0; x < i; ++x) {
                buffer.append("." + args[x].toLowerCase());
            }
            final String cmdLabel = buffer.toString();
            if (this.commandMap.containsKey(cmdLabel)) {
                final Method method = this.commandMap.get(cmdLabel).getKey();
                final Object methodObject = this.commandMap.get(cmdLabel).getValue();
                final dev.astro.net.utils.commands.Command command = method.getAnnotation(dev.astro.net.utils.commands.Command.class);
                if (!command.permission().equalsIgnoreCase("") && !sender.hasPermission(command.permission())) {
                    sender.sendMessage(ChatColor.RED + command.noPerm());
                    return true;
                }
                if (command.inGameOnly() && !(sender instanceof Player)) {
                    sender.sendMessage("This command is only performable in game");
                    return true;
                }
                try {
                    method.invoke(methodObject, new CommandArgs(sender, cmd, label, args, cmdLabel.split("\\.").length - 1));
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
                return true;
            }
            else {
                --i;
            }
        }
        this.defaultCommand(new CommandArgs(sender, cmd, label, args, 0));
        return true;
    }
    
    public void registerCommands(final Object obj) {
        Method[] methods;
        for (int length = (methods = obj.getClass().getMethods()).length, i = 0; i < length; ++i) {
            final Method m = methods[i];
            if (m.getAnnotation(dev.astro.net.utils.commands.Command.class) != null) {
                final dev.astro.net.utils.commands.Command command = m.getAnnotation(dev.astro.net.utils.commands.Command.class);
                if (m.getParameterTypes().length > 1 || m.getParameterTypes()[0] != CommandArgs.class) {
                    System.out.println("Unable to register command " + m.getName() + ". Unexpected method arguments");
                }
                else {
                    this.registerCommand(command, command.name(), m, obj);
                    String[] aliases;
                    for (int length2 = (aliases = command.aliases()).length, j = 0; j < length2; ++j) {
                        final String alias = aliases[j];
                        this.registerCommand(command, alias, m, obj);
                    }
                }
            }
            else if (m.getAnnotation(Completer.class) != null) {
                final Completer comp = m.getAnnotation(Completer.class);
                if (m.getParameterTypes().length > 1 || m.getParameterTypes().length == 0 || m.getParameterTypes()[0] != CommandArgs.class) {
                    System.out.println("Unable to register tab completer " + m.getName() + ". Unexpected method arguments");
                }
                else if (m.getReturnType() != List.class) {
                    System.out.println("Unable to register tab completer " + m.getName() + ". Unexpected return type");
                }
                else {
                    this.registerCompleter(comp.name(), m, obj);
                    String[] aliases2;
                    for (int length3 = (aliases2 = comp.aliases()).length, k = 0; k < length3; ++k) {
                        final String alias = aliases2[k];
                        this.registerCompleter(alias, m, obj);
                    }
                }
            }
        }
    }
    
    public void registerHelp() {
        final Set<HelpTopic> help = new TreeSet<HelpTopic>((Comparator<? super HelpTopic>)HelpTopicComparator.helpTopicComparatorInstance());
        for (final String s : this.commandMap.keySet()) {
            if (!s.contains(".")) {
                final Command cmd = this.map.getCommand(s);
                final HelpTopic topic = (HelpTopic)new GenericCommandHelpTopic(cmd);
                help.add(topic);
            }
        }
        final IndexHelpTopic topic2 = new IndexHelpTopic(this.plugin.getName(), "All commands for " + this.plugin.getName(), (String)null, (Collection)help, "Below is a list of all " + this.plugin.getName() + " commands:");
        Bukkit.getServer().getHelpMap().addTopic((HelpTopic)topic2);
    }
    
    public void registerCommand(final dev.astro.net.utils.commands.Command command, final String label, final Method m, final Object obj) {
        this.commandMap.put(label.toLowerCase(), new AbstractMap.SimpleEntry<Method, Object>(m, obj));
        this.commandMap.put(String.valueOf(this.plugin.getName()) + ':' + label.toLowerCase(), new AbstractMap.SimpleEntry<Method, Object>(m, obj));
        final String cmdLabel = label.split("\\.")[0].toLowerCase();
        if (this.map.getCommand(cmdLabel) == null) {
            final Command cmd = new BukkitCommand(cmdLabel, (CommandExecutor)this, this.plugin);
            this.map.register(this.plugin.getName(), cmd);
        }
        if (!command.description().equalsIgnoreCase("") && cmdLabel == label) {
            this.map.getCommand(cmdLabel).setDescription(command.description());
        }
        if (!command.usage().equalsIgnoreCase("") && cmdLabel == label) {
            this.map.getCommand(cmdLabel).setUsage(command.usage());
        }
    }
    
    public void registerCompleter(final String label, final Method m, final Object obj) {
        final String cmdLabel = label.split("\\.")[0].toLowerCase();
        if (this.map.getCommand(cmdLabel) == null) {
            final Command command = new BukkitCommand(cmdLabel, (CommandExecutor)this, this.plugin);
            this.map.register(this.plugin.getName(), command);
        }
        if (this.map.getCommand(cmdLabel) instanceof BukkitCommand) {
            final BukkitCommand command2 = (BukkitCommand)this.map.getCommand(cmdLabel);
            if (command2.completer == null) {
                command2.completer = new BukkitCompleter();
            }
            command2.completer.addCompleter(label, m, obj);
        }
        else if (this.map.getCommand(cmdLabel) instanceof PluginCommand) {
            try {
                final Object command3 = this.map.getCommand(cmdLabel);
                final Field field = command3.getClass().getDeclaredField("completer");
                field.setAccessible(true);
                if (field.get(command3) == null) {
                    final BukkitCompleter completer = new BukkitCompleter();
                    completer.addCompleter(label, m, obj);
                    field.set(command3, completer);
                }
                else if (field.get(command3) instanceof BukkitCompleter) {
                    final BukkitCompleter completer = (BukkitCompleter)field.get(command3);
                    completer.addCompleter(label, m, obj);
                }
                else {
                    System.out.println("Unable to register tab completer " + m.getName() + ". A tab completer is already registered for that command!");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void defaultCommand(final CommandArgs args) {
        args.getSender().sendMessage(String.valueOf(args.getLabel()) + " is not handled! Oh noes!");
    }
}
