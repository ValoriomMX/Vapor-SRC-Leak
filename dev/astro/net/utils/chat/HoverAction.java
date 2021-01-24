package dev.astro.net.utils.chat;

import net.minecraft.server.v1_7_R4.*;

public enum HoverAction
{
    SHOW_TEXT("SHOW_TEXT", 0, EnumHoverAction.SHOW_TEXT), 
    SHOW_ITEM("SHOW_ITEM", 1, EnumHoverAction.SHOW_ITEM), 
    SHOW_ACHIEVEMENT("SHOW_ACHIEVEMENT", 2, EnumHoverAction.SHOW_ACHIEVEMENT);
    
    private EnumHoverAction hoverAction;
    
    private HoverAction(final String s, final int n, final EnumHoverAction hoverAction) {
        this.hoverAction = hoverAction;
    }
    
    public EnumHoverAction getNMS() {
        return this.hoverAction;
    }
}
