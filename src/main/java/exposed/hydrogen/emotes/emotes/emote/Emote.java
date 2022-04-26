package exposed.hydrogen.emotes.emotes.emote;

import lombok.Getter;
import team.unnamed.creative.font.BitMapFontProvider;
import team.unnamed.creative.texture.Texture;

import java.util.List;

/**
 * Represents an emote.
 */
public class Emote {
    @Getter private final Texture texture;
    @Getter private final BitMapFontProvider font;
    @Getter private final String description; // = "This is an emote!";
    @Getter private final List<String> usages; // = List.of(":emote:", ":e:", ":emoet:");

    private Emote() {
        texture = null;
        font = null;
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
        this.description = description;
        this.usages = usages;
    }

}
