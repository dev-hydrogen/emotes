package exposed.hydrogen.emotes.emotes.json;

import com.google.gson.Gson;
import exposed.hydrogen.emotes.emotes.emote.EmoteManager;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class JSONManager {
    private final Gson gson;

    public JSONManager(Gson gson) {
        this.gson = gson;
    }

    public void save(EmoteManager manager) throws IOException {
        Writer writer = new FileWriter(JSONUtils.LIGHT_JSON);
        gson.toJson(manager, writer);
        writer.close();
    }

    public @NotNull EmoteManager load() throws IOException {
        Reader reader = JSONUtils.getReader(JSONUtils.LIGHT_JSON);
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
