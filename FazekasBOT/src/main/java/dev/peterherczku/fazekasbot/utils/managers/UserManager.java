package dev.peterherczku.fazekasbot.utils.managers;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import sun.awt.image.GifImageDecoder;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserManager {

    private final FazekasManager fazekasManager;

    /**
     * rank hozzáadása egy felhasználóhoz
     * @param guild
     * @param member
     * @param roleName
     */

    public void addRole(Guild guild, Member member, String roleName) {
        if (!fazekasManager.getRoleManager().roleExists(guild, roleName)) fazekasManager.getRoleManager().createRole(guild, roleName);

        guild.addRoleToMember(member, fazekasManager.getRoleManager().getRole(guild, roleName)).queue();
    }

    /**
     * rank elvétele egy felhasználótól
     * @param guild
     * @param member
     * @param roleName
     */

    public void removeRole(Guild guild, Member member, String roleName) {
        guild.removeRoleFromMember(member, fazekasManager.getRoleManager().getRole(guild, roleName)).queue();
    }

    /**
     * felhasználónak van-e ilyen rangja, név alapján
     * @param guild
     * @param member
     * @param roleName
     * @return
     */

    public boolean hasRole(Guild guild, Member member, String roleName) {
        if (!fazekasManager.getRoleManager().roleExists(guild, roleName)) fazekasManager.getRoleManager().createRole(guild, roleName);

        for (Role role : member.getRoles())
            if (role.getName().equalsIgnoreCase(roleName))
                return true;
        return false;
    }

    /**
     * felhasználó rankjának lekérdezése név alapján
     * @param guild
     * @param member
     * @param roleName
     * @return
     */

    public Role getRole(Guild guild, Member member, String roleName) {
        if (!fazekasManager.getRoleManager().roleExists(guild, roleName)) fazekasManager.getRoleManager().createRole(guild, roleName);

        for (Role role : member.getRoles())
            if (role.getName().equalsIgnoreCase(roleName))
                return role;
        return null;
    }

    public void sendPrivateMessage(User user, String message) {
        user.openPrivateChannel().flatMap(privateChannel -> privateChannel.sendMessage(new MessageBuilder(message).build())).queue();
    }

    /**
     * a szerveren található összes user lekérése egy tömbben
     * @param guild
     * @return
     */

    public List<User> getUsers(Guild guild) {
        List<User> users = new ArrayList<>();
        for (Member member : guild.getMembers())
            users.add(member.getUser());
        return users;
    }

}
