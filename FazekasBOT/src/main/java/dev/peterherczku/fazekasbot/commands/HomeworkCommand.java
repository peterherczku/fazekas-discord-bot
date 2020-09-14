package dev.peterherczku.fazekasbot.commands;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import dev.peterherczku.fazekasbot.utils.managers.FazekasManager;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.xml.soap.Text;
import java.awt.*;

@RequiredArgsConstructor
public class HomeworkCommand extends ListenerAdapter {

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

            if (args[0].equalsIgnoreCase("!homework")) {
                if (channel.getName().equalsIgnoreCase("bot-chat")) {
                    if (fazekasManager.getUserManager().hasRole(guild, member, "Tanár")) {
                        if (args.length > 2) {
                            if (fazekasManager.getChannelManager().textChannelExists(guild, "házi-feladatok")) {
                                TextChannel homeworksChannel = fazekasManager.getChannelManager().getTextChannel(guild, "házi-feladatok");
                                Role role = fazekasManager.getRoleManager().getRoleByMention(guild, args[1]);
                                if (role != null) {

                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (int i = 2; i <= args.length - 1; i++) {
                                        stringBuilder.append(args[i]+" ");
                                    }
                                    String homework = stringBuilder.toString();


                                    EmbedBuilder embedBuilder = new EmbedBuilder();
                                    embedBuilder.setTitle("Házi feladat | " + role.getName());
                                    embedBuilder.setDescription("Házi feladatot adtak ki a " + role.getName() + " csoportnak!");
                                    embedBuilder.addField("Házi feladat", homework, false);
                                    embedBuilder.setFooter("Tanár: " + user.getName() + " | by: peterherczku#9596 | ALPHA0.0.1");
                                    embedBuilder.setColor(Color.ORANGE);

                                    fazekasManager.getChannelManager().sendMessage(homeworksChannel, role.getAsMention());
                                    fazekasManager.getChannelManager().sendMessage(homeworksChannel, embedBuilder.build());
                                    fazekasManager.getChannelManager().sendMessage(channel, user.getAsMention() + " Sikeresen feladta a házi feladatot a " + role.getAsMention() + " csoportnak!");
                                } else {
                                    fazekasManager.getChannelManager().sendMessage(channel, user.getAsMention() + " Nem létezik ilyen csoport a szerveren!");
                                }
                            } else {
                                fazekasManager.getChannelManager().sendMessage(channel, user.getAsMention() + " Még nem létezik a #házi-feladatok szöveges csatorna!");
                            }

                        } else {
                            fazekasManager.getChannelManager().sendMessage(channel, user.getAsMention() + " Hibás használat! Használat: !homework @csoport");
                        }
                    } else {
                        fazekasManager.getChannelManager().sendMessage(channel, user.getAsMention() + " Te nem vagy tanár!");
                    }
                } else {
                    fazekasManager.getChannelManager().sendMessage(channel, user.getAsMention() + " Ezt csak a #bot-chat csatornában lehet használni!");
                    message.delete().queue();
                }
            }


        }
    }
}
