import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Logger {

    private static Charset utf8 = StandardCharsets.UTF_8;
    private static final String SPACE = "  ";
    private static boolean isStart = true;

    public static void logRun(Brain brain, Action.Actions action){
        List<String> line = null;

        if (isStart){
            line = Arrays.asList("=============================================NEW GAME=============================================",
                    buildLogData(brain, action));
            isStart = false;
        } else {
            line = Arrays.asList(buildLogData(brain, action));
        }

        try {
            File logDir = new File(System.getProperty("user.dir") + File.separator + "logs");
            File logFile = new File(logDir,brain.getTeam()+brain.getNumber() + ".log");
            if (!logDir.exists()){
                if (logDir.mkdir() && !logFile.exists()){
                    logFile.createNewFile();
                }

            }

            Files.write(Paths.get(logFile.getAbsolutePath()),
                    line, utf8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }


    private static String buildLogData(Brain brain, Action.Actions action){
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(".[ ");
        logBuilder.append(getCurrentTimeStamp());
        logBuilder.append(SPACE);
        logBuilder.append("RUN: ");
        logBuilder.append(brain.getRunNumber());
        logBuilder.append(SPACE);
        logBuilder.append("CURRENT STATE: ");
        logBuilder.append(brain.getAgentState().getStateName());
        logBuilder.append(SPACE);
        logBuilder.append("ACTION: ");
        logBuilder.append(action.name());
        logBuilder.append(" ].");
        return logBuilder.toString();
    }
}
