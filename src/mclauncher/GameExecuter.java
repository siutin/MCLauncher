package mclauncher;

import org.apache.log4j.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameExecuter implements Runnable{

    private Logger logger = Logger.getLogger(GameExecuter.class);

    private JavaConfig _javaConfig;
    private GameConfig _gameConfig;
    public GameExecuter(JavaConfig javaConfig, GameConfig gameConfig){
        _javaConfig = javaConfig;
        _gameConfig = gameConfig;
    }

    // this params arraylist is a command to execute the game
    private List<String> GetParams(){

        ArrayList<String> params = new ArrayList<String>();

        // java executable binary
        params.add(_javaConfig.getJava());

        params.add("-Xms".concat(_javaConfig.getXms()));
        params.add("-Xmx".concat(_javaConfig.getXmx()));
        params.add("-Djava.library.path=".concat(_javaConfig.getJava_library_path()));

        params.add("-cp");
        params.add(_javaConfig.getClasspath());

        params.add("net.minecraft.client.main.Main");

        params.add("--username");
        params.add(_gameConfig.getUserName());

        params.add("--session");
        params.add(_gameConfig.getSession());

        params.add("--gameDir");
        params.add(_gameConfig.getGameDir());

        params.add("--assetsDir");
        params.add(_gameConfig.getAssetsDir());

        params.add("--version");
        params.add(_gameConfig.getVersion());

        return params;
    }
    @Override
    public void run() {

        List<String> params = GetParams();
        ProcessBuilder pb = new ProcessBuilder(params);

        try {
            Process process = pb.start();

            logger.info("------------------ Game Execute ------------------------");

            printCommand(params);

            //read output after the command is executed
            InputStreamReader isr = new InputStreamReader(process.getErrorStream());
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null){
                logger.info(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printCommand(List<String> params) {
        String command="";
        for (String param : params) {
            command += param + " ";
        }
        System.out.println(command);
    }


}
