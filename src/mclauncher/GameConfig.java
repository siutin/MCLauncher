package mclauncher;

// This class contains the game's command options

public class GameConfig{

    private String UserName;
    private String Session;
    private String GameDir;
    private String AssetsDir;
    private String Version;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public String getGameDir() {
        return GameDir;
    }

    public void setGameDir(String gameDir) {
        GameDir = gameDir;
    }

    public String getAssetsDir() {
        return AssetsDir;
    }

    public void setAssetsDir(String assetsDir) {
        AssetsDir = assetsDir;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
