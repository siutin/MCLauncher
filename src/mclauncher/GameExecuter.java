package mclauncher;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

            final StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null){
                logger.info(line);
                sb.append(line+"\n");
            }

            try {
                process.waitFor();
                int exitValue = process.exitValue();

                if(exitValue != 0){

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            ErrorDailog dialog = new ErrorDailog(sb.toString());
                            dialog.setVisible(true);
                        }
                    });

                }

            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
        logger.info(command);
    }


}
