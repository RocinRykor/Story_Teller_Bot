package studio.rrprojects.storytellerbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import studio.rrprojects.storytellerbot.config.ConfigController;
import studio.rrprojects.storytellerbot.server.Server;
import studio.rrprojects.util_library.DebugUtils;

import javax.security.auth.login.LoginException;

public class MainController {
    private final JDA jda;
    private final Server server;
    private ConfigController configController;
    

    public MainController() {
        DebugUtils.ProgressNormalMsg("MAIN CONTROLLER: STARTING!");

        //Grab Config Information
        configController = new ConfigController();

        //Start Discord Process
        jda = StartJDA();

        //Start Server Process
        server = new Server(this, 5000);
    }

    public void processLine(String line) {
        //This is the input from the client
        //In the future it will check if valid by ensuring it is a json object
        //For now just post to botTesting

        TextChannel channel = jda.getGuilds().get(0).getTextChannelsByName("bot_testing", true).get(0);
        channel.sendMessage(line).queue();
    }

    private JDA StartJDA() {
        try {
            return JDABuilder.createDefault(configController.getOption("botToken")).build();
        } catch (LoginException e) {
            e.printStackTrace();
            DebugUtils.ErrorMsg("MAIN CONTROLLER: ERROR - UNABLE TO LOGIN, PLEASE CHECK CONFIG FILE");
            System.exit(0);
        }

        return null;
    }

    public static void main(String[] args) {
	    new MainController();
    }

}
