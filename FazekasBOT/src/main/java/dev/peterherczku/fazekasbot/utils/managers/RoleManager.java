package dev.peterherczku.fazekasbot.utils.managers;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.restaction.RoleAction;

import java.awt.*;
import java.util.Collection;

@RequiredArgsConstructor
public class RoleManager {

    private final FazekasManager fazekasManager;

    /**
     * rank készítése név alapján
     * @param guild
     * @param roleName
     */

    public void createRole(Guild guild, String roleName) {
        RoleAction roleAction = guild.createRole();
        roleAction.setName(roleName);
        roleAction.queue();
    }

    /**
     * rank készítése név és szín alapján
     * @param guild
     * @param roleName
     * @param color
     */

    public void createRole(Guild guild, String roleName, Color color) {
        RoleAction roleAction = guild.createRole();
        roleAction.setName(roleName);
        roleAction.setColor(color);
        roleAction.queue();
    }

    /**
     * rank készítése név, szín és jogok alapján
     * @param guild
     * @param roleName
     * @param color
     * @param permissions
     */

    public void createRole(Guild guild, String roleName, Color color, Collection<Permission> permissions) {
        RoleAction roleAction = guild.createRole();
        roleAction.setName(roleName);
        roleAction.setColor(color);
        roleAction.setPermissions(permissions);
        roleAction.queue();
    }

    /**
     * rank lekérése név alapján
     * @param guild
     * @param roleName
     * @return Role
     */

    public Role getRole(Guild guild, String roleName) {
        for (Role role : guild.getRoles())
            if (role.getName().equalsIgnoreCase(roleName))
                return role;
        return null;
    }

    /**
     * rank lekérése név alapján
     * @param guild
     * @param mention
     * @return Role
     */

    public Role getRoleByMention(Guild guild, String mention) {
        for (Role role : guild.getRoles()) {
            if (role.getAsMention().contains(mention)) {
                return role;
            }
        }
        return null;
    }

    /**
     * rank létezésének lekérése
     * @param guild
     * @param roleName
     * @return boolean
     */

    public boolean roleExists(Guild guild, String roleName) {
        for (Role role : guild.getRoles())
            if (role.getName().equalsIgnoreCase(roleName))
                return true;
        return false;
    }


}
