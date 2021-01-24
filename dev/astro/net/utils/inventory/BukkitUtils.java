package dev.astro.net.utils.inventory;

import com.google.common.collect.*;
import net.minecraft.util.gnu.trove.list.*;
import net.minecraft.util.gnu.trove.list.array.*;
import com.google.common.base.*;
import java.util.stream.*;
import org.bukkit.command.*;
import org.bukkit.event.entity.*;
import org.bukkit.projectiles.*;
import dev.astro.net.utils.chat.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.potion.*;
import org.bukkit.entity.*;
import java.util.*;
import dev.astro.net.*;

public class BukkitUtils
{
    public static ImmutableMap<ChatColor, DyeColor> CHAT_DYE_COLOUR_MAP;
    private static ImmutableSet<PotionEffectType> DEBUFF_TYPES;
    private static int DEFAULT_COMPLETION_LIMIT;
    private static String STRAIGHT_LINE_TEMPLATE;
    public static String STRAIGHT_LINE_DEFAULT;
    private static TCharList COLOUR_CHARACTER_LIST;
    
    static {
        BukkitUtils.DEFAULT_COMPLETION_LIMIT = 80;
        BukkitUtils.STRAIGHT_LINE_TEMPLATE = String.valueOf(ChatColor.STRIKETHROUGH.toString()) + Strings.repeat("-", 256);
        BukkitUtils.STRAIGHT_LINE_DEFAULT = BukkitUtils.STRAIGHT_LINE_TEMPLATE.substring(0, 55);
        BukkitUtils.CHAT_DYE_COLOUR_MAP = (ImmutableMap<ChatColor, DyeColor>)ImmutableMap.builder().put((Object)ChatColor.AQUA, (Object)DyeColor.LIGHT_BLUE).put((Object)ChatColor.BLACK, (Object)DyeColor.BLACK).put((Object)ChatColor.BLUE, (Object)DyeColor.LIGHT_BLUE).put((Object)ChatColor.DARK_AQUA, (Object)DyeColor.CYAN).put((Object)ChatColor.DARK_BLUE, (Object)DyeColor.BLUE).put((Object)ChatColor.DARK_GRAY, (Object)DyeColor.GRAY).put((Object)ChatColor.DARK_GREEN, (Object)DyeColor.GREEN).put((Object)ChatColor.DARK_PURPLE, (Object)DyeColor.PURPLE).put((Object)ChatColor.DARK_RED, (Object)DyeColor.RED).put((Object)ChatColor.GOLD, (Object)DyeColor.ORANGE).put((Object)ChatColor.GRAY, (Object)DyeColor.SILVER).put((Object)ChatColor.GREEN, (Object)DyeColor.LIME).put((Object)ChatColor.LIGHT_PURPLE, (Object)DyeColor.MAGENTA).put((Object)ChatColor.RED, (Object)DyeColor.RED).put((Object)ChatColor.WHITE, (Object)DyeColor.WHITE).put((Object)ChatColor.YELLOW, (Object)DyeColor.YELLOW).build();
        BukkitUtils.DEBUFF_TYPES = (ImmutableSet<PotionEffectType>)ImmutableSet.builder().add((Object)PotionEffectType.BLINDNESS).add((Object)PotionEffectType.CONFUSION).add((Object)PotionEffectType.HARM).add((Object)PotionEffectType.HUNGER).add((Object)PotionEffectType.POISON).add((Object)PotionEffectType.SATURATION).add((Object)PotionEffectType.SLOW).add((Object)PotionEffectType.SLOW_DIGGING).add((Object)PotionEffectType.WEAKNESS).add((Object)PotionEffectType.WITHER).build();
        final ChatColor[] values = ChatColor.values();
        BukkitUtils.COLOUR_CHARACTER_LIST = (TCharList)new TCharArrayList(values.length);
        ChatColor[] array;
        for (int length = (array = values).length, i = 0; i < length; ++i) {
            final ChatColor colour = array[i];
            BukkitUtils.COLOUR_CHARACTER_LIST.add(colour.getChar());
        }
    }
    
    public static int countColoursUsed(final String id, final boolean ignoreDuplicates) {
        int count = 0;
        final Set<ChatColor> found = new HashSet<ChatColor>();
        for (int i = 1; i < id.length(); ++i) {
            final char current = id.charAt(i);
            if (BukkitUtils.COLOUR_CHARACTER_LIST.contains(current) && id.charAt(i - 1) == '&' && (ignoreDuplicates || found.add(ChatColor.getByChar(current)))) {
                ++count;
            }
        }
        return count;
    }
    
    public static List<String> getCompletions(final String[] args, final List<String> input) {
        return getCompletions(args, input, 80);
    }
    
    public static List<String> getCompletions(final String[] args, final List<String> input, final int limit) {
        Preconditions.checkNotNull((Object)args);
        Preconditions.checkArgument(args.length != 0);
        final String argument = args[args.length - 1];
        final String s;
        return input.stream().filter(string -> string.regionMatches(true, 0, s, 0, s.length())).limit(limit).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
    }
    
    public static String getDisplayName(final CommandSender sender) {
        Preconditions.checkNotNull((Object)sender);
        return (sender instanceof Player) ? ((Player)sender).getDisplayName() : sender.getName();
    }
    
    public static DyeColor toDyeColor(final ChatColor colour) {
        return (DyeColor)BukkitUtils.CHAT_DYE_COLOUR_MAP.get((Object)colour);
    }
    
    public static Player getFinalAttacker(final EntityDamageEvent ede, final boolean ignoreSelf) {
        Player attacker = null;
        if (ede instanceof EntityDamageByEntityEvent) {
            final EntityDamageByEntityEvent event = (EntityDamageByEntityEvent)ede;
            final Entity damager = event.getDamager();
            if (event.getDamager() instanceof Player) {
                attacker = (Player)damager;
            }
            else if (event.getDamager() instanceof Projectile) {
                final Projectile projectile = (Projectile)damager;
                final ProjectileSource shooter = (ProjectileSource)projectile.getShooter();
                if (shooter instanceof Player) {
                    attacker = (Player)shooter;
                }
            }
            if (attacker != null && ignoreSelf && event.getEntity().equals(attacker)) {
                attacker = null;
            }
        }
        return attacker;
    }
    
    public static Player playerWithNameOrUUID(final String string) {
        if (string == null) {
            return null;
        }
        return JavaUtils.isUUID(string) ? Bukkit.getPlayer(UUID.fromString(string)) : Bukkit.getPlayer(string);
    }
    
    @Deprecated
    public static OfflinePlayer offlinePlayerWithNameOrUUID(final String string) {
        if (string == null) {
            return null;
        }
        return JavaUtils.isUUID(string) ? Bukkit.getOfflinePlayer(UUID.fromString(string)) : Bukkit.getOfflinePlayer(string);
    }
    
    public static boolean isWithinX(final Location location, final Location other, final double distance) {
        return location.getWorld().equals(other.getWorld()) && Math.abs(other.getX() - location.getX()) <= distance && Math.abs(other.getZ() - location.getZ()) <= distance;
    }
    
    public static Location getHighestLocation(final Location origin) {
        return getHighestLocation(origin, null);
    }
    
    public static Location getHighestLocation(final Location origin, final Location def) {
        Preconditions.checkNotNull((Object)origin, (Object)"The location cannot be null");
        final Location cloned = origin.clone();
        final World world = cloned.getWorld();
        final int x = cloned.getBlockX();
        int y = world.getMaxHeight();
        final int z = cloned.getBlockZ();
        while (y > origin.getBlockY()) {
            final Block block = world.getBlockAt(x, --y, z);
            if (!block.isEmpty()) {
                final Location next = block.getLocation();
                next.setPitch(origin.getPitch());
                next.setYaw(origin.getYaw());
                return next;
            }
        }
        return def;
    }
    
    public static boolean isDebuff(final PotionEffectType type) {
        return BukkitUtils.DEBUFF_TYPES.contains((Object)type);
    }
    
    public static boolean isDebuff(final PotionEffect potionEffect) {
        return isDebuff(potionEffect.getType());
    }
    
    public static boolean isDebuff(final ThrownPotion thrownPotion) {
        for (final PotionEffect effect : thrownPotion.getEffects()) {
            if (isDebuff(effect)) {
                return true;
            }
        }
        return false;
    }
    
    public static Collection<? extends Player> getOnlinePlayers() {
        final Collection<Player> toReturn = new ArrayList<Player>();
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Vapor.getInstance().getServer().getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player player = onlinePlayers[i];
            toReturn.add(player);
        }
        return toReturn;
    }
}
