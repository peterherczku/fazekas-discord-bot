package dev.peterherczku.fazekasbot.commands;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import dev.peterherczku.fazekasbot.utils.Subject;
import dev.peterherczku.fazekasbot.utils.managers.FazekasManager;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

@RequiredArgsConstructor
public class TeacherDatasCommand extends ListenerAdapter {

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

            if (args[0].equalsIgnoreCase("!teacherdatas")) {
                if (channel.getName().equalsIgnoreCase("set-teacher-datas")) {
                    if (fazekasManager.getUserManager().hasRole(guild, member, "not-set-teacher-datas")) {
                        if (args.length >= 3) {
                            String possibleSubject = args[1];

                            Subject subject = null;

                            for (Subject subjects : Subject.values()) {
                                if (subjects.toString().toLowerCase().equalsIgnoreCase(possibleSubject.toLowerCase())) {
                                    subject=subjects;
                                }
                            }

                            if (subject!=null) {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i=2; i<=args.length-1; i++) {
                                    stringBuilder.append(args[i]+" ");
                                }
                                String name = stringBuilder.toString();

                                guild.modifyNickname(member, name).queue();
                                fazekasManager.getUserManager().addRole(guild, member, "Tanár");
                                fazekasManager.getUserManager().addRole(guild, member, subject.toString());
                                fazekasManager.getUserManager().removeRole(guild, member, "not-set-teacher-datas");
                                fazekasManager.getUserManager().sendPrivateMessage(user, user.getAsMention()+ " Sikeresen beállíotta az adatait. Mostantól teljes hozzáférése van a Discord szerverhez.");
                                message.delete().queue();
                            } else {
                                fazekasManager.getUserManager().sendPrivateMessage(user, user.getAsMention()+" Ilyen tantrágy nem létezik! Tantárgyak: Matematika, Fizika, Kémia, " +
                                        "Biológia, Földrajz, Francia, Angol, Rajz, Informatika, Ének");
                                message.delete().queue();
                            }
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
