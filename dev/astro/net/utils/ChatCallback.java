package dev.astro.net.utils;

public abstract class ChatCallback
{
    public static int TTL;
    private long expiry;
    
    static {
        ChatCallback.TTL = 30000;
    }
    
    public ChatCallback() {
        this.expiry = System.currentTimeMillis() + ChatCallback.TTL;
    }
    
    public boolean expired() {
        return System.currentTimeMillis() > this.expiry;
    }
    
    public abstract void run(final String p0);
}
