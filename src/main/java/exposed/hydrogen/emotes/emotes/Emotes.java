package exposed.hydrogen.emotes.emotes;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Emotes extends JavaPlugin {
    @Getter private static Emotes instance;
    public static final String NAMESPACE = "hydrogen.emotes";
    public static File DATA_FOLDER;

    @Override
    public void onEnable() {
        instance = this;
        DATA_FOLDER = this.getDataFolder();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
