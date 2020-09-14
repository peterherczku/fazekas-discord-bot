package dev.peterherczku.fazekasbot.events;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import dev.peterherczku.fazekasbot.utils.managers.FazekasManager;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@RequiredArgsConstructor
public class MemberJoinEvent extends ListenerAdapter {

    private final FazekasManager fazekasManager;

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        Guild guild = event.getGuild();
        Member member = event.getMember();
        User user = member.getUser();

        fazekasManager.getUserManager().addRole(guild, member, "not-set-datas");
        fazekasManager.getUserManager().sendPrivateMessage(user, user.getAsMention()+" Kérem állítsa be, hogy diák, vagy tanár-e a #set-datas szöveges csatornában az emotikonok segítségével!");
    }
}
