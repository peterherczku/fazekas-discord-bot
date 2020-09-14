package dev.peterherczku.fazekasbot.utils.managers;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import lombok.Getter;

public class FazekasManager {

    @Getter
    private final UserManager userManager;
    @Getter
    private final RoleManager roleManager;
    @Getter
    private final ChannelManager channelManager;

    public FazekasManager() {
        this.userManager=new UserManager(this);
        this.roleManager=new RoleManager(this);
        this.channelManager=new ChannelManager(this);
    }

}
