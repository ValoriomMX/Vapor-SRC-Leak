package dev.astro.net.utils;

import java.util.*;
import org.bukkit.entity.*;
import org.apache.commons.lang.time.*;

public class Cooldown
{
    private Map<UUID, Long> cooldownMap;
    
    public Cooldown() {
        this.cooldownMap = new HashMap<UUID, Long>();
    }
    
    public void CooldownApply(final Player player, final long cooldown) {
        this.cooldownMap.put(player.getUniqueId(), System.currentTimeMillis() + cooldown);
    }
    
    public boolean onCooldown(final Player player) {
        return this.cooldownMap.containsKey(player.getUniqueId()) && this.cooldownMap.get(player.getUniqueId()) >= System.currentTimeMillis();
    }
    
    public void cooldownRemove(final Player player) {
        this.cooldownMap.remove(player.getUniqueId());
    }
    
    public String getRemaining(final Player player) {
        final long l = this.cooldownMap.get(player.getUniqueId()) - System.currentTimeMillis();
        return DurationFormatUtils.formatDuration(l, "s");
    }
}
