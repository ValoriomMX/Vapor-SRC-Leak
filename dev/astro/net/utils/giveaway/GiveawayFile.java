package dev.astro.net.utils.giveaway;

import dev.astro.net.file.*;
import dev.astro.net.*;
import java.io.*;

public class GiveawayFile
{
    public FileConfig file;
    public Vapor plugin;
    
    public GiveawayFile(final Vapor plugin) {
        this.plugin = plugin;
        this.file = new FileConfig(new File(new StringBuilder().append(plugin.getDataFolder()).toString()), "giveaway.yml");
    }
    
    public FileConfig getFile() {
        return this.file;
    }
    
    public Vapor getPlugin() {
        return this.plugin;
    }
}
