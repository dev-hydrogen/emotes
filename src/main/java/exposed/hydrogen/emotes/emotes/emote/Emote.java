package exposed.hydrogen.emotes.emotes.emote;

import lombok.Getter;
import net.kyori.adventure.key.Key;
import team.unnamed.creative.font.BitMapFontProvider;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents an emote.
 */
public class Emote {
    @Getter private final Texture texture;
    @Getter private transient final BitMapFontProvider font;
    @Getter private final Key key;
    @Getter private final String description; // = "This is an emote!";
    @Getter private final List<String> unicode;
    @Getter private final List<String> usages; // = List.of(":emote:", ":e:", ":emoet:");

    private Emote() {
        texture = null;
        font = null;
        key = null;
        unicode = null;
        description = "";
        usages = null;
    }
    /**
     * Constructs a new emote.
     * @param texture The texture of the emote.
     * @param font The font of the emote.
     * @param description The description of the emote.
     * @param usages The aliases for usage of the emote.
     */
    public Emote(Texture texture, BitMapFontProvider font, String description, List<String> usages) {
        this.texture = texture;
        this.font = font;
        this.key = font.file();
        this.description = description;
        this.usages = usages;
        unicode = new LinkedList<>();
        unicode.addAll(font.characters());
    }

    /**
     * Constructs a new emote.
     * @param texture The texture of the emote.
     * @param key The key of the emote.
     * @param description The description of the emote.
     * @param usages The aliases for usage of the emote.
     */
    public Emote(Texture texture, Key key, String description, List<String> usages, List<String> unicode) {
        this.texture = texture;
        this.font = FontProvider.bitMap(key,8,0, unicode);
        this.key = font.file();
        this.description = description;
        this.usages = usages;
        this.unicode = font.characters();
    }

}
