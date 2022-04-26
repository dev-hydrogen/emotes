package exposed.hydrogen.emotes.emotes.commands;

import cloud.commandframework.ArgumentDescription;
import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.context.CommandContext;
import exposed.hydrogen.emotes.emotes.Emotes;
import exposed.hydrogen.emotes.emotes.emote.Emote;
import net.kyori.adventure.key.Key;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;

public class EmoteManagementCommand implements BaseCommand {

    public EmoteManagementCommand() {}

    @Override
    public void register(@NotNull CommandManager<CommandSender> manager) {
        final Command.Builder<CommandSender> builder = manager.commandBuilder("emotes");

        manager.command(builder.literal(
                "add",
                ArgumentDescription.of("Add an emote"),
                "a")
                .permission(Permissions.STAFF.getPermission())
                .argument(StringArgument.of("file"), ArgumentDescription.of("File name ending with file extension"))
                .argument(StringArgument.of("name"), ArgumentDescription.of("The name of the emote"))
                .argument(StringArgument.of("unicode"), ArgumentDescription.of("The unicode of the emote"))
                .argument(StringArgument.quoted("description"), ArgumentDescription.of("The description of the emote"))
                .argument(StringArgument.optional("aliases", StringArgument.StringMode.GREEDY), ArgumentDescription.of("The aliases of the emote"))
                .handler(this::addEmote)
        );
        manager.command(builder.literal(
                        "remove",
                        ArgumentDescription.of("Remove an emote"),
                        "r")
                .permission(Permissions.STAFF.getPermission())
                .argument(StringArgument.of("name"), ArgumentDescription.of("The name of the emote"))
                .handler(this::removeEmote)
        );
    }

    private void addEmote(@NotNull CommandContext<CommandSender> context) {
        String file = context.get("file");
        String name = context.get("name");
        String description = context.get("description");
        String unicode = context.get("unicode");
        String[] aliases = ((String)context.get("aliases")).split(" ");

        Key key = Key.key(Emotes.NAMESPACE, name);

        Texture texture = Texture.of(key, Writable.file(new File(Emotes.PNG_FOLDER + file)));

        Emote emote = new Emote(texture, FontProvider.bitMap(key,8,0, List.of(unicode)) ,description, asList(aliases));
        Emotes.getEmoteManager().addEmote(emote);
    }

    private void removeEmote(@NotNull CommandContext<CommandSender> context) {
        String name = context.get("name");

        Emotes.getEmoteManager().removeEmote(name);
    }
}
