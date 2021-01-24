package dev.astro.net.utils.giveaway;

import dev.astro.net.*;

public enum Giveaway
{
    INSTANCE("INSTANCE", 0, "INSTANCE", 0);
    
    private Giveaway(final String s2, final int n2, final String s, final int n) {
    }
    
    public void enable() {
        final GiveawayFile file = new GiveawayFile(Vapor.getInstance());
        file.getFile().getConfig().set("active", (Object)false);
        file.getFile().getConfig().set("number", (Object)null);
        this.setPrefix();
        file.getFile().save();
    }
    
    public void start(final Integer number) {
        final GiveawayFile file = new GiveawayFile(Vapor.getInstance());
        file.getFile().getConfig().set("active", (Object)true);
        file.getFile().getConfig().set("number", (Object)number);
        file.getFile().save();
    }
    
    public void reset() {
        final GiveawayFile file = new GiveawayFile(Vapor.getInstance());
        file.getFile().getConfig().set("active", (Object)false);
        file.getFile().getConfig().set("number", (Object)null);
        file.getFile().save();
    }
    
    public boolean isActive() {
        final GiveawayFile file = new GiveawayFile(Vapor.getInstance());
        return file.getFile().getConfig().getBoolean("active");
    }
    
    public int getNumber() {
        final GiveawayFile file = new GiveawayFile(Vapor.getInstance());
        return file.getFile().getConfig().getInt("number");
    }
    
    public void setPrefix() {
        final GiveawayFile file = new GiveawayFile(Vapor.getInstance());
        if (file.getFile().getConfig().getString("prefix") == null) {
            file.getFile().getConfig().set("prefix", (Object)this.getDefaultPrefix());
        }
    }
    
    public String getDefaultPrefix() {
        return "&8[&6&lGiveaway&8]";
    }
    
    public String getPrefix() {
        final GiveawayFile file = new GiveawayFile(Vapor.getInstance());
        return file.getFile().getConfig().getString("prefix");
    }
}
