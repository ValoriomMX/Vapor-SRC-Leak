package dev.astro.net.utils.cuboid;

import org.bukkit.block.*;

public enum CuboidDirection
{
    NORTH("NORTH", 0), 
    EAST("EAST", 1), 
    SOUTH("SOUTH", 2), 
    WEST("WEST", 3), 
    UP("UP", 4), 
    DOWN("DOWN", 5), 
    HORIZONTAL("HORIZONTAL", 6), 
    VERTICAL("VERTICAL", 7), 
    BOTH("BOTH", 8), 
    UNKNOWN("UNKNOWN", 9);
    
    private CuboidDirection(final String s, final int n) {
    }
    
    public CuboidDirection opposite() {
        switch (this) {
            case NORTH: {
                return CuboidDirection.SOUTH;
            }
            case EAST: {
                return CuboidDirection.WEST;
            }
            case SOUTH: {
                return CuboidDirection.NORTH;
            }
            case WEST: {
                return CuboidDirection.EAST;
            }
            case HORIZONTAL: {
                return CuboidDirection.VERTICAL;
            }
            case VERTICAL: {
                return CuboidDirection.HORIZONTAL;
            }
            case UP: {
                return CuboidDirection.DOWN;
            }
            case DOWN: {
                return CuboidDirection.UP;
            }
            case BOTH: {
                return CuboidDirection.BOTH;
            }
            default: {
                return CuboidDirection.UNKNOWN;
            }
        }
    }
    
    public BlockFace toBukkitDirection() {
        switch (this) {
            case NORTH: {
                return BlockFace.NORTH;
            }
            case EAST: {
                return BlockFace.EAST;
            }
            case SOUTH: {
                return BlockFace.SOUTH;
            }
            case WEST: {
                return BlockFace.WEST;
            }
            case UP: {
                return BlockFace.UP;
            }
            case DOWN: {
                return BlockFace.DOWN;
            }
            default: {
                return null;
            }
        }
    }
}
