package dev.peterherczku.fazekasbot.events;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import dev.peterherczku.fazekasbot.utils.managers.FazekasManager;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

@RequiredArgsConstructor
public class ReactionAddEvent extends ListenerAdapter {

    private final FazekasManager fazekasManager;

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        super.onMessageReactionAdd(event);
        if (event.isFromGuild()) {
            Guild guild = event.getGuild();
            Member member = event.getMember();
            User user = event.getUser();
            TextChannel textChannel = event.getTextChannel();

            if (textChannel.getName().equalsIgnoreCase("set-datas")) {
                if (!user.isBot()) {
                    if (event.getReaction().getReactionEmote().getAsCodepoints().toUpperCase().equals("U+1F468")) {
                        if (fazekasManager.getUserManager().hasRole(guild, member, "not-set-datas")) {
                            fazekasManager.getUserManager().removeRole(guild, member, "not-set-datas");
                            fazekasManager.getUserManager().addRole(guild, member, "not-set-teacher-datas");
                            fazekasManager.getUserManager().sendPrivateMessage(user, "Kérjük állítsa be a további adatait a #set-teacher-datas nevű szöveges csatornában!");
                        } else {
                            event.getReaction().removeReaction(user).queue();
                        }
                    }
                    if (event.getReaction().getReactionEmote().getAsCodepoints().toUpperCase().equals("U+1F393")) {
                        if (fazekasManager.getUserManager().hasRole(guild, member, "not-set-datas")) {
                            fazekasManager.getUserManager().removeRole(guild, member, "not-set-datas");
                            fazekasManager.getUserManager().addRole(guild, member, "not-set-student-datas");
                            fazekasManager.getUserManager().sendPrivateMessage(user, "Kérjük állítsa be a további adatait a #set-student-datas nevű szöveges csatornában!");
                        } else {
                            event.getReaction().removeReaction(user).queue();
                        }
                    }
                }
            }
        }



    }
}
