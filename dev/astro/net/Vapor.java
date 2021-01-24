package dev.astro.net;

import org.bukkit.plugin.java.*;
import net.milkbowl.vault.chat.*;
import net.milkbowl.vault.permission.*;
import dev.astro.net.games.crate.*;
import dev.astro.net.handlers.reclaim.*;
import dev.astro.net.handlers.revive.*;
import dev.astro.net.holograms.*;
import dev.astro.net.handlers.basics.*;
import dev.astro.net.handlers.essentials.*;
import dev.astro.net.handlers.elevators.*;
import dev.astro.net.pvpclass.utils.*;
import dev.astro.net.pvpclass.utils.bard.*;
import com.sk89q.worldedit.bukkit.*;
import dev.astro.net.playerdata.*;
import dev.astro.net.file.*;
import dev.astro.net.pvpclass.*;
import dev.astro.net.timer.specialitems.*;
import dev.astro.net.timer.type.*;
import dev.astro.net.commands.*;
import dev.astro.net.tournaments.*;
import dev.astro.net.handlers.tournaments.*;
import java.util.concurrent.*;
import dev.astro.net.combatlog.*;
import dev.astro.net.utils.giveaway.*;
import dev.astro.net.utils.inventory.item.*;
import dev.astro.net.tablist.layout.*;
import com.comphenix.protocol.*;
import dev.astro.net.tablist.*;
import com.comphenix.protocol.events.*;
import dev.astro.net.scoreboard.*;
import dev.astro.net.scoreboard.provider.*;
import dev.astro.net.nametag.provider.*;
import dev.astro.net.nametag.*;
import org.bukkit.scheduler.*;
import dev.astro.net.utils.inventory.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import org.bukkit.event.*;
import dev.astro.net.handlers.fix.*;
import dev.astro.net.handlers.lff.*;
import dev.astro.net.handlers.*;
import dev.astro.net.visualise.*;
import dev.astro.net.games.dtc.*;
import dev.astro.net.handlers.manager.*;
import org.bukkit.plugin.*;
import org.bukkit.block.*;
import dev.astro.net.utils.commands.*;
import java.util.function.*;
import dev.astro.net.commands.inventory.*;
import org.bukkit.command.*;
import dev.astro.net.factions.commands.staff.*;
import dev.astro.net.timer.*;
import dev.astro.net.games.conquest.*;
import dev.astro.net.games.koth.*;
import dev.astro.net.deathban.lives.*;
import dev.astro.net.commands.tournament.*;
import dev.astro.net.commands.death.*;
import dev.astro.net.commands.vapor.*;
import dev.astro.net.commands.giveaway.*;
import dev.astro.net.commands.perks.*;
import dev.astro.net.commands.chat.*;
import dev.astro.net.commands.staff.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.*;
import org.bukkit.configuration.serialization.*;
import dev.astro.net.kit.*;
import dev.astro.net.utils.cuboid.*;
import dev.astro.net.games.*;
import dev.astro.net.deathban.*;
import dev.astro.net.factions.claim.*;
import dev.astro.net.user.*;
import dev.astro.net.games.faction.*;
import dev.astro.net.factions.*;
import dev.astro.net.factions.type.*;
import dev.astro.net.factions.mountain.*;
import java.io.*;
import java.text.*;
import org.apache.commons.lang.time.*;
import dev.astro.net.utils.*;
import dev.astro.net.utils.chat.*;
import java.util.*;
import dev.astro.net.commands.other.*;
import org.bukkit.*;

public class Vapor extends JavaPlugin
{
    public static File SchedulesData;
    public static Chat chat;
    public static Vapor instance;
    private Configuration configuration;
    private PlayerDataHandler playerDataHandler;
    public Permission permissions;
    private CombatLogListener combatLogListener;
    private FocusCommand focusCommand;
    private UserManager userManager;
    private MessageConfig messageConfig;
    private KeyManager keyManager;
    public static long MINUTE;
    public static long HOUR;
    private ReclaimHandler reclaimHandler;
    private ArcherUpgradesHandler archerUpgradesHandler;
    private ConfigurationFile tab;
    private ConfigurationFile waypoints;
    private Config holograms;
    private ReviveHandler reviveHandler;
    private DeathbanManager deathbanManager;
    private OreMountainManager oreMountainManager;
    private PurgeHandler purgeHandler;
    private SwitchStickHandler switchStickHandler;
    private ShieldHandler shieldHandler;
    private KitManager kitManager;
    private StaffChatHandler staffChatHandler;
    private HologramsManager hologramsManager;
    private GlowstoneMountainManager glowstoneMountainManager;
    private WorldEditCrashFixHandler worldEditCrashFixHandler;
    private BackHandler backHandler;
    private GrapplingHookHandler grapplingHookHandler;
    private GodHandler godHandler;
    private KitExecutor kitExecutor;
    private NametagInfo nametagInfo;
    private HiderManager hiderManager;
    private HCFHandler hcfHandler;
    private WarpHandler warpHandler;
    private ArmorFixListener armorfixHandler;
    private BlockHitFixListener blockhitHandler;
    private BlockJumpGlitchFixListener blockjumpHandler;
    private EventScheduler eventScheduler;
    private BoatGlitchFixListener boatglitchHandler;
    private ColonCommandFixListener coloncommandHandler;
    private CreativeClickListener creativeclickHandler;
    private BeaconRenamerHandler beaconRenamerHandler;
    private CustomTimerManager customTimerManager;
    private HitDetectionListener hitdetectionHandler;
    private IlegalEnchantListener ilegalenchantHandler;
    private InfinityArrowFixListener infinityarrowHandler;
    private PortalTrapFixListener portaltrapHandler;
    private NoPotLagListener nopotlagHandler;
    private MobFarmListener mobfarmHandler;
    private PhaseGlitchListener phaseglitchHandler;
    private BlockPickupHandler blockPickupHandler;
    private SignElevatorHandler signElevatorHandler;
    private MinecartElevatorHandler minecartelevatorHandler;
    private ClaimWandHandler claimWandHandler;
    private SignSubclaimHandler signSubclaimHandler;
    private SkullHandler skullHandler;
    private EnchantmentLimiterHandler enchantmentLimiterHandler;
    private PotionLimitHandler potionLimitHandler;
    private BorderHandler borderHandler;
    private AntiSpam antispamHandler;
    private ChatHandler chatHandler;
    private BrewingSpeedHandler brewingSpeedHandler;
    private ClaimHandler claimHandler;
    private CrowbarHandler crowbarHandler;
    private DeathMessageHandler deathMessageHandler;
    private DeathSignHandler deathSignHandler;
    private EOTWHandler eotwHandler;
    private EventSignHandler eventSignHandler;
    private ExpHandler expHandler;
    private DynamicPlayerHandler dynamicPlayerHandler;
    private FoundDiamondsHandler foundDiamondsHandler;
    private FreezeHandler freezeHandler;
    private PlayerFaction playerfaction;
    private ItemStatTrackingHandler itemStatTrackingHandler;
    private RallyHandler rallyHandler;
    private KitMapHandler kitMapHandler;
    private MapKitHandler mapKitHandler;
    private ChatControlHandler chatControlHandler;
    private PortalHandler portalHandler;
    private HorseNMS horseNMS;
    private ShopSignHandler shopSignHandler;
    private SignHandler signHandler;
    private FactionHandler factionHandler;
    private FurnaceSpeedHandler furnaceSpeedHandler;
    private MobStackHandler mobStackHandler;
    private ProtectionHandler protectionHandler;
    private WhitelistHandler wlHandler;
    private VisualiseHandler visualiseHandler;
    private VanishCommand vanishCommand;
    private StaffModeHandler staffModeHandler;
    private FactionManager factionManager;
    private ArmorClassManager armorClassManager;
    private TimerManager timerManager;
    private EffectRestorer effectRestorer;
    private EOTWUtilsHandler eotwUtils;
    private EndPortalHandler endPortalHandler;
    private ClassWarmupHandler classLoadTimer;
    private WorldEditPlugin worldEdit;
    private ItemDB itemDB;
    private PlayerData playerData;
    private ConfigFile configFile;
    private PrefixFile prefixFile;
    private ScoreboardFile scoreboardFile;
    private LimitersFile limitersFile;
    private UtilitiesFile utilitiesFile;
    private ConfigHandler configHandler;
    private Archer archer;
    private Bard bard;
    private Ghost ghost;
    private DeathbanListener deathbanListener;
    private Bomber bomber;
    private Miner minerData;
    private Rogue rogue;
    private AppleHandler appleHandler;
    private EnderpearlHandler enderpearlHandler;
    private PvPTimerHandler pvpTimerHandler;
    private GappleHandler gappleHandler;
    private SOTWHandler sotwHandler;
    private SwitcherHandler switcherHandler;
    private RottenEggHandler rottenEggHandler;
    private RebootHandler rebootHandler;
    private CommandHandler commandExecutor;
    public String wngnq;
    private DeathHandler deathListener;
    public TournamentManager tournamentManager;
    private TournamentSignListener tournamentSignListener;
    private TournamentListener tournamentHandler;
    private ChatCallbackManager chatCallbackManager;
    private Location killsLocation;
    private PersistableLocation pointsLocation;
    private PersistableLocation deathsLocation;
    
    static {
        Vapor.MINUTE = TimeUnit.MINUTES.toMillis(1L);
        Vapor.HOUR = TimeUnit.HOURS.toMillis(1L);
        Vapor.chat = null;
    }
    
    public HologramsManager getHologramsManager() {
        return this.hologramsManager;
    }
    
    private boolean setupPermissions() {
        this.permissions = (Permission)this.getServer().getServicesManager().getRegistration((Class)Permission.class).getProvider();
        return this.permissions != null;
    }
    
    private boolean setupChat() {
        final RegisteredServiceProvider<Chat> chatProvider = (RegisteredServiceProvider<Chat>)this.getServer().getServicesManager().getRegistration((Class)Chat.class);
        if (chatProvider != null) {
            Vapor.chat = (Chat)chatProvider.getProvider();
        }
        return Vapor.chat != null;
    }
    
    public void onEnable() {
        Cooldowns.createCooldown("report_cooldown");
        Cooldowns.createCooldown("TOURNAMENT_COOLDOWN");
        Cooldowns.createCooldown("helpop_cooldown");
        Cooldowns.createCooldown("fastpearl");
        Cooldowns.createCooldown("Ghost_item_cooldown");
        (Vapor.instance = this).registerConfiguration();
        CustomEntityRegistration.registerCustomEntities();
        ChatUtil.sendMessage((CommandSender)Bukkit.getConsoleSender(), "&9[Vapor] &7Enabling Vapor...");
        this.visualiseHandler = new VisualiseHandler();
        Giveaway.INSTANCE.enable();
        try {
            final BufferedWriter out = new BufferedWriter(new FileWriter("./logs/latest.log"));
            out.write("");
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ProtocolLibHook.hook(this);
        this.registerCommands();
        this.setupPermissions();
        this.setupChat();
        this.registerListeners();
        this.holograms = new Config(this, "holograms");
        if (getInstance().getConfig().getBoolean("HOLOGRAMS")) {
            this.executeMonititoredTask("Holograms Registration", this::registerHolograms);
        }
        this.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        Vapor.SchedulesData = new File(this.getDataFolder(), "event-schedules.txt");
        this.commandExecutor = new CommandHandler(this);
        this.limitersFile = new LimitersFile();
        this.utilitiesFile = new UtilitiesFile();
        this.configFile = new ConfigFile();
        this.tab = new ConfigurationFile(this, "tab.yml");
        this.waypoints = new ConfigurationFile(this, "waypoints.yml");
        this.prefixFile = new PrefixFile();
        this.scoreboardFile = new ScoreboardFile();
        this.messageConfig = new MessageConfig();
        this.configHandler = new ConfigHandler(this);
        this.configuration = new Configuration(this);
        Vault.hook();
        this.playerDataHandler = new PlayerDataHandler(this);
        if (this.getConfig().getBoolean("TOURNAMENT.ENABLED")) {
            this.tournamentSignListener = new TournamentSignListener(this);
        }
        this.kitManager = new FlatFileKitManager(this);
        this.appleHandler = new AppleHandler(this);
        this.enderpearlHandler = new EnderpearlHandler(this);
        this.eventSignHandler = new EventSignHandler(this);
        this.gappleHandler = new GappleHandler(this);
        this.sotwHandler = new SOTWHandler(this);
        this.horseNMS = new HorseNMS(this);
        this.rebootHandler = new RebootHandler(this);
        this.mobStackHandler = new MobStackHandler(this);
        this.claimHandler = new ClaimHandler(this);
        this.archerUpgradesHandler = new ArcherUpgradesHandler(this);
        this.factionHandler = new FactionHandler(this);
        this.staffModeHandler = new StaffModeHandler(this);
        this.brewingSpeedHandler = new BrewingSpeedHandler(this);
        this.armorfixHandler = new ArmorFixListener(this);
        this.blockhitHandler = new BlockHitFixListener(this);
        this.glowstoneMountainManager = new GlowstoneMountainManager(this);
        this.blockjumpHandler = new BlockJumpGlitchFixListener(this);
        this.boatglitchHandler = new BoatGlitchFixListener(this);
        this.coloncommandHandler = new ColonCommandFixListener(this);
        this.creativeclickHandler = new CreativeClickListener(this);
        this.ilegalenchantHandler = new IlegalEnchantListener(this);
        this.infinityarrowHandler = new InfinityArrowFixListener(this);
        this.keyManager = new KeyManager(this);
        this.nopotlagHandler = new NoPotLagListener(this);
        this.mobfarmHandler = new MobFarmListener(this);
        this.phaseglitchHandler = new PhaseGlitchListener(this);
        this.factionManager = new FlatFileFactionManager(this);
        this.antispamHandler = new AntiSpam(this);
        this.chatHandler = new ChatHandler(this);
        this.deathMessageHandler = new DeathMessageHandler(this);
        this.blockPickupHandler = new BlockPickupHandler(this);
        if (!this.configuration.isKitMap()) {
            this.deathSignHandler = new DeathSignHandler(this);
        }
        this.minecartelevatorHandler = new MinecartElevatorHandler(this);
        this.crowbarHandler = new CrowbarHandler(this);
        this.rallyHandler = new RallyHandler(this);
        this.endPortalHandler = new EndPortalHandler(this);
        this.portalHandler = new PortalHandler(this);
        this.furnaceSpeedHandler = new FurnaceSpeedHandler(this);
        this.protectionHandler = new ProtectionHandler(this);
        this.shopSignHandler = new ShopSignHandler(this);
        this.signSubclaimHandler = new SignSubclaimHandler(this);
        this.oreMountainManager = new OreMountainManager(this);
        this.dynamicPlayerHandler = new DynamicPlayerHandler(this);
        this.skullHandler = new SkullHandler(this);
        this.eotwHandler = new EOTWHandler(this);
        this.claimWandHandler = new ClaimWandHandler(this);
        this.borderHandler = new BorderHandler(this);
        if (this.configuration.isKitMap()) {
            this.kitMapHandler = new KitMapHandler(this);
        }
        this.signHandler = new SignHandler(this);
        this.foundDiamondsHandler = new FoundDiamondsHandler(this);
        this.mapKitHandler = new MapKitHandler(this);
        this.chatControlHandler = new ChatControlHandler(this);
        this.itemDB = new SimpleItemDB(this);
        this.potionLimitHandler = new PotionLimitHandler(this);
        this.wlHandler = new WhitelistHandler(this);
        this.freezeHandler = new FreezeHandler(this);
        this.expHandler = new ExpHandler(this);
        this.enchantmentLimiterHandler = new EnchantmentLimiterHandler(this);
        this.effectRestorer = new EffectRestorer(this);
        this.armorClassManager = new ArmorClassManager(this);
        this.customTimerManager = new CustomTimerManager();
        this.timerManager = new TimerManager(this);
        this.eventScheduler = new EventScheduler(this);
        this.purgeHandler = new PurgeHandler(this);
        this.eotwUtils = new EOTWUtilsHandler(this);
        this.worldEditCrashFixHandler = new WorldEditCrashFixHandler(this);
        this.backHandler = new BackHandler(this);
        this.beaconRenamerHandler = new BeaconRenamerHandler(this);
        this.godHandler = new GodHandler(this);
        this.hcfHandler = new HCFHandler(this);
        this.warpHandler = new WarpHandler(this);
        this.chatCallbackManager = new ChatCallbackManager(this);
        this.hiderManager = new HiderManager(this);
        (this.reviveHandler = new ReviveHandler()).setupRevives();
        (this.reclaimHandler = new ReclaimHandler()).setupReclaims();
        PartnerPackageHandler.loadPackages();
        this.keyManager.saveKeyData();
        this.setupReclaims();
        this.other();
        this.directories();
        if (getInstance().getConfig().getBoolean("TABLIST")) {
            TabHandler.setLayoutProvider(new TabProvider());
            TabHandler.init(this);
            ProtocolLibrary.getProtocolManager().addPacketListener((PacketListener)new TabAdapter(this));
        }
        ScoreboardManager.init();
        ScoreboardManager.setConfiguration(HCFScoreboardConfiguration.create());
        NametagManager.init();
        NametagManager.registerProvider(new HCFNametagProvider());
        new BukkitRunnable() {
            public void run() {
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), Vapor.getInstance().getConfigFile().getString("STARTUP-COMMAND"));
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "difficulty peaceful");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "gamerule doMobSpawning false");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "gamerule commandBlockOutput false");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "gamerule logAdminCommands false");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "gamerule mobGriefing false");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "gamerule doDaylightCycle false");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "gamerule sendCommandFeedback false");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "difficulty normal");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "time set day");
                if (Vapor.getInstance().getConfig().getBoolean("DELAY-CHAT-ON-START")) {
                    Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), "chat delay " + Vapor.getInstance().getConfig().getInt("DELAY-CHAT-ON-START-TIME"));
                }
            }
        }.runTaskLater((Plugin)this, 50L);
        new BukkitRunnable() {
            public void run() {
                final String players = BukkitUtils.getOnlinePlayers().stream().filter(player -> player.hasPermission("hcf.donator") && !player.isOp() && !player.hasPermission("*")).map((Function<? super Player, ?>)HumanEntity::getName).collect((Collector<? super Object, ?, String>)Collectors.joining(", "));
                for (final String onlinedonator : Vapor.getInstance().getConfig().getStringList("ONLINE-DONATOR.BROADCAST")) {
                    Vapor.this.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', onlinedonator).replace("%store%", Vapor.getInstance().getConfig().getString("ONLINE-DONATOR.STORE")).replace("%player%", players).toString().replace("%right-arrow%", SymbolUtil.UNICODE_ARROWS_RIGHT));
                }
            }
        }.runTaskTimer((Plugin)this, 60L, (long)(1200 * getInstance().getConfig().getInt("ONLINE-DONATOR.INTERVAL")));
        if (this.getConfig().getBoolean("CLEAR.ENABLED")) {
            Bukkit.getScheduler().scheduleAsyncRepeatingTask((Plugin)this, () -> {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "killall all");
                this.saveData();
                this.oreMountainManager.save();
                this.glowstoneMountainManager.save();
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getInstance().getConfig().getString("CLEAR.MESSAGE").replace("%right-arrow%", SymbolUtil.UNICODE_ARROWS_RIGHT)));
                return;
            }, 60L, (long)(1200 * getInstance().getConfig().getInt("CLEAR.INTERVAL")));
        }
        if (this.getMessageConfig().getConfiguration().getBoolean("MESSAGES.SAVE.ENABLED")) {
            Bukkit.getScheduler().scheduleAsyncRepeatingTask((Plugin)this, () -> {
                Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', getInstance().getMessageConfig().getConfiguration().getString("MESSAGES.SAVE.SAVING").replace("%right-arrow%", SymbolUtil.UNICODE_ARROWS_RIGHT))));
                Bukkit.getWorlds().forEach(world -> {
                    world.setThundering(false);
                    world.setStorm(false);
                    return;
                });
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "save-all");
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getInstance().getMessageConfig().getConfiguration().getString("MESSAGES.SAVE.SUCCESSFULLY").replace("%right-arrow%", SymbolUtil.UNICODE_ARROWS_RIGHT)));
            }, 3600L, 18000L);
        }
    }
    
    public Location getKillsLocation() {
        return new Location(Bukkit.getWorld("world"), (double)getInstance().getHolograms().getInt("LOCATIONS.KILLS.X"), (double)getInstance().getHolograms().getInt("LOCATIONS.KILLS.Y"), (double)getInstance().getHolograms().getInt("LOCATIONS.KILLS.Z"));
    }
    
    public void setKillsLocation(final Location location) {
        this.holograms.save();
    }
    
    public Location getPointsLocation() {
        return new Location(Bukkit.getWorld("world"), (double)getInstance().getHolograms().getInt("LOCATIONS.POINTS.X"), (double)getInstance().getHolograms().getInt("LOCATIONS.POINTS.Y"), (double)getInstance().getHolograms().getInt("LOCATIONS.POINTS.Z"));
    }
    
    public void setPointsLocation(final Location location) {
        this.pointsLocation = new PersistableLocation(location);
        this.holograms.save();
    }
    
    public Location getDeathsLocation() {
        return new Location(Bukkit.getWorld("world"), (double)getInstance().getHolograms().getInt("LOCATIONS.DEATHS.X"), (double)getInstance().getHolograms().getInt("LOCATIONS.DEATHS.Y"), (double)getInstance().getHolograms().getInt("LOCATIONS.DEATHS.Z"));
    }
    
    public void setDeathsLocation(final Location location) {
        this.deathsLocation = new PersistableLocation(location);
        this.holograms.save();
    }
    
    private void registerListeners() {
        final PluginManager manager = this.getServer().getPluginManager();
        ChatUtil.sendMessage((CommandSender)Bukkit.getConsoleSender(), "&9[Vapor] &7Loading Listeners...");
        if (this.getConfig().getBoolean("ENDERPEARL-GLITCH")) {
            manager.registerEvents((Listener)new PearlGlitchHandler(this), (Plugin)this);
        }
        if (getInstance().getConfig().getBoolean("DROP-ITEMS-SPAWN")) {
            manager.registerEvents((Listener)new DropItemsHandler(), (Plugin)this);
        }
        manager.registerEvents((Listener)new GiveawayHandler(), (Plugin)this);
        manager.registerEvents((Listener)new KitListener(this), (Plugin)this);
        manager.registerEvents((Listener)new ArcherUpgradesHandler(this), (Plugin)this);
        manager.registerEvents((Listener)new PlayerInteractHandler(), (Plugin)this);
        manager.registerEvents((Listener)new MobFixesHandler(), (Plugin)this);
        manager.registerEvents((Listener)new JoinHandler(this), (Plugin)this);
        manager.registerEvents((Listener)new RefillSignHandler(this), (Plugin)this);
        manager.registerEvents((Listener)new OverrideHandler(), (Plugin)this);
        if (getInstance().getConfig().getBoolean("ELEVATOR-SIGN.ENABLED")) {
            manager.registerEvents((Listener)new SignElevatorHandler(), (Plugin)this);
        }
        manager.registerEvents((Listener)new DeathHandler(this), (Plugin)this);
        manager.registerEvents((Listener)new ItemStatTrackingHandler(), (Plugin)this);
        manager.registerEvents((Listener)new DupeGlitchFix(), (Plugin)this);
        manager.registerEvents((Listener)new HiderHandler(this), (Plugin)this);
        manager.registerEvents((Listener)new DeathMessageHandler(this), (Plugin)this);
        manager.registerEvents((Listener)new PortalTrapFixListener(), (Plugin)this);
        manager.registerEvents((Listener)new StaffChatHandler(), (Plugin)this);
        manager.registerEvents((Listener)new MenuHandler(this), (Plugin)this);
        manager.registerEvents((Listener)new LFFHandler(), (Plugin)this);
        manager.registerEvents((Listener)new KingHandler(), (Plugin)this);
        manager.registerEvents((Listener)new WallBorderHandler(this), (Plugin)this);
        manager.registerEvents((Listener)new DTCListener(), (Plugin)this);
        manager.registerEvents((Listener)(this.combatLogListener = new CombatLogListener(this)), (Plugin)this);
        if (this.getConfig().getBoolean("TOURNAMENT.ENABLED")) {
            manager.registerEvents((Listener)new TournamentListener(), (Plugin)this);
            manager.registerEvents((Listener)new TournamentSignListener(this), (Plugin)this);
        }
        if (this.getConfig().getBoolean("KITMAP")) {
            manager.registerEvents((Listener)new KillStreakManager(), (Plugin)this);
        }
        if (!this.getConfig().getBoolean("KITMAP")) {
            manager.registerEvents((Listener)new DeathbanListener(this), (Plugin)this);
        }
        manager.registerEvents((Listener)new GrapplingHookHandler(), (Plugin)this);
        ChatUtil.sendMessage((CommandSender)Bukkit.getConsoleSender(), "&9[Vapor] &7Loaded Listeners.");
    }
    
    public void onDisable() {
        ChatUtil.sendMessage((CommandSender)Bukkit.getConsoleSender(), "&9[Vapor] &7Disabling Vapor...");
        Bukkit.getServer().savePlayers();
        CombatLogListener.removeCombatLoggers();
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "save-all");
        this.kitManager.saveKitData();
        this.saveData();
    }
    
    public void saveData() {
        this.deathbanManager.saveDeathbanData();
        this.userManager.saveUserData();
        this.factionManager.saveFactionData();
        this.signHandler.cancelTasks(null);
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player players = onlinePlayers[i];
            players.saveData();
        }
        Bukkit.getServer().savePlayers();
        Bukkit.savePlayers();
    }
    
    private void registerHolograms() {
        new BukkitRunnable() {
            public void run() {
                Vapor.this.hologramsManager.updateKillsHologram();
                Vapor.this.hologramsManager.updateDeathsHologram();
                Vapor.this.hologramsManager.updatePointsHologram();
            }
        }.runTaskTimer((Plugin)this, 1000L, 1000L);
    }
    
    private void registerCommands(final Object... command) {
        final CommandFramework commandFramework = new CommandFramework((Plugin)this);
        Arrays.stream(command).forEach(commandFramework::registerCommands);
    }
    
    public void registerCommands() {
        if (getInstance().getConfig().getBoolean("STAFFMODE.ENABLED")) {
            this.registerCommands(new StaffModeCommand());
        }
    }
    
    public void other() {
        this.getCommand("enchant").setExecutor((CommandExecutor)new EnchantCommand());
        this.getCommand("poweritems").setExecutor((CommandExecutor)new PowerItemCommand());
        this.getCommand("managedtr").setExecutor((CommandExecutor)new FactionManageDtrCommand(this));
        this.getCommand("manageregen").setExecutor((CommandExecutor)new FactionManageDtrRegenCommand(this));
        this.getCommand("timer").setExecutor((CommandExecutor)new TimerExecutor(this));
        this.getCommand("package").setExecutor((CommandExecutor)new PackageCommand());
        this.getCommand("condense").setExecutor((CommandExecutor)new CondenseCommand(this));
        this.getCommand("focus").setExecutor((CommandExecutor)new FocusCommand(this));
        this.getCommand("corehelp").setExecutor((CommandExecutor)new HelpCommand());
        this.getCommand("message").setExecutor((CommandExecutor)new MessageCommand(this));
        this.getCommand("supplydrop").setExecutor((CommandExecutor)new SupplydropCommand(this));
        this.getCommand("lff").setExecutor((CommandExecutor)new LFFCommand());
        this.getCommand("oremountain").setExecutor((CommandExecutor)new OreMountainCommand(this));
        this.getCommand("customtimer").setExecutor((CommandExecutor)new CustomTimerCommand(this));
        this.getCommand("event").setExecutor((CommandExecutor)new EventExecutor(this));
        this.getCommand("vanish").setExecutor((CommandExecutor)new VanishCommand());
        this.getCommand("conquest").setExecutor((CommandExecutor)new ConquestExecutor(this));
        this.getCommand("koth").setExecutor((CommandExecutor)new KothExecutor(this));
        this.getCommand("endplayers").setExecutor((CommandExecutor)new EndPlayersCommand(this));
        this.getCommand("netherplayers").setExecutor((CommandExecutor)new NetherPlayersCommand(this));
        this.getCommand("staffchat").setExecutor((CommandExecutor)new StaffChatCommand());
        this.getCommand("lives").setExecutor((CommandExecutor)new LivesExecutor(this));
        if (this.getConfig().getBoolean("TOURNAMENT.ENABLED")) {
            this.getCommand("tournament").setExecutor((CommandExecutor)new TournamentExecutor(this));
        }
        this.getCommand("kit").setExecutor((CommandExecutor)new KitExecutor(this));
        this.getCommand("death").setExecutor((CommandExecutor)new DeathExecutor(this));
        this.getCommand("vapor").setExecutor((CommandExecutor)new VaporExecutor());
        this.getCommand("giveaway").setExecutor((CommandExecutor)new GiveawayExecutor());
        this.getCommand("dtc").setExecutor((CommandExecutor)new DTCCommand());
        this.getCommand("ktk").setExecutor((CommandExecutor)new KingCommand(this));
        this.getCommand("helpop").setExecutor((CommandExecutor)new HelpOpCommand());
        this.getCommand("perk").setExecutor((CommandExecutor)new PerkExecutor(this));
        this.getCommand("report").setExecutor((CommandExecutor)new ReportCommand());
        if (this.getConfig().getBoolean("GLOWSTONE.ENABLED")) {
            this.getCommand("glowstonemountain").setExecutor((CommandExecutor)new GlowstoneMountainCommand(this));
        }
        this.getCommand("faction").setExecutor((CommandExecutor)new FactionExecutor(this));
        this.phaseglitchHandler.enable();
        this.appleHandler.enable();
        this.rallyHandler.enable();
        this.playerDataHandler.enable();
        this.freezeHandler.enable();
        this.sotwHandler.enable();
        if (!this.configuration.isKitMap()) {
            this.deathSignHandler.enable();
        }
        this.gappleHandler.enable();
        if (this.configuration.isKitMap()) {
            this.kitMapHandler.enable();
        }
        this.eventSignHandler.enable();
        this.rebootHandler.enable();
        this.blockPickupHandler.enable();
        this.chatControlHandler.enable();
        this.deathbanManager = new FlatFileDeathbanManager(this);
        this.armorfixHandler = new ArmorFixListener(this);
        this.blockhitHandler = new BlockHitFixListener(this);
        this.deathMessageHandler = new DeathMessageHandler(this);
        this.blockjumpHandler = new BlockJumpGlitchFixListener(this);
        this.boatglitchHandler = new BoatGlitchFixListener(this);
        this.coloncommandHandler = new ColonCommandFixListener(this);
        this.creativeclickHandler = new CreativeClickListener(this);
        this.hitdetectionHandler = new HitDetectionListener(this);
        this.ilegalenchantHandler = new IlegalEnchantListener(this);
        this.infinityarrowHandler = new InfinityArrowFixListener(this);
        this.nopotlagHandler = new NoPotLagListener(this);
        this.mobfarmHandler = new MobFarmListener(this);
        this.phaseglitchHandler = new PhaseGlitchListener(this);
        if (getInstance().getConfig().getBoolean("HOLOGRAMS")) {
            this.hologramsManager = new HologramsManager(this);
        }
        this.userManager = new UserManager(this);
        if (this.getConfig().getBoolean("TOURNAMENT.ENABLED")) {
            this.tournamentManager = new TournamentManager();
        }
        this.mobStackHandler.enable();
        this.foundDiamondsHandler.enable();
        this.enchantmentLimiterHandler.enable();
        this.potionLimitHandler.enable();
        this.mapKitHandler.enable();
        this.dynamicPlayerHandler.enable();
        this.worldEditCrashFixHandler.enable();
        this.godHandler.enable();
        this.hcfHandler.enable();
        this.purgeHandler.enable();
        this.backHandler.enable();
        this.warpHandler.enable();
        this.hrecipe();
        this.precipe();
        this.lrecipe();
        this.brecipe();
        this.mrecipe();
        this.gprecipe();
        this.worldEdit = (WorldEditPlugin)this.getServer().getPluginManager().getPlugin("WorldEdit");
    }
    
    private void hrecipe() {
        final ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        final ItemMeta meta = helmet.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Chain Helmet");
        helmet.setItemMeta(meta);
        final ShapedRecipe hrecipe = new ShapedRecipe(helmet);
        hrecipe.shape(new String[] { "@@@", "@ @" });
        hrecipe.setIngredient('@', Material.IRON_FENCE);
        Bukkit.getServer().addRecipe((Recipe)hrecipe);
    }
    
    private void precipe() {
        final ItemStack plate = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
        final ItemMeta meta2 = plate.getItemMeta();
        meta2.setDisplayName(ChatColor.WHITE + "Chain Chestplate");
        plate.setItemMeta(meta2);
        final ShapedRecipe precipe = new ShapedRecipe(plate);
        precipe.shape(new String[] { "@ @", "@@@", "@@@" });
        precipe.setIngredient('@', Material.IRON_FENCE);
        Bukkit.getServer().addRecipe((Recipe)precipe);
    }
    
    private void lrecipe() {
        final ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        final ItemMeta meta3 = leggings.getItemMeta();
        meta3.setDisplayName(ChatColor.WHITE + "Chain Leggings");
        leggings.setItemMeta(meta3);
        final ShapedRecipe lrecipe = new ShapedRecipe(leggings);
        lrecipe.shape(new String[] { "@@@", "@ @", "@ @" });
        lrecipe.setIngredient('@', Material.IRON_FENCE);
        Bukkit.getServer().addRecipe((Recipe)lrecipe);
    }
    
    private void brecipe() {
        final ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        final ItemMeta meta4 = boots.getItemMeta();
        meta4.setDisplayName(ChatColor.WHITE + "Chain Leggings");
        boots.setItemMeta(meta4);
        final ShapedRecipe lrecipe = new ShapedRecipe(boots);
        lrecipe.shape(new String[] { "   ", "@ @", "@ @" });
        lrecipe.setIngredient('@', Material.IRON_FENCE);
        Bukkit.getServer().addRecipe((Recipe)lrecipe);
    }
    
    private void mrecipe() {
        final ItemStack helmet = new ItemStack(Material.SPECKLED_MELON, 1);
        final ShapedRecipe mrecipe = new ShapedRecipe(helmet);
        mrecipe.shape(new String[] { "@@@", "@ @" });
        mrecipe.setIngredient('@', Material.SPECKLED_MELON);
        Bukkit.getServer().addRecipe((Recipe)mrecipe);
    }
    
    private void gprecipe() {
        final ItemStack plate = new ItemStack(Material.SPECKLED_MELON, 1);
        final ShapelessRecipe gprecipe = new ShapelessRecipe(plate);
        gprecipe.addIngredient(1, Material.MELON);
        gprecipe.addIngredient(1, Material.GOLD_NUGGET);
        Bukkit.getServer().addRecipe((Recipe)gprecipe);
    }
    
    public void disable() {
        this.phaseglitchHandler.disable();
        this.appleHandler.disable();
        this.gappleHandler.disable();
        this.staffModeHandler.disable();
        this.playerDataHandler.disable();
        this.dynamicPlayerHandler.disable();
        this.freezeHandler.disable();
        if (!this.configuration.isKitMap()) {
            this.deathSignHandler.disable();
        }
        if (this.configuration.isKitMap()) {
            this.kitMapHandler.disable();
        }
        this.getTimerManager().getClassWarmupHandler().disable();
        this.configuration.isKitMap();
        this.rallyHandler.disable();
        this.eventSignHandler.disable();
        this.chatControlHandler.disable();
        this.mobStackHandler.disable();
        this.foundDiamondsHandler.disable();
        this.potionLimitHandler.disable();
        this.blockPickupHandler.disable();
        this.enchantmentLimiterHandler.disable();
        this.mapKitHandler.disable();
        this.armorClassManager.onDisable();
        this.worldEditCrashFixHandler.disable();
        this.backHandler.disable();
        this.godHandler.disable();
        this.hcfHandler.disable();
        this.warpHandler.disable();
    }
    
    public void directories() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        final File localFile1 = new File(this.getDataFolder(), "playerdata");
        if (!localFile1.exists()) {
            localFile1.mkdir();
        }
        final File staff = new File(this.getDataFolder(), "staff");
        if (!staff.exists()) {
            staff.mkdir();
        }
        final File staffCommand = new File(staff, "command");
        if (!staffCommand.exists()) {
            staffCommand.mkdir();
        }
        final File staffBlock = new File(staff, "block");
        if (!staffBlock.exists()) {
            staffBlock.mkdir();
        }
        final File staffDrop = new File(staff, "drop");
        if (!staffDrop.exists()) {
            staffDrop.mkdir();
        }
        final File staffChat = new File(staff, "chat");
        if (!staffChat.exists()) {
            staffChat.mkdir();
        }
        final File staffJL = new File(staff, "joinleave");
        if (!staffJL.exists()) {
            staffJL.mkdir();
        }
    }
    
    public void registerConfiguration() {
        ConfigurationSerialization.registerClass((Class)PersistableLocation.class);
        ConfigurationSerialization.registerClass((Class)Kit.class);
        ConfigurationSerialization.registerClass((Class)Cuboid.class);
        ConfigurationSerialization.registerClass((Class)NamedCuboid.class);
        ConfigurationSerialization.registerClass((Class)CaptureZone.class);
        ConfigurationSerialization.registerClass((Class)Deathban.class);
        ConfigurationSerialization.registerClass((Class)ClaimZone.class);
        ConfigurationSerialization.registerClass((Class)SubclaimZone.class);
        ConfigurationSerialization.registerClass((Class)Deathban.class);
        ConfigurationSerialization.registerClass((Class)ClaimableFaction.class);
        ConfigurationSerialization.registerClass((Class)FactionUser.class);
        ConfigurationSerialization.registerClass((Class)ConquestFaction.class);
        ConfigurationSerialization.registerClass((Class)CapturableFaction.class);
        ConfigurationSerialization.registerClass((Class)KothFaction.class);
        ConfigurationSerialization.registerClass((Class)CitadelFaction.class);
        ConfigurationSerialization.registerClass((Class)Faction.class);
        ConfigurationSerialization.registerClass((Class)FactionMember.class);
        ConfigurationSerialization.registerClass((Class)PlayerFaction.class);
        ConfigurationSerialization.registerClass((Class)RoadFaction.class);
        ConfigurationSerialization.registerClass((Class)SpawnFaction.class);
        ConfigurationSerialization.registerClass((Class)EndPortalFaction.class);
        ConfigurationSerialization.registerClass((Class)RoadFaction.NorthRoadFaction.class);
        ConfigurationSerialization.registerClass((Class)RoadFaction.EastRoadFaction.class);
        ConfigurationSerialization.registerClass((Class)RoadFaction.SouthRoadFaction.class);
        ConfigurationSerialization.registerClass((Class)RoadFaction.WestRoadFaction.class);
        ConfigurationSerialization.registerClass((Class)TournamentFaction.class);
        ConfigurationSerialization.registerClass((Class)DTCFaction.class);
        ConfigurationSerialization.registerClass((Class)MountainFaction.class);
        ConfigurationSerialization.registerClass((Class)OreFaction.class);
        ConfigurationSerialization.registerClass((Class)GlowstoneFaction.class);
    }
    
    public void sendToServer(final Player player, final String server) {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage((Plugin)this, "BungeeCord", b.toByteArray());
    }
    
    private void executeMonititoredTask(final String name, final Runnable runnable) {
        final long start = System.nanoTime();
        runnable.run();
        final long end = System.nanoTime();
        final long diff = end - start;
        if (diff > 100000000L) {
            final double millis = diff / 1000000.0;
            final String output = DateTimeFormats.REMAINING_SECONDS.get().format(millis);
            this.getLogger().info("Running [" + name + "] took " + output + "ms");
        }
    }
    
    public DeathbanManager getDeathbanManager() {
        return this.deathbanManager;
    }
    
    public ArmorFixListener getArmorFixListener() {
        return this.armorfixHandler;
    }
    
    public BlockHitFixListener getBlockHitFixListener() {
        return this.blockhitHandler;
    }
    
    public BlockJumpGlitchFixListener getBlockJumpGlitchFixListener() {
        return this.blockjumpHandler;
    }
    
    public BoatGlitchFixListener getBoatGlitchFixListener() {
        return this.boatglitchHandler;
    }
    
    public UserManager getUserManager() {
        return this.userManager;
    }
    
    public ColonCommandFixListener getColonCommandFixListener() {
        return this.coloncommandHandler;
    }
    
    public CombatLogListener getCombatLogListener() {
        return this.combatLogListener;
    }
    
    public CreativeClickListener getCreativeClickListener() {
        return this.creativeclickHandler;
    }
    
    public HitDetectionListener getHitDetectionListener() {
        return this.hitdetectionHandler;
    }
    
    public IlegalEnchantListener getIlegalEnchantListener() {
        return this.ilegalenchantHandler;
    }
    
    public SwitcherHandler getSwticherHandler() {
        return this.switcherHandler;
    }
    
    public RottenEggHandler getRottenEggHandler() {
        return this.rottenEggHandler;
    }
    
    public InfinityArrowFixListener getInfinityArrowFixListener() {
        return this.infinityarrowHandler;
    }
    
    public PortalTrapFixListener getportaltrapfixlistener() {
        return this.portaltrapHandler;
    }
    
    public static Vapor getInstance() {
        return Vapor.instance;
    }
    
    public static String getReaming(final long millis) {
        return getRemaining(millis, true, true);
    }
    
    public static String getRemaining(final long millis, final boolean milliseconds) {
        return getRemaining(millis, milliseconds, true);
    }
    
    public static String getRemaining(final long duration, final boolean milliseconds, final boolean trail) {
        if (milliseconds && duration < Vapor.MINUTE) {
            return String.valueOf((trail ? DateTimeFormats.REMAINING_SECONDS_TRAILING : DateTimeFormats.REMAINING_SECONDS).get().format(duration * 0.001)) + 's';
        }
        return DurationFormatUtils.formatDuration(duration, String.valueOf((duration >= Vapor.HOUR) ? "HH:" : "") + "mm:ss");
    }
    
    public SignElevatorHandler getSignElevatorHandler() {
        return this.signElevatorHandler;
    }
    
    public MinecartElevatorHandler getMinecartElevatorHandler() {
        return this.minecartelevatorHandler;
    }
    
    public ClaimWandHandler getClaimWandHandler() {
        return this.claimWandHandler;
    }
    
    public SignSubclaimHandler getSignSubclaimHandler() {
        return this.signSubclaimHandler;
    }
    
    public EnchantmentLimiterHandler getEnchantmentLimiterHandler() {
        return this.enchantmentLimiterHandler;
    }
    
    public PotionLimitHandler getPotionLimitHandler() {
        return this.potionLimitHandler;
    }
    
    public BorderHandler getBorderHandler() {
        return this.borderHandler;
    }
    
    public ChatHandler getChatHandler() {
        return this.chatHandler;
    }
    
    public ClaimHandler getClaimHandler() {
        return this.claimHandler;
    }
    
    public CrowbarHandler getCrowbarHandler() {
        return this.crowbarHandler;
    }
    
    public GrapplingHookHandler getGrapplingHookHandler() {
        return this.grapplingHookHandler;
    }
    
    public DynamicPlayerHandler getDynamicPlayerHandler() {
        return this.dynamicPlayerHandler;
    }
    
    public DeathMessageHandler getDeathMessageHandler() {
        return this.deathMessageHandler;
    }
    
    public DeathSignHandler getDeathSignHandler() {
        return this.deathSignHandler;
    }
    
    public EOTWHandler getEOTWHandler() {
        return this.eotwHandler;
    }
    
    public ShieldHandler getShieldHandler() {
        return this.shieldHandler;
    }
    
    public EventSignHandler getEventSignHandler() {
        return this.eventSignHandler;
    }
    
    public ExpHandler getExpHandler() {
        return this.expHandler;
    }
    
    public FoundDiamondsHandler getFoundDiamondsHandler() {
        return this.foundDiamondsHandler;
    }
    
    public FreezeHandler getFreezeHandler() {
        return this.freezeHandler;
    }
    
    public PlayerFaction getPlayerFaction() {
        return this.playerfaction;
    }
    
    public ItemStatTrackingHandler getItemStatTrackingHandler() {
        return this.itemStatTrackingHandler;
    }
    
    public RallyHandler getRallyHandler() {
        return this.rallyHandler;
    }
    
    public KitMapHandler getKitMapHandler() {
        return this.kitMapHandler;
    }
    
    public MapKitHandler getMapKitHandler() {
        return this.mapKitHandler;
    }
    
    public PortalHandler getPortalHandler() {
        return this.portalHandler;
    }
    
    public ShopSignHandler getShopSignHandler() {
        return this.shopSignHandler;
    }
    
    public SignHandler getSignHandler() {
        return this.signHandler;
    }
    
    public FactionHandler getFationHandler() {
        return this.factionHandler;
    }
    
    public ProtectionHandler getProtectionHandler() {
        return this.protectionHandler;
    }
    
    public StaffModeHandler getStaffModeHandler() {
        return this.staffModeHandler;
    }
    
    public VanishCommand getVanishCommand() {
        return this.vanishCommand;
    }
    
    public FactionManager getFactionManager() {
        return this.factionManager;
    }
    
    public ArmorClassManager getArmorClassManager() {
        return this.armorClassManager;
    }
    
    public TimerManager getTimerManager() {
        return this.timerManager;
    }
    
    public EffectRestorer getEffectRestorer() {
        return this.effectRestorer;
    }
    
    public StaffChatHandler getStaffChatHandler() {
        return this.staffChatHandler;
    }
    
    public EOTWUtilsHandler getEOTWUtils() {
        return this.eotwUtils;
    }
    
    public ClassWarmupHandler getClassLoadTimer() {
        return this.classLoadTimer;
    }
    
    public ItemDB getItemDB() {
        return this.itemDB;
    }
    
    public PlayerData getData() {
        return this.playerData;
    }
    
    public ConfigFile getConfigFile() {
        return this.configFile;
    }
    
    public PrefixFile getPrefixFile() {
        return this.prefixFile;
    }
    
    public ScoreboardFile getScoreboardFile() {
        return this.scoreboardFile;
    }
    
    public LimitersFile getLimitersFile() {
        return this.limitersFile;
    }
    
    public UtilitiesFile getUtilitiesFile() {
        return this.utilitiesFile;
    }
    
    public ConfigHandler getConfigHandler() {
        return this.configHandler;
    }
    
    public PlayerDataHandler getPlayerData() {
        return this.playerDataHandler;
    }
    
    public MobStackHandler getMobStackHandler() {
        return this.mobStackHandler;
    }
    
    public BackHandler getBackHandler() {
        return this.backHandler;
    }
    
    public GodHandler getGodHandler() {
        return this.godHandler;
    }
    
    public WarpHandler getWarpHandler() {
        return this.warpHandler;
    }
    
    public Permission getPermissions() {
        return this.permissions;
    }
    
    public ChatControlHandler getChatControlHandler() {
        return this.chatControlHandler;
    }
    
    public BlockPickupHandler getBlockPickupHandler() {
        return this.blockPickupHandler;
    }
    
    public AppleHandler getAppleHandler() {
        return this.appleHandler;
    }
    
    public EnderpearlHandler getEnderpearlHandler() {
        return this.enderpearlHandler;
    }
    
    public GappleHandler getGappleHandler() {
        return this.gappleHandler;
    }
    
    public SOTWHandler getSotwHandler() {
        return this.sotwHandler;
    }
    
    public RebootHandler getRebootHandler() {
        return this.rebootHandler;
    }
    
    public PvPTimerHandler getPvpTimerHandler() {
        return this.pvpTimerHandler;
    }
    
    public Configuration getConfiguration() {
        return this.configuration;
    }
    
    public Archer getArcher() {
        return this.archer;
    }
    
    public Bomber getBomber() {
        return this.bomber;
    }
    
    public Ghost getGhost() {
        return this.ghost;
    }
    
    public Bard getBard() {
        return this.bard;
    }
    
    public Miner getMiner() {
        return this.minerData;
    }
    
    public Rogue getRogue() {
        return this.rogue;
    }
    
    public CommandHandler getCommandHandlerE() {
        return this.commandExecutor;
    }
    
    public VisualiseHandler getVisualiseHandler() {
        return this.visualiseHandler;
    }
    
    public FurnaceSpeedHandler getFurnaceSpeedHandler() {
        return this.furnaceSpeedHandler;
    }
    
    public EndPortalHandler getEndPortalHandler() {
        return this.endPortalHandler;
    }
    
    public HorseNMS getHorseNMS() {
        return this.horseNMS;
    }
    
    public BrewingSpeedHandler getBrewingSpeedHandler() {
        return this.brewingSpeedHandler;
    }
    
    public DeathHandler getDeathListener() {
        return this.deathListener;
    }
    
    public MessageConfig getMessageConfig() {
        return this.messageConfig;
    }
    
    public KitManager getKitManager() {
        return this.kitManager;
    }
    
    public KitExecutor getKitExecutor() {
        return this.kitExecutor;
    }
    
    public BeaconRenamerHandler getBeaconRenameHandler() {
        return this.beaconRenamerHandler;
    }
    
    public ReclaimHandler getReclaimHandler() {
        return this.reclaimHandler;
    }
    
    public ReviveHandler getReviveHandler() {
        return this.reviveHandler;
    }
    
    public CustomTimerManager getCustomTimerManager() {
        return this.customTimerManager;
    }
    
    public TournamentSignListener getTournamentSignHandler() {
        return this.tournamentSignListener;
    }
    
    public TournamentManager getTournamentManager() {
        return this.tournamentManager;
    }
    
    public SwitchStickHandler getSwitchstickHandler() {
        return this.switchStickHandler;
    }
    
    public ChatCallbackManager getChatCallbackManager() {
        return this.chatCallbackManager;
    }
    
    public KeyManager getKeyManager() {
        return this.keyManager;
    }
    
    public EventScheduler getEventScheduler() {
        return this.eventScheduler;
    }
    
    public FocusCommand getFocusCommand() {
        return this.focusCommand;
    }
    
    public OreMountainManager getOreMountainManager() {
        return this.oreMountainManager;
    }
    
    public HiderManager getHiderManager() {
        return this.hiderManager;
    }
    
    public Chat getChat() {
        return Vapor.chat;
    }
    
    public GlowstoneMountainManager getGlowstoneMountainManager() {
        return this.glowstoneMountainManager;
    }
    
    public void setTab(final ConfigurationFile tab) {
        this.tab = tab;
    }
    
    public ConfigurationFile getTab() {
        return this.tab;
    }
    
    public ConfigurationFile getWaypoints() {
        return this.waypoints;
    }
    
    public Config getHolograms() {
        return this.holograms;
    }
    
    public PurgeHandler getPurgeHandler() {
        return this.purgeHandler;
    }
    
    public ArcherUpgradesHandler getArcherUpgradesHandler() {
        return this.archerUpgradesHandler;
    }
    
    public ItemStack packageItem(final int amt) {
        return new ItemBuilder(Material.valueOf(this.getConfig().getString("PACKAGES-SETTINGS.MATERIAL")), Color.translate(this.getConfig().getString("PACKAGES-SETTINGS.NAME")), amt, new String[] { Color.translate(this.getConfig().getString("PACKAGES-SETTINGS.LORE")) }).getItem();
    }
    
    public PartnerPackageHandler getRandom() {
        if (PartnerPackageHandler.getPackages().size() == 1) {
            return PartnerPackageHandler.getPackages().get(0);
        }
        return PartnerPackageHandler.getPackages().get(new Random().nextInt(PartnerPackageHandler.getPackages().size()));
    }
    
    private void setupReclaims() {
        final String reclaim;
        final String string = reclaim = getInstance().getConfigFile().getConfiguration().getString("RECLAIMS.RECLAIM");
        final String s;
        switch (s = string) {
            case "MAP": {
                this.getCommand("reclaim").setExecutor((CommandExecutor)new ReclaimMapCommand(this));
                break;
            }
            case "DAILY": {
                this.getCommand("reclaim").setExecutor((CommandExecutor)new ReclaimDailyCommand(this));
                break;
            }
            default:
                break;
        }
    }
    
    public NametagInfo getNametagInfo() {
        return this.nametagInfo;
    }
    
    public WorldEditPlugin getWorldEdit() {
        return (WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit");
    }
    
    public DeathbanListener getDeathbanListener() {
        return this.deathbanListener;
    }
}
