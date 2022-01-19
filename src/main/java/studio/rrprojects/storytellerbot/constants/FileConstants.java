package studio.rrprojects.storytellerbot.constants;

import java.io.File;

public class FileConstants {
    //Directories
    public static final String HOME_DIR = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Story Teller" + File.separator;

    //Important Files
    public static final String BOT_CONFIG_FILE = HOME_DIR + "BotInfo.cfg";
}
