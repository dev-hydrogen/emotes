package exposed.hydrogen.emotes.emotes.commands;

import lombok.Getter;

public enum Permissions {
    STAFF("hydrogen.emotes.staff"),
    USE("hydrogen.emotes.use"),
    LIST("hydrogen.emotes.list"),
    RELOAD("hydrogen.emotes.reload"),
    HELP("hydrogen.emotes.help");

    @Getter private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

}
