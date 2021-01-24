package dev.astro.net.utils;

import org.bukkit.*;
import java.util.concurrent.*;
import dev.astro.net.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Configuration extends Handler
{
    public static boolean DEATHBAN_ROOM;
    public String DEFAULT_SERVER_TIME_ZONE;
    private List<String> reclaimMessageNoPermission;
    private String reclaimMessageRedeem;
    private List<String> reviveMessageNoPermission;
    private String reviveMessageRedeem;
    private int subclaimNameMinCharacters;
    private int subclaimNameMaxCharacters;
    public int factionNameMinCharacters;
    private int factionNameMaxCharacters;
    private int maxMembers;
    private int roadMinHeight;
    private int roadMaxHeight;
    private int maxAllysPerFaction;
    public static int CONQUEST_REQUIRED_WIN_POINTS;
    public static int maxClaimsPerFaction;
    private int conquestDeathLoss;
    private int conquestWinPoints;
    public static int warzoneRadius;
    public static ChatColor teammateColor;
    public static ChatColor allyColor;
    private ChatColor captainColor;
    public static ChatColor enemyColor;
    private ChatColor spawnColor;
    private ChatColor endportalColor;
    private ChatColor roadColor;
    private ChatColor warzoneColor;
    private ChatColor glowstoneColor;
    private ChatColor dtcColor;
    public static ChatColor wildernessColor;
    private boolean kitMap;
    private boolean DevCommand;
    private boolean disableObsidianGenerators;
    public static boolean allowClaimingOnRoads;
    public static boolean IS_UHCF;
    private double dtrIncrementBetweenUpdate;
    private double maxDtr;
    private List<String> blockedfacnames;
    public static List<String> disabledMobs;
    private double LootingXPMultiplier;
    private double FortuneXPMultiplier;
    private double GlobalXPMultiplier;
    private double FishingXPMultiplier;
    private double LuckXPMultiplier;
    private double SmeltXPMultiplier;
    private long dtrUpdate;
    public static long COMBAT_LOG_DESPAWN_TICKS;
    public static boolean COMBAT_LOG_PREVENTION_ENABLED;
    public static Map<World.Environment, Integer> bordersizes;
    
    static {
        Configuration.COMBAT_LOG_DESPAWN_TICKS = TimeUnit.SECONDS.toMillis(Vapor.getInstance().getConfig().getLong("COMBATLOG.DESPAWN-DELAY-TICKS"));
        Configuration.COMBAT_LOG_PREVENTION_ENABLED = Vapor.getInstance().getConfig().getBoolean("COMBATLOG.ENABLED");
        Configuration.bordersizes = new EnumMap<World.Environment, Integer>(World.Environment.class);
    }
    
    public ChatColor getEndportalColor() {
        return this.endportalColor;
    }
    
    public void setEndportalColor(final ChatColor endportalColor) {
        this.endportalColor = endportalColor;
    }
    
    public ChatColor getDtcColor() {
        return this.dtcColor;
    }
    
    public void setDtcColor(final ChatColor dtcColor) {
        this.dtcColor = dtcColor;
    }
    
    public static boolean isIS_UHCF() {
        return Configuration.IS_UHCF;
    }
    
    public static void setIS_UHCF(final boolean iS_UHCF) {
        Configuration.IS_UHCF = iS_UHCF;
    }
    
    public List<String> getBlockedfacnames() {
        return this.blockedfacnames;
    }
    
    public void setBlockedfacnames(final List<String> blockedfacnames) {
        this.blockedfacnames = blockedfacnames;
    }
    
    public double getLootingXPMultiplier() {
        return this.LootingXPMultiplier;
    }
    
    public void setLootingXPMultiplier(final double lootingXPMultiplier) {
        this.LootingXPMultiplier = lootingXPMultiplier;
    }
    
    public double getFortuneXPMultiplier() {
        return this.FortuneXPMultiplier;
    }
    
    public void setFortuneXPMultiplier(final double fortuneXPMultiplier) {
        this.FortuneXPMultiplier = fortuneXPMultiplier;
    }
    
    public double getGlobalXPMultiplier() {
        return this.GlobalXPMultiplier;
    }
    
    public void setGlobalXPMultiplier(final double globalXPMultiplier) {
        this.GlobalXPMultiplier = globalXPMultiplier;
    }
    
    public double getFishingXPMultiplier() {
        return this.FishingXPMultiplier;
    }
    
    public void setFishingXPMultiplier(final double fishingXPMultiplier) {
        this.FishingXPMultiplier = fishingXPMultiplier;
    }
    
    public double getLuckXPMultiplier() {
        return this.LuckXPMultiplier;
    }
    
    public void setLuckXPMultiplier(final double luckXPMultiplier) {
        this.LuckXPMultiplier = luckXPMultiplier;
    }
    
    public double getSmeltXPMultiplier() {
        return this.SmeltXPMultiplier;
    }
    
    public void setSmeltXPMultiplier(final double smeltXPMultiplier) {
        this.SmeltXPMultiplier = smeltXPMultiplier;
    }
    
    public static Map<World.Environment, Integer> getBordersizes() {
        return Configuration.bordersizes;
    }
    
    public static void setBordersizes(final Map<World.Environment, Integer> bordersizes) {
        Configuration.bordersizes = bordersizes;
    }
    
    public static long getCombatLogDespawnTicks() {
        return Configuration.COMBAT_LOG_DESPAWN_TICKS;
    }
    
    public static boolean isCombatLogPreventionEnabled() {
        return Configuration.COMBAT_LOG_PREVENTION_ENABLED;
    }
    
    public void setReclaimMessageNoPermission(final List<String> reclaimMessageNoPermission) {
        this.reclaimMessageNoPermission = reclaimMessageNoPermission;
    }
    
    public void setReclaimMessageRedeem(final String reclaimMessageRedeem) {
        this.reclaimMessageRedeem = reclaimMessageRedeem;
    }
    
    public void setReviveMessageNoPermission(final List<String> reviveMessageNoPermission) {
        this.reviveMessageNoPermission = reviveMessageNoPermission;
    }
    
    public void setReviveMessageRedeem(final String reviveMessageRedeem) {
        this.reviveMessageRedeem = reviveMessageRedeem;
    }
    
    public static List<String> getDisabledMobs() {
        return Configuration.disabledMobs;
    }
    
    public Configuration(final Vapor instance) {
        super(instance);
        this.blockedfacnames = new ArrayList<String>();
        this.kitMap = Vapor.getInstance().getConfigFile().getBoolean("KITMAP");
        this.reclaimMessageNoPermission = Vapor.getInstance().getConfigFile().getStringList("RECLAIM-MESSAGES.NO-PERMISSION");
        this.reclaimMessageRedeem = Vapor.getInstance().getConfigFile().getString("RECLAIM-MESSAGES.RECLAIM-REDEEMED");
        this.reviveMessageNoPermission = Vapor.getInstance().getConfigFile().getStringList("REVIVE-MESSAGES.NO-PERMISSION");
        this.reviveMessageRedeem = Vapor.getInstance().getConfigFile().getString("REVIVE-MESSAGES.REVIVE-REDEMEED");
        this.blockedfacnames = Vapor.getInstance().getConfigFile().getStringList("BLOCKEDFAC-NAMES");
        Configuration.disabledMobs = Vapor.getInstance().getConfigFile().getStringList("DISABLEDMOBS").stream().map((Function<? super Object, ?>)String::toUpperCase).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
        if (this.kitMap) {
            Configuration.bordersizes.put(World.Environment.NORMAL, Vapor.getInstance().getConfigFile().getInt("OVERWORLD"));
            Configuration.bordersizes.put(World.Environment.NETHER, Vapor.getInstance().getConfigFile().getInt("NETHER"));
            Configuration.bordersizes.put(World.Environment.THE_END, Vapor.getInstance().getConfigFile().getInt("END"));
            Configuration.warzoneRadius = Vapor.getInstance().getConfigFile().getInt("WARZONE-RADIUS");
            this.maxMembers = Vapor.getInstance().getConfigFile().getInt("MAX-MEMBERS");
            Configuration.maxClaimsPerFaction = Vapor.getInstance().getConfigFile().getInt("MAX-CLAIMS-PER-FAC");
            this.dtrUpdate = Vapor.getInstance().getConfigFile().getLong("DTR-UPDATE");
            this.dtrIncrementBetweenUpdate = Vapor.getInstance().getConfigFile().getDouble("DTR-INCREMENT");
            this.maxDtr = Vapor.getInstance().getConfigFile().getDouble("MAX-DTR");
        }
        else {
            Configuration.bordersizes.put(World.Environment.NORMAL, Vapor.getInstance().getConfigFile().getInt("OVERWORLD"));
            Configuration.bordersizes.put(World.Environment.NETHER, Vapor.getInstance().getConfigFile().getInt("NETHER"));
            Configuration.bordersizes.put(World.Environment.THE_END, Vapor.getInstance().getConfigFile().getInt("END"));
            Configuration.warzoneRadius = Vapor.getInstance().getConfigFile().getInt("WARZONE-RADIUS");
            this.maxMembers = Vapor.getInstance().getConfigFile().getInt("MAX-MEMBERS");
            Configuration.maxClaimsPerFaction = Vapor.getInstance().getConfigFile().getInt("MAX-CLAIMS-PER-FAC");
            this.LootingXPMultiplier = Vapor.getInstance().getConfig().getDouble("EXP.LOOTING");
            this.FortuneXPMultiplier = Vapor.getInstance().getConfig().getDouble("EXP.FORTUNE");
            this.GlobalXPMultiplier = Vapor.getInstance().getConfig().getDouble("EXP.GLOBAL");
            this.FishingXPMultiplier = Vapor.getInstance().getConfig().getDouble("EXP.FISHING");
            this.LuckXPMultiplier = Vapor.getInstance().getConfig().getDouble("EXP.LUCK");
            this.SmeltXPMultiplier = Vapor.getInstance().getConfig().getDouble("EXP.SMELTING");
            this.dtrUpdate = Vapor.getInstance().getConfigFile().getLong("DTR-UPDATE");
            this.dtrIncrementBetweenUpdate = Vapor.getInstance().getConfigFile().getDouble("DTR-INCREMENT");
            this.maxDtr = Vapor.getInstance().getConfigFile().getDouble("MAX-DTR");
        }
        this.subclaimNameMinCharacters = Vapor.getInstance().getConfigFile().getInt("SUBCLAIMS.MIN-NAME");
        this.subclaimNameMaxCharacters = Vapor.getInstance().getConfigFile().getInt("SUBCLAIMS.MAX-NAME");
        this.factionNameMinCharacters = Vapor.getInstance().getConfigFile().getInt("MIN-NAME");
        this.factionNameMaxCharacters = Vapor.getInstance().getConfigFile().getInt("MAX-NAME");
        this.roadMinHeight = Vapor.getInstance().getConfigFile().getInt("ROAD-MIN-HEIGHT");
        this.roadMaxHeight = Vapor.getInstance().getConfigFile().getInt("ROAD-MAX-HEIGHT");
        this.conquestDeathLoss = Vapor.getInstance().getConfigFile().getInt("CONQUEST.DEATH-LOSS");
        this.conquestWinPoints = Vapor.getInstance().getConfigFile().getInt("CONQUEST.WIN-POINTS");
        this.maxAllysPerFaction = Vapor.getInstance().getConfigFile().getInt("MAX-ALLIES");
        Configuration.teammateColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.TEAMMATE"));
        Configuration.allyColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.ALLY"));
        this.captainColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.CAPTAIN"));
        Configuration.enemyColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.ENEMY"));
        this.spawnColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.SPAWN"));
        this.roadColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.ROAD"));
        this.warzoneColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.WARZONE"));
        final int CONQUEST_POINT_LOSS_PER_DEATH = Vapor.getInstance().getConfig().getInt("CONQUEST.LOSS-POINT-DEATH");
        final int CONQUEST_REQUIRED_WIN_POINTS = Vapor.getInstance().getConfig().getInt("CONQUEST.REQUIRED-POINTS");
        this.glowstoneColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.GLOWSTONE"));
        this.dtcColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.DTC"));
        Configuration.wildernessColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.WILDERNESS"));
        this.endportalColor = ChatColor.valueOf(Vapor.getInstance().getConfig().getString("COLORS.ENDPORTAL"));
        Configuration.allowClaimingOnRoads = false;
        this.disableObsidianGenerators = true;
        Configuration.IS_UHCF = Vapor.getInstance().getConfigFile().getBoolean("UHCF");
    }
    
    public double getExpMultiplierLooting() {
        return this.LootingXPMultiplier;
    }
    
    public double getExpMultiplierFortune() {
        return this.FortuneXPMultiplier;
    }
    
    public double getExpMultiplierGlobal() {
        return this.GlobalXPMultiplier;
    }
    
    public double getExpMultiplierFishing() {
        return this.FishingXPMultiplier;
    }
    
    public double getExpMultiplierLuck() {
        return this.LuckXPMultiplier;
    }
    
    public double getExpMultiplierSmelting() {
        return this.SmeltXPMultiplier;
    }
    
    public List<String> getReclaimMessageNoPermission() {
        return this.reclaimMessageNoPermission;
    }
    
    public String getReclaimMessageRedeem() {
        return this.reclaimMessageRedeem;
    }
    
    public List<String> getReviveMessageNoPermission() {
        return this.reviveMessageNoPermission;
    }
    
    public String getReviveMessageRedeem() {
        return this.reviveMessageRedeem;
    }
    
    public List<String> getBlockedFacNames() {
        return this.blockedfacnames;
    }
    
    public int getSubclaimNameMinCharacters() {
        return this.subclaimNameMinCharacters;
    }
    
    public void setSubclaimNameMinCharacters(final int subclaimNameMinCharacters) {
        this.subclaimNameMinCharacters = subclaimNameMinCharacters;
    }
    
    public int getSubclaimNameMaxCharacters() {
        return this.subclaimNameMaxCharacters;
    }
    
    public void setSubclaimNameMaxCharacters(final int subclaimNameMaxCharacters) {
        this.subclaimNameMaxCharacters = subclaimNameMaxCharacters;
    }
    
    public int getFactionNameMinCharacters() {
        return this.factionNameMinCharacters;
    }
    
    public void setFactionNameMinCharacters(final int factionNameMinCharacters) {
        this.factionNameMinCharacters = factionNameMinCharacters;
    }
    
    public int getFactionNameMaxCharacters() {
        return this.factionNameMaxCharacters;
    }
    
    public void setFactionNameMaxCharacters(final int factionNameMaxCharacters) {
        this.factionNameMaxCharacters = factionNameMaxCharacters;
    }
    
    public int getMaxMembers() {
        return this.maxMembers;
    }
    
    public void setMaxMembers(final int maxMembers) {
        this.maxMembers = maxMembers;
    }
    
    public int getRoadMinHeight() {
        return this.roadMinHeight;
    }
    
    public void setRoadMinHeight(final int roadMinHeight) {
        this.roadMinHeight = roadMinHeight;
    }
    
    public int getRoadMaxHeight() {
        return this.roadMaxHeight;
    }
    
    public void setRoadMaxHeight(final int roadMaxHeight) {
        this.roadMaxHeight = roadMaxHeight;
    }
    
    public int getMaxAllysPerFaction() {
        return this.maxAllysPerFaction;
    }
    
    public void setMaxAllysPerFaction(final int maxAllysPerFaction) {
        this.maxAllysPerFaction = maxAllysPerFaction;
    }
    
    public int getMaxClaimsPerFaction() {
        return Configuration.maxClaimsPerFaction;
    }
    
    public void setMaxClaimsPerFaction(final int maxClaimsPerFaction) {
        Configuration.maxClaimsPerFaction = maxClaimsPerFaction;
    }
    
    public int getConquestDeathLoss() {
        return this.conquestDeathLoss;
    }
    
    public void setConquestDeathLoss(final int conquestDeathLoss) {
        this.conquestDeathLoss = conquestDeathLoss;
    }
    
    public int getConquestWinPoints() {
        return this.conquestWinPoints;
    }
    
    public void setConquestWinPoints(final int conquestWinPoints) {
        this.conquestWinPoints = conquestWinPoints;
    }
    
    public int getWarzoneRadius() {
        return Configuration.warzoneRadius;
    }
    
    public void setWarzoneRadius(final int warzoneRadius) {
        Configuration.warzoneRadius = warzoneRadius;
    }
    
    public ChatColor getTeammateColor() {
        return Configuration.teammateColor;
    }
    
    public void setTeammateColor(final ChatColor teammateColor) {
        Configuration.teammateColor = teammateColor;
    }
    
    public ChatColor getAllyColor() {
        return Configuration.allyColor;
    }
    
    public void setAllyColor(final ChatColor allyColor) {
        Configuration.allyColor = allyColor;
    }
    
    public ChatColor getCaptainColor() {
        return this.captainColor;
    }
    
    public void setCaptainColor(final ChatColor captainColor) {
        this.captainColor = captainColor;
    }
    
    public ChatColor getEnemyColor() {
        return Configuration.enemyColor;
    }
    
    public void setEnemyColor(final ChatColor enemyColor) {
        Configuration.enemyColor = enemyColor;
    }
    
    public ChatColor getSpawnColor() {
        return this.spawnColor;
    }
    
    public void setSpawnColor(final ChatColor spawnColor) {
        this.spawnColor = spawnColor;
    }
    
    public ChatColor getRoadColor() {
        return this.roadColor;
    }
    
    public void setRoadColor(final ChatColor roadColor) {
        this.roadColor = roadColor;
    }
    
    public ChatColor getWarzoneColor() {
        return this.warzoneColor;
    }
    
    public void setWarzoneColor(final ChatColor warzoneColor) {
        this.warzoneColor = warzoneColor;
    }
    
    public ChatColor getGlowstoneColor() {
        return this.glowstoneColor;
    }
    
    public void setGlowstoneColor(final ChatColor glowstoneColor) {
        this.glowstoneColor = glowstoneColor;
    }
    
    public ChatColor getEndPortalColor() {
        return this.endportalColor;
    }
    
    public void setEndPortalColor(final ChatColor endportalColor) {
        this.endportalColor = endportalColor;
    }
    
    public ChatColor getDTCColor() {
        return this.dtcColor;
    }
    
    public void setDTCColor(final ChatColor dtcColor) {
        this.dtcColor = dtcColor;
    }
    
    public ChatColor getWildernessColor() {
        return Configuration.wildernessColor;
    }
    
    public void setWildernessColor(final ChatColor wildernessColor) {
        Configuration.wildernessColor = wildernessColor;
    }
    
    public boolean isKitMap() {
        return this.kitMap;
    }
    
    public void setKitMap(final boolean kitMap) {
        this.kitMap = kitMap;
    }
    
    public boolean isDevCommand() {
        return this.DevCommand;
    }
    
    public void setDevCommand(final boolean DevCommand) {
        this.DevCommand = DevCommand;
    }
    
    public boolean isDisableObsidianGenerators() {
        return this.disableObsidianGenerators;
    }
    
    public void setDisableObsidianGenerators(final boolean disableObsidianGenerators) {
        this.disableObsidianGenerators = disableObsidianGenerators;
    }
    
    public boolean isAllowClaimingOnRoads() {
        return Configuration.allowClaimingOnRoads;
    }
    
    public void setAllowClaimingOnRoads(final boolean allowClaimingOnRoads) {
        Configuration.allowClaimingOnRoads = allowClaimingOnRoads;
    }
    
    public double getDtrIncrementBetweenUpdate() {
        return this.dtrIncrementBetweenUpdate;
    }
    
    public void setDtrIncrementBetweenUpdate(final double dtrIncrementBetweenUpdate) {
        this.dtrIncrementBetweenUpdate = dtrIncrementBetweenUpdate;
    }
    
    public double getMaxDtr() {
        return this.maxDtr;
    }
    
    public void setMaxDtr(final double maxDtr) {
        this.maxDtr = maxDtr;
    }
    
    public long getDtrUpdate() {
        return this.dtrUpdate;
    }
    
    public String DEFAULT_SERVER_TIME_ZONE() {
        return this.DEFAULT_SERVER_TIME_ZONE;
    }
    
    public void setDtrUpdate(final long dtrUpdate) {
        this.dtrUpdate = dtrUpdate;
    }
    
    public long getDeathbanBaseDurationMinutes() {
        return 0L;
    }
}
