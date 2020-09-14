package dev.peterherczku.fazekasbot.commands;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import dev.peterherczku.fazekasbot.utils.managers.FazekasManager;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

@RequiredArgsConstructor
public class StudentdatasCommand extends ListenerAdapter {

    private final FazekasManager fazekasManager;

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.isFromGuild()) {
            Guild guild = event.getGuild();
            Member member = event.getMember();
            User user = member.getUser();
            TextChannel channel = event.getTextChannel();
            Message message = event.getMessage();
            String[] args = message.getContentRaw().split(" ");

            if (args[0].equalsIgnoreCase("!studentdatas")) {
                if (channel.getName().equalsIgnoreCase("set-student-datas")) {
                    if (fazekasManager.getUserManager().hasRole(guild, member, "not-set-student-datas")) {
                        if (args.length >= 2) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i=1; i<=args.length-1; i++) {
                                stringBuilder.append(args[i]+" ");
                            }
                            String name = stringBuilder.toString();

                            guild.modifyNickname(member, name).queue();
                            fazekasManager.getUserManager().addRole(guild, member, "Diák");
                            fazekasManager.getUserManager().removeRole(guild, member, "not-set-student-datas");
                            fazekasManager.getUserManager().sendPrivateMessage(user, user.getAsMention()+ " Sikeresen beállíotta az adatait. Mostantól teljes hozzáférése van a Discord szerverhez.");
                            message.delete().queue();
                        } else {
                            message.delete().queue();
                        }
                    } else {
                        message.delete().queue();
                    }
                } else {
                    message.delete().queue();
                }
            }
        }
    }

}
