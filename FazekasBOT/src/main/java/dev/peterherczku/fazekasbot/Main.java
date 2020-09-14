package dev.peterherczku.fazekasbot;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

import dev.peterherczku.fazekasbot.commands.HomeworkCommand;
import dev.peterherczku.fazekasbot.commands.SetchannelCommand;
import dev.peterherczku.fazekasbot.commands.StudentdatasCommand;
import dev.peterherczku.fazekasbot.commands.TeacherDatasCommand;
import dev.peterherczku.fazekasbot.events.MemberJoinEvent;
import dev.peterherczku.fazekasbot.events.ReactionAddEvent;
import dev.peterherczku.fazekasbot.utils.managers.FazekasManager;
import lombok.Getter;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    @Getter
    private static JDA jda;
    private static FazekasManager fazekasManager;

    public static void main(String[] args) {
            try {
                JDABuilder builder = new JDABuilder(AccountType.BOT).setToken("NzUyOTM3MjU5OTMzODkyNzY5.X1e5dw.AU_y7dvL1-gIs1z8jhJaSqYbEIU");
                builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
                jda=builder.build();
                fazekasManager=new FazekasManager();
                registerEvents();
            } catch (LoginException e) {
                System.out.println("Something unexpected happened during the loading");
                e.printStackTrace();
            }

    }

    private static void registerEvents() {
        //commands
        jda.addEventListener(new HomeworkCommand(fazekasManager));
        jda.addEventListener(new StudentdatasCommand(fazekasManager));
        jda.addEventListener(new TeacherDatasCommand(fazekasManager));
        jda.addEventListener(new SetchannelCommand(fazekasManager));

        //events
        jda.addEventListener(new MemberJoinEvent(fazekasManager));
        jda.addEventListener(new ReactionAddEvent(fazekasManager));
    }

    /**
     * belépéskor mindenkinek be kell állítania, hogy tanár vagy diák-e
     * !homework @csoport <házi> = embed üzenetet küld a #házi-feladat csatornába
     * !tanora @csoport start = embed üzenetet küld a #órák csatornába és létrehoz egy új hangcsatornát "@csoport óra" néven
     * !tanora @csoport stop = embed üzenet küld a #órák csatornába és kitörli a létrehozott hangcsatornát
     * !csoportmunka start @csoport integer = létrehoz integer számonként egy szobát és belemoveolja a usereket
     * !csoportmunka stop @csoport = kitörli a szobákat és visszamoveolja az összes usert
     */

}
