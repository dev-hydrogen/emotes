package exposed.hydrogen.emotes.emotes.json;

import com.google.gson.Gson;
import exposed.hydrogen.emotes.emotes.Emotes;
import exposed.hydrogen.emotes.emotes.emote.EmoteManager;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class JSONManager {
    private final Gson gson;

    public JSONManager(Gson gson) {
        this.gson = gson;
    }

    public void save(EmoteManager manager) throws IOException {
        Writer writer = new FileWriter(Emotes.DATA_FOLDER + Emotes.JSON_FILE_NAME);
        gson.toJson(manager, writer);
        writer.close();
    }

    public @NotNull EmoteManager load() throws IOException {
        Reader reader = JSONUtils.getReader(new File(Emotes.DATA_FOLDER + Emotes.JSON_FILE_NAME));
        if(reader == null) {
            EmoteManager manager = new EmoteManager();
            save(manager);
            return manager;
        }
        EmoteManager manager = gson.fromJson(reader, EmoteManager.class);
        reader.close();
        return manager;
    }
}
