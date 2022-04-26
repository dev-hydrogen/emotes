package exposed.hydrogen.emotes.emotes.emote;

import exposed.hydrogen.emotes.emotes.Emotes;
import lombok.Getter;

import java.io.IOException;
import java.util.LinkedList;

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
}
