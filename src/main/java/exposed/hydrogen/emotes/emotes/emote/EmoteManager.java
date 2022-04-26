package exposed.hydrogen.emotes.emotes.emote;

import lombok.Getter;

import java.util.LinkedList;

public class EmoteManager {
    @Getter private final LinkedList<Emote> emotes;

    public EmoteManager() {
        emotes = new LinkedList<>();
    }

    public void addEmote(Emote emote) {
        emotes.add(emote);
    }

    public void removeEmote(Emote emote) {
        emotes.remove(emote);
    }

    public void removeEmote(String string) {
        emotes.removeIf(emote -> emote.getFont().file().value().equals(string));
    }
}
