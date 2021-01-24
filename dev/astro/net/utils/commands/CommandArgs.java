package dev.astro.net.utils.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class CommandArgs
{
    private CommandSender sender;
    private Command command;
    private String label;
    private String[] args;
    
    protected CommandArgs(final CommandSender sender, final Command command, final String label, final String[] args, final int subCommand) {
        final String[] modArgs = new String[args.length - subCommand];
        for (int i = 0; i < args.length - subCommand; ++i) {
            modArgs[i] = args[i + subCommand];
        }
        final StringBuffer buffer = new StringBuffer();
        buffer.append(label);
        for (int x = 0; x < subCommand; ++x) {
            buffer.append("." + args[x]);
        }
        final String cmdLabel = buffer.toString();
        this.sender = sender;
        this.command = command;
        this.label = cmdLabel;
        this.args = modArgs;
    }
    
    public CommandSender getSender() {
        return this.sender;
    }
    
    public Command getCommand() {
        return this.command;
    }
    
    public String getLabel() {
        return this.label.replace(".", " ");
    }
    
    public String[] getArgs() {
        return this.args;
    }
    
    public String getArgs(final int index) {
        return this.args[index];
    }
    
    public int length() {
        return this.args.length;
    }
    
    public boolean isPlayer() {
        return this.sender instanceof Player;
    }
    
    public Player getPlayer() {
        if (this.sender instanceof Player) {
            return (Player)this.sender;
        }
        return null;
    }
}
