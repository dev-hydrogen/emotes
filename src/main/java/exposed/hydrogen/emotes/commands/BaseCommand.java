package exposed.hydrogen.emotes.commands;

import cloud.commandframework.CommandManager;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface BaseCommand {

    void register(@NotNull final CommandManager<CommandSender> manager);
}
