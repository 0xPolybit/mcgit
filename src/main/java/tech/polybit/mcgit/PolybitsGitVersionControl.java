package tech.polybit.mcgit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.polybit.mcgit.commands.McgitCommand;

public class PolybitsGitVersionControl implements ModInitializer {
	public static final String MOD_ID = "mcgit";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register commands
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			McgitCommand.register(dispatcher);
		});
	}
}