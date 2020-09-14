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
import java.awt.*;

@RequiredArgsConstructor
public class SetchannelCommand extends ListenerAdapter {

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

            if (args[0].equalsIgnoreCase("!setchannel")) {
                if (channel.getName().equalsIgnoreCase("bot-chat")) {
                    if (args.length == 3) {
                        if (args[1].equals("datas") || args[1].equals("studentdatas") || args[1].equals("teacherdatas")) {
                            if (args[1].equals("datas")) {
                                String possibleChannelName = args[2];
                                if (fazekasManager.getChannelManager().textChannelExists(guild, possibleChannelName)) {
                                    TextChannel dataschannel = fazekasManager.getChannelManager().getTextChannel(guild, possibleChannelName);

                                    EmbedBuilder embedBuilder = new EmbedBuilder();
                                    embedBuilder.setTitle("Adatok beállítása");
                                    embedBuilder.setDescription("Ahhoz, hogy teljes körű hozzáférést tudjunk biztosítani a Discord szerverünkhöz, kérjük reagáljon az alábbi módon:");
                                    embedBuilder.addField("Tanár", "Amennyiben tanár, kérjük reagáljon a következő emojival: :man:", false);
                                    embedBuilder.addField("Diák", "Amennyiben diák, kérjük reagáljon a következő emojival: :mortar_board:", false);
                                    embedBuilder.setFooter("Fazekas Discord BOT | by: peterherczku#9596");
                                    embedBuilder.setColor(Color.ORANGE);

                                    dataschannel.sendMessage(embedBuilder.build()).queue(embedmessage -> {
                                        embedmessage.addReaction("U+1F468").queue();
                                        embedmessage.addReaction("U+1F393").queue();
                                    });
                                    fazekasManager.getChannelManager().sendMessage(channel, "Sikeresen beállítottad az Adatok beállítására szolgáló csatornát!");
                                } else {
                                    fazekasManager.getChannelManager().sendMessage(channel, "Ilyen csatorna nem létezik!");
                                }
                            }
                            if (args[1].equals("studentdatas")) {
                                String possibleChannelName = args[2];
                                if (fazekasManager.getChannelManager().textChannelExists(guild, possibleChannelName)) {
                                    TextChannel dataschannel = fazekasManager.getChannelManager().getTextChannel(guild, possibleChannelName);

                                    EmbedBuilder embedBuilder = new EmbedBuilder();
                                    embedBuilder.setTitle("Diák adatok beállítása");
                                    embedBuilder.setColor(Color.ORANGE);
                                    embedBuilder.setDescription("Itt további pontosított adatokat kell megadnia.");
                                    embedBuilder.addField("Név", "A Nevére azért van szükségünk, hogy a Discord szerveren a saját neve látszódjon mindenki számára.", false);
                                    embedBuilder.addField("", "", true);
                                    embedBuilder.addField("Továbblépéshez:", "A továbblépéshez, gépelje be a következő parancsot ebbe a szöveges csatornába: !studentdatas név", false);
                                    embedBuilder.setFooter("Fazekas Discord BOT | by: peterherczku#9596");

                                    dataschannel.sendMessage(embedBuilder.build()).queue();
                                    fazekasManager.getChannelManager().sendMessage(channel, "Sikeresen beállította a Diák adatok beállítására megfelelő csatornát!");

                                } else {
                                    fazekasManager.getChannelManager().sendMessage(channel, "Ilyen csatorna nem létezik!");
                                }
                            }
                            if (args[1].equals("teacherdatas")) {
                                String possibleChannelName = args[2];
                                if (fazekasManager.getChannelManager().textChannelExists(guild, possibleChannelName)) {
                                    TextChannel dataschannel = fazekasManager.getChannelManager().getTextChannel(guild, possibleChannelName);

                                    EmbedBuilder embedBuilder = new EmbedBuilder();
                                    embedBuilder.setTitle("Tanári adatok beállítása");
                                    embedBuilder.setColor(Color.ORANGE);
                                    embedBuilder.setDescription("Itt további pontosított adatokat kell megadnia.");
                                    embedBuilder.addField("Név", "A Nevére azért van szükségünk, hogy a Discord szerveren a saját neve látszódjon mindenki számára.", false);
                                    embedBuilder.addField("Tantárgy", "Szükségünk van továbbá a tantárgyra amit tanít, hogy a tanulók, tudjanak magával beszélni a különböző hang-, és szöveges csatornákban.", false);
                                    embedBuilder.addField("", "", true);
                                    embedBuilder.addField("Megjegyzés:", "Tantárgytípusok: Matematika, Magyar, Történelem, Fizika, Kémia, Földrajz, Francia, Angol, Informatika, Ének, Rajz.", false);
                                    embedBuilder.addField("Továbblépéshez:", "A továbblépéshez, gépelje be a következő parancsot ebbe a szöveges csatornába: !teacherdatas tantárgy név", false);
                                    embedBuilder.setFooter("Fazekas Discord BOT | by: peterherczku#9596");

                                    dataschannel.sendMessage(embedBuilder.build()).queue();
                                    fazekasManager.getChannelManager().sendMessage(channel, "Sikeresen beállította a Tanári adatok beállítására megfelelő csatornát!");

                                } else {
                                    fazekasManager.getChannelManager().sendMessage(channel, "Ilyen csatorna nem létezik!");
                                }
                            }
                        } else {
                            fazekasManager.getChannelManager().sendMessage(channel, user.getAsMention() + " Hibás használat: Használat: !setchannel <datas|studentdatas|teacherdatas> #channel");
                        }
                    } else {
                        fazekasManager.getChannelManager().sendMessage(channel, user.getAsMention() + " Hibás használat: Használat: !setchannel <datas|studentdatas|teacherdatas> #channel");
                    }
                } else {
                    message.delete().queue();
                }
            }
        }
    }

}
