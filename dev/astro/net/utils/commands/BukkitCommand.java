package dev.astro.net.utils.commands;

import org.bukkit.plugin.*;
import java.util.*;
import org.apache.commons.lang.*;
import org.bukkit.command.*;

public class BukkitCommand extends Command
{
    private Plugin owningPlugin;
    protected BukkitCompleter completer;
    private CommandExecutor executor;
    
    protected BukkitCommand(final String label, final CommandExecutor executor, final Plugin owner) {
        super(label);
        this.executor = executor;
        this.owningPlugin = owner;
        this.usageMessage = "";
    }
    
    public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
        boolean success = false;
        if (!this.owningPlugin.isEnabled()) {
            return false;
        }
        if (!this.testPermission(sender)) {
            return true;
        }
        try {
            success = this.executor.onCommand(sender, (Command)this, commandLabel, args);
        }
        catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing command '" + commandLabel + "' in plugin " + this.owningPlugin.getDescription().getFullName(), ex);
        }
        if (!success && this.usageMessage.length() > 0) {
            String[] split;
            for (int length = (split = this.usageMessage.replace("<command>", commandLabel).split("\n")).length, i = 0; i < length; ++i) {
                final String line = split[i];
                sender.sendMessage(line);
            }
        }
        return success;
    }
    
    public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) throws CommandException, IllegalArgumentException {
        Validate.notNull((Object)sender, "Sender cannot be null");
        Validate.notNull((Object)args, "Arguments cannot be null");
        Validate.notNull((Object)alias, "Alias cannot be null");
        List<String> completions = null;
        try {
            if (this.completer != null) {
                completions = this.completer.onTabComplete(sender, this, alias, args);
            }
            if (completions == null && this.executor instanceof TabCompleter) {
                completions = (List<String>)((TabCompleter)this.executor).onTabComplete(sender, (Command)this, alias, args);
            }
        }
        catch (Throwable ex) {
            final StringBuilder message = new StringBuilder();
            message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
            for (final String arg : args) {
                message.append(arg).append(' ');
            }
            message.deleteCharAt(message.length() - 1).append("' in plugin ").append(this.owningPlugin.getDescription().getFullName());
            throw new CommandException(message.toString(), ex);
        }
        if (completions == null) {
            return (List<String>)super.tabComplete(sender, alias, args);
        }
        return completions;
    }
}
