package dev.peterherczku.fazekasbot.utils.managers;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

@RequiredArgsConstructor
public class ChannelManager {

    private final FazekasManager fazekasManager;

    /**
     * szöveges csatorna létezésének lekérdezése
     * @param guild
     * @param channelname
     * @return boolean
     */

    public boolean textChannelExists(Guild guild, String channelname) {
        for (TextChannel channel : guild.getTextChannels())
            if (channel.getName().equalsIgnoreCase(channelname))
                return true;
        return false;
    }

    /**
     * szöveges csatorna lekérése név alapján
     * @param guild
     * @param channelname
     * @return
     */

    public TextChannel getTextChannel(Guild guild, String channelname) {
        for (TextChannel channel : guild.getTextChannels())
            if (channel.getName().equalsIgnoreCase(channelname))
                return channel;
        return null;
    }

    /**
     * szöveges csatorna létrehozása név alapján
     * @param guild
     * @param channelname
     */

    public void createTextChannel(Guild guild, String channelname) {
        ChannelAction<TextChannel> channelChannelAction = guild.createTextChannel(channelname);
        channelChannelAction.queue();
    }

    /**
     * szöveges csatorna létrehozása név és kategória alapján
     * @param guild
     * @param channelname
     */

    public void createTextChannel(Guild guild, String channelname, String categoryName) {
        ChannelAction<TextChannel> channelChannelAction = guild.createTextChannel(channelname);
        channelChannelAction.setParent(getCategory(guild, categoryName));
        channelChannelAction.queue();
    }

    /**
     * üzenet küldése string üzenet alapján
     * @param channel
     * @param message
     */

    public void sendMessage(TextChannel channel, String message) {
        channel.sendMessage(new MessageBuilder(message).build()).queue();
    }

    /**
     * üzenet küldése Embed üzenet alapján
     * @param channel
     * @param messageEmbed
     */

    public void sendMessage(TextChannel channel, MessageEmbed messageEmbed) {
        channel.sendMessage(messageEmbed).queue();
    }


    /**
     * hangcsatorna létezésének lekérdezése
     * @param guild
     * @param channelname
     * @return boolean
     */

    public boolean voiceChannelExists(Guild guild, String channelname) {
        for (VoiceChannel channel : guild.getVoiceChannels())
            if (channel.getName().equalsIgnoreCase(channelname))
                return true;
        return false;
    }


    /**
     * szöveges csatorna lekérése név alapján
     * @param guild
     * @param channelname
     * @return
     */

    public VoiceChannel getVoiceChannel(Guild guild, String channelname) {
        for (VoiceChannel channel : guild.getVoiceChannels())
            if (channel.getName().equalsIgnoreCase(channelname))
                return channel;
        return null;
    }

    /**
     * hangcsatorna létrehozása név és kategória alapján
     * @param guild
     * @param channelname
     */

    public void createVoiceChannel(Guild guild, String channelname, String categoryName) {
        ChannelAction<VoiceChannel> channelChannelAction = guild.createVoiceChannel(channelname);
        channelChannelAction.setParent(getCategory(guild, categoryName));
        channelChannelAction.queue();
    }






    /**
     * kategória lekérése név alapján
     * @param guild
     * @param categoryName
     * @return
     */

    public Category getCategory(Guild guild, String categoryName) {
        for (Category category : guild.getCategories())
            if (category.getName().equalsIgnoreCase(categoryName))
                return category;
        return null;
    }


}
