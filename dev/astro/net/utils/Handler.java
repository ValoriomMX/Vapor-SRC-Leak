package dev.astro.net.utils;

import dev.astro.net.*;

public class Handler
{
    private Vapor instance;
    
    public Handler(final Vapor instance) {
        this.instance = instance;
    }
    
    public void enable() {
    }
    
    public void disable() {
    }
    
    public Vapor getInstance() {
        return this.instance;
    }
}
