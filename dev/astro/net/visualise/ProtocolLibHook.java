package dev.astro.net.visualise;

import dev.astro.net.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import com.comphenix.protocol.reflect.*;
import com.comphenix.protocol.events.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.*;
import net.minecraft.server.v1_7_R4.*;
import com.comphenix.protocol.*;
import org.bukkit.*;

public class ProtocolLibHook
{
    private static final int STARTED_DIGGING = 0;
    private static final int FINISHED_DIGGING = 2;
    
    public static void hook(final Vapor hcf) {
        final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener((PacketListener)new PacketAdapter(hcf, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Client.BLOCK_PLACE }) {
            public void onPacketReceiving(final PacketEvent event) {
                final PacketContainer packet = event.getPacket();
                final StructureModifier modifier = packet.getIntegers();
                final Player player = event.getPlayer();
                try {
                    final int face;
                    if (modifier.size() < 4 || (face = (int)modifier.read(3)) == 255) {
                        return;
                    }
                    final Location location = new Location(player.getWorld(), (double)(int)modifier.read(0), (double)(int)modifier.read(1), (double)(int)modifier.read(2));
                    VisualBlock visualBlock = hcf.getVisualiseHandler().getVisualBlockAt(player, location);
                    if (visualBlock == null) {
                        return;
                    }
                    switch (face) {
                        case 0: {
                            location.add(0.0, -1.0, 0.0);
                            break;
                        }
                        case 1: {
                            location.add(0.0, 1.0, 0.0);
                            break;
                        }
                        case 2: {
                            location.add(0.0, 0.0, -1.0);
                            break;
                        }
                        case 3: {
                            location.add(0.0, 0.0, 1.0);
                            break;
                        }
                        case 4: {
                            location.add(-1.0, 0.0, 0.0);
                            break;
                        }
                        case 5: {
                            location.add(1.0, 0.0, 0.0);
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                    event.setCancelled(true);
                    final ItemStack stack = (ItemStack)packet.getItemModifier().read(0);
                    if (stack != null && (stack.getType().isBlock() || isLiquidSource(stack.getType()))) {
                        player.setItemInHand(player.getItemInHand());
                    }
                    if ((visualBlock = hcf.getVisualiseHandler().getVisualBlockAt(player, location)) != null) {
                        final VisualBlockData visualBlockData = visualBlock.getBlockData();
                        player.sendBlockChange(location, visualBlockData.getBlockType(), visualBlockData.getData());
                    }
                    else {
                        new BukkitRunnable() {
                            public void run() {
                                final Block block = location.getBlock();
                                player.sendBlockChange(location, block.getType(), block.getData());
                            }
                        }.runTask((Plugin)hcf);
                    }
                }
                catch (FieldAccessException ex) {}
            }
        });
        protocolManager.addPacketListener((PacketListener)new PacketAdapter(hcf, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Client.BLOCK_DIG }) {
            public void onPacketReceiving(final PacketEvent event) {
                final PacketContainer packet = event.getPacket();
                final StructureModifier modifier = packet.getIntegers();
                final Player player = event.getPlayer();
                try {
                    final int status = (int)modifier.read(4);
                    if (status == 0 || status == 2) {
                        final int x = (int)modifier.read(0);
                        final int y = (int)modifier.read(1);
                        final int z = (int)modifier.read(2);
                        final Location location = new Location(player.getWorld(), (double)x, (double)y, (double)z);
                        final VisualBlock visualBlock = hcf.getVisualiseHandler().getVisualBlockAt(player, location);
                        if (visualBlock == null) {
                            return;
                        }
                        event.setCancelled(true);
                        final VisualBlockData data = visualBlock.getBlockData();
                        if (status == 2) {
                            player.sendBlockChange(location, data.getBlockType(), data.getData());
                        }
                        else if (status == 0) {
                            final EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
                            if (player.getGameMode() == GameMode.CREATIVE || net.minecraft.server.v1_7_R4.Block.getById(data.getItemTypeId()).getDamage((EntityHuman)entityPlayer, entityPlayer.world, x, y, z) > 1.0f) {
                                player.sendBlockChange(location, data.getBlockType(), data.getData());
                            }
                        }
                    }
                }
                catch (FieldAccessException ex) {}
            }
        });
    }
    
    private static boolean isLiquidSource(final Material material) {
        switch (material) {
            case WATER_BUCKET:
            case LAVA_BUCKET: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
