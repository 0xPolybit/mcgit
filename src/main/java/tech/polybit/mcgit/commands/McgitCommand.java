package tech.polybit.mcgit.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import tech.polybit.mcgit.PolybitsGitVersionControl;

public class McgitCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("mcgit")
            .executes(McgitCommand::execute)
            .then(CommandManager.literal("help")
                .executes(McgitCommand::executeHelp)
            )
        );
    }

    private static int execute(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        // Get mod information dynamically
        String modName = "Polybit's Git Version Control";
        String modVersion = FabricLoader.getInstance().getModContainer(PolybitsGitVersionControl.MOD_ID)
                .map(container -> container.getMetadata().getVersion().getFriendlyString())
                .orElse("Unknown");

        // Send welcome message to the command sender only
        source.sendMessage(Text.literal("§6Welcome to " + modName + " v" + modVersion + "!"));
        source.sendMessage(Text.literal("§7Type §f/mcgit help §7for more details."));

        return 1; // Success
    }

    private static int executeHelp(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        // Display help information
        source.sendMessage(Text.literal("§6=== Polybit's Git Version Control Help ==="));
        source.sendMessage(Text.literal("§7Available commands:"));
        source.sendMessage(Text.literal("§f/mcgit §7- Display mod information"));
        source.sendMessage(Text.literal("§f/mcgit help §7- Show this help message"));
        source.sendMessage(Text.literal("§7More commands will be added in future updates!"));

        return 1; // Success
    }
}



