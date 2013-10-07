package mclauncher;

import mclauncher.PathDecodes.IPathDecode;
import mclauncher.PathDecodes.LinuxPathDecode;
import mclauncher.PathDecodes.MacPathDecode;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 7/10/13
 * Time: 4:43 PM
 */

public class Main {

    public static void main(String[] args) {

        init();
        loadConfigs();

        MainGUI dialog = new MainGUI();
        dialog.setTitle("MCLauncer v1.0.0 by osiutino");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

    private static void init(){

        String homeDir = System.getProperty("user.home");
        String osName = System.getProperty("os.name");

        System.out.println( homeDir );
        System.out.println( osName );
        OSs os = ENVHelper.OS(osName);

        if(os == OSs.MAC){
            pathDecode = new MacPathDecode(homeDir);
        }else if (os == OSs.LINUX)
            pathDecode = new LinuxPathDecode(homeDir);
    }

    private static void loadConfigs() {

        //java environment configuration
        Properties javaProps = new Properties();

        try {
            javaProps.load(new FileInputStream("env.properties"));

            javaConfig.setJava( pathDecode.Apply( javaProps.getProperty("java")) );
            javaConfig.setXms(  pathDecode.Apply(javaProps.getProperty("Xms")) );
            javaConfig.setXmx( pathDecode.Apply(javaProps.getProperty("Xmx")) );
            javaConfig.setJava_library_path( pathDecode.Apply(javaProps.getProperty("java.library.path")) );
            javaConfig.setClasspath( pathDecode.Apply(javaProps.getProperty("classpath")) );

        }catch (IOException e) {
            e.printStackTrace();
        }

        //game enviroment configuration
        Properties gameProps = new Properties();

        try {
            gameProps.load(new FileInputStream("game.properties"));

            Random random = new Random(System.currentTimeMillis());
            gameConfig.setSession(String.valueOf(random.nextInt()));
            gameConfig.setGameDir( pathDecode.Apply(gameProps.getProperty("gameDir")) );
            gameConfig.setAssetsDir( pathDecode.Apply(gameProps.getProperty("assetsDir")) );
            gameConfig.setVersion(gameProps.getProperty("version"));

        } catch (IOException e) {
            e.printStackTrace();

        }

        printConfigs();
    }

    private static void printConfigs(){

        System.out.println("------------ Print Configs --------------");

        System.out.println("java=".concat(String.valueOf(javaConfig.getJava())));
        System.out.println("Xms=".concat(String.valueOf(javaConfig.getXms())));
        System.out.println("Xmx=".concat(String.valueOf(javaConfig.getXmx())));
        System.out.println("java.library.path=".concat(String.valueOf(javaConfig.getJava_library_path())));
        System.out.println("classpath=".concat(String.valueOf(javaConfig.getClasspath())));
        System.out.println("session=".concat(String.valueOf(gameConfig.getSession())));
        System.out.println("username=".concat(String.valueOf(gameConfig.getUserName())));
        System.out.println("gameDir=".concat(String.valueOf(gameConfig.getGameDir())));
        System.out.println("assetsDir=".concat(String.valueOf(gameConfig.getAssetsDir())));
        System.out.println("version=".concat(String.valueOf(gameConfig.getVersion())));

        System.out.println("------------------------------------------");
    }


    public static void RunGame(){
        Thread th = new Thread( new GameExecuter(javaConfig, gameConfig) );
        th.start();

        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static JavaConfig javaConfig = new JavaConfig();
    public static GameConfig gameConfig = new GameConfig();
    private static IPathDecode pathDecode;
}
