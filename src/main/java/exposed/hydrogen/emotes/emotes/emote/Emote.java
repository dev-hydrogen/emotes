package exposed.hydrogen.emotes.emotes.emote;

import lombok.Getter;
import team.unnamed.creative.font.Font;
import team.unnamed.creative.texture.Texture;

import java.util.List;

/**
 * Represents an emote.
 */
public class Emote {
    @Getter private Texture texture;
    @Getter private Font font;
    @Getter private String name = "emote";
    @Getter private String description = "This is an emote!";
    @Getter private String usage = ":emote:";
    @Getter private List<String> usageAliases = List.of(":e:", ":emoet:");

    /**
     * Constructs a new emote.
     * @param texture The texture of the emote.
     * @param font The font of the emote.
     * @param name The name of the emote.
     * @param description The description of the emote.
     * @param usage The usage of the emote.
     * @param aliases The aliases for usage of the emote.
     */
    public Emote(Texture texture, Font font, String name, String description, String usage, List<String> aliases) {
        this.texture = texture;
        this.font = font;
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.usageAliases = aliases;
    }

}
