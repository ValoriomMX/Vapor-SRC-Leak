package dev.astro.net.utils.chat;

import net.minecraft.server.v1_7_R4.*;

public enum ClickAction
{
    OPEN_URL("OPEN_URL", 0, EnumClickAction.OPEN_URL), 
    OPEN_FILE("OPEN_FILE", 1, EnumClickAction.OPEN_FILE), 
    RUN_COMMAND("RUN_COMMAND", 2, EnumClickAction.RUN_COMMAND), 
    SUGGEST_COMMAND("SUGGEST_COMMAND", 3, EnumClickAction.SUGGEST_COMMAND);
    
    private EnumClickAction clickAction;
    
    private ClickAction(final String s, final int n, final EnumClickAction action) {
        this.clickAction = action;
    }
    
    public EnumClickAction getNMS() {
        return this.clickAction;
    }
}
