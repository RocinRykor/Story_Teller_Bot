package studio.rrprojects.storytellerbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
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
