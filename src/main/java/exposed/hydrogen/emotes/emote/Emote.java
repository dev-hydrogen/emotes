package exposed.hydrogen.emotes.emote;

import com.google.gson.JsonDeserializer;
import exposed.hydrogen.emotes.Emotes;
import lombok.Data;
import net.kyori.adventure.key.Key;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.font.BitMapFontProvider;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents an emote.
 */
@Data
public class Emote {
    private transient Texture texture;
    private transient BitMapFontProvider font;
    private String filename;
    private Key key;
    private String description; // = "This is an emote!";
    private List<String> unicode; // = List.of("\uF80A");
    private List<String> usages; // = List.of(":emote:", ":e:", ":emoet:");

    private Emote() {
        // Required for GSON
        filename = "";
        key = Key.key("gson");
        description = "";
        usages = new LinkedList<>();
        unicode = new LinkedList<>();
        texture = Texture.of(key, Writable.file(new File(Emotes.PNG_FOLDER + File.separator + filename)));
        font = FontProvider.bitMap(key,8,0, unicode);
    }
    /**
     * Constructs a new emote.
     * @param filename the filename of the emote.
     * @param key The key of the emote.
     * @param description The description of the emote.
     * @param usages The aliases for usage of the emote.
     */
    public Emote(String filename, Key key, String description, List<String> usages, List<String> unicode) throws FileNotFoundException {
        this(
                Texture.of(key, Writable.file(new File(Emotes.PNG_FOLDER + File.separator + filename))),
                filename,
                FontProvider.bitMap(key,8,0, unicode),
                description,
                usages
        );
    }

    /**
     * Constructs a new emote.
     * @param texture The texture of the emote.
     * @param key The key of the emote.
     * @param description The description of the emote.
     * @param usages The aliases for usage of the emote.
     */
    public Emote(Texture texture, String filename, Key key, String description, List<String> usages, List<String> unicode) {
        this(
                texture,
                filename,
                FontProvider.bitMap(key,8,0, unicode),
                description,
                usages
        );
    }

    /**
     * Constructs a new emote.
     * @param texture The texture of the emote.
     * @param font The font of the emote.
     * @param description The description of the emote.
     * @param usages The aliases for usage of the emote.
     */
    public Emote(Texture texture, String filename, BitMapFontProvider font, String description, List<String> usages) {

        this.texture = texture;
        this.filename = filename;
        this.font = font;
        this.key = font.file();
        this.description = description;
        this.usages = usages;
        unicode = new LinkedList<>();
        unicode.addAll(font.characters());
    }

    public class EmoteDeserializer implements JsonDeserializer<Emote> {
        @Override
        public Emote deserialize(com.google.gson.JsonElement json, java.lang.reflect.Type type, com.google.gson.JsonDeserializationContext context) throws com.google.gson.JsonParseException {
            try {
                return new Emote(
                        json.getAsJsonObject().get("filename").getAsString(),
                        Key.key(json.getAsJsonObject().get("key").getAsJsonObject().get("namespace").getAsString(), json.getAsJsonObject().get("key").getAsJsonObject().get("value").getAsString()),
                        json.getAsJsonObject().get("description").getAsString(),
                        List.of(json.getAsJsonObject().get("usages").getAsJsonArray().toString().replace("[", "").replace("]", "").split(",")),
                        List.of(json.getAsJsonObject().get("unicode").getAsJsonArray().toString().replace("[", "").replace("]", "").split(","))
                );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
