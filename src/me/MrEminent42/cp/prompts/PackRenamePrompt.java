package me.MrEminent42.cp.prompts;

import me.MrEminent42.cp.CloudPacksPlugin;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class PackRenamePrompt extends StringPrompt {

	CloudPacksPlugin plugin;
	
	@Override
	public Prompt acceptInput(ConversationContext arg0, String arg1) {
		
		return END_OF_CONVERSATION;
	}

	@Override
	public String getPromptText(ConversationContext arg0) {
		return plugin.messages.getConfig().getString("prompts-rename");
	}

}
