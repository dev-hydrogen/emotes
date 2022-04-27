package exposed.hydrogen.emotes;

import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import exposed.hydrogen.emotes.commands.EmoteManagementCommand;
import exposed.hydrogen.emotes.emote.EmoteManager;
import exposed.hydrogen.emotes.json.JSONManager;
import lombok.Getter;
import net.kyori.adventure.key.Key;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class Emotes extends JavaPlugin {
    @Getter private static Emotes instance;
    @Getter private static EmoteManager emoteManager;
    @Getter private static PaperCommandManager<CommandSender> paperCommandManager;
    @Getter private static JSONManager jsonManager;

    public static final String NAMESPACE = "hydrogen.emotes";
    public static final String JSON_FILE_NAME = "emotes.json";
    public static File DATA_FOLDER;
    public static File PNG_FOLDER;


    @Override
    public void onEnable() {
        Gson GSON = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Key.class, (InstanceCreator<Key>) type -> Key.key("gson"))
                .create();

        instance = this;

        DATA_FOLDER = this.getDataFolder();
        DATA_FOLDER.mkdirs();
        PNG_FOLDER = new File(DATA_FOLDER + File.separator + "png");
        jsonManager = new JSONManager(GSON);
        try {
            emoteManager = jsonManager.load();
            getLogger().info(emoteManager.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            paperCommandManager = PaperCommandManager.createNative(this, AsynchronousCommandExecutionCoordinator.simpleCoordinator());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(paperCommandManager.queryCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
            paperCommandManager.registerAsynchronousCompletions();
        }

        EmoteManagementCommand emcommand = new EmoteManagementCommand();
        emcommand.register(paperCommandManager);

        // Add emote textures and fonts to Resources, and compile on the last one.
        // This generates a resource pack on the server to eventually serve to players.
        Thread thread = new Thread(() -> {
            try {
                emoteManager.addEmotesToResources();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.schedule(thread, 3, java.util.concurrent.TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        try {
            jsonManager.save(emoteManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
