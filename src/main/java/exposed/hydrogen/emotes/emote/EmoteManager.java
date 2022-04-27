package exposed.hydrogen.emotes.emote;

import exposed.hydrogen.emotes.Emotes;
import exposed.hydrogen.resources.Resources;
import lombok.Getter;
import lombok.ToString;
import team.unnamed.creative.font.Font;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@ToString
public class EmoteManager {
    @Getter private final LinkedList<Emote> emotes;

    public EmoteManager() {
        emotes = new LinkedList<>();
    }

    public void addEmote(Emote emote) {
        emotes.add(emote);
        try {
            Emotes.getJsonManager().save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeEmote(Emote emote) {
        emotes.remove(emote);
        try {
            Emotes.getJsonManager().save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeEmote(String string) {
        emotes.removeIf(emote -> emote.getFont().file().value().equals(string));
    }

    public void addEmotesToResources() {
        Emotes.getInstance().getLogger().info("Adding emotes to resources...");
        List<Emote> emotes = getEmotes();
        for (int i = 0; i < emotes.size(); i++) {
            Emote emote = emotes.get(i);
            boolean shouldCompile = i == emotes.size() - 1;
            Resources.getResourcePackHandler().addResource(emote.getTexture(), shouldCompile);
            Resources.getResourcePackHandler().addResource(Font.of(emote.getKey(),emote.getFont()), shouldCompile);
        }
        Emotes.getInstance().getLogger().info("Compiled resource pack successfully!");
    }
}
