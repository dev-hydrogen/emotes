package exposed.hydrogen.emotes.emotes;

import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exposed.hydrogen.emotes.emotes.commands.EmoteManagementCommand;
import exposed.hydrogen.emotes.emotes.emote.EmoteManager;
import exposed.hydrogen.emotes.emotes.json.JSONManager;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Emotes extends JavaPlugin {
    @Getter private static Emotes instance;
    @Getter private static EmoteManager emoteManager;
    @Getter private static PaperCommandManager<CommandSender> paperCommandManager;
    @Getter private static JSONManager jsonManager;
    private static Gson GSON;

    public static final String NAMESPACE = "hydrogen.emotes";
    public static final String JSON_FILE_NAME = "emotes.json";
    public static File DATA_FOLDER;
    public static File PNG_FOLDER;


    @Override
    public void onEnable() {
        GSON = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        instance = this;

        DATA_FOLDER = this.getDataFolder();
        DATA_FOLDER.mkdirs();
        PNG_FOLDER = new File(DATA_FOLDER + File.separator + "png" + File.separator);
        jsonManager = new JSONManager(GSON);
        try {
            emoteManager = jsonManager.load();
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
    }

    @Override
    public void onDisable() {
        try {
            jsonManager.save(emoteManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Plugin shutdown logic
    }
}
