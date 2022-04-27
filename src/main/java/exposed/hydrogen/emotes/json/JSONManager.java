package exposed.hydrogen.emotes.json;

import com.google.gson.Gson;
import exposed.hydrogen.emotes.emote.EmoteManager;
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
        Writer writer = new FileWriter(JSONUtils.EMOTES_JSON);
        gson.toJson(manager, writer);
        writer.close();
    }

    public @NotNull EmoteManager load() throws IOException {
        Reader reader = JSONUtils.getReader(JSONUtils.EMOTES_JSON);
        EmoteManager manager = new EmoteManager();
        if(reader == null) {
            save(manager);
            return manager;
        }
        EmoteManager fromJson = gson.fromJson(reader, EmoteManager.class);
        reader.close();
        if(fromJson == null || fromJson.getEmotes() == null) {
            save(manager);
            return manager;
        }
        return fromJson;
    }
}
