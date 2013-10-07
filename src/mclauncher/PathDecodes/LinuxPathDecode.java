package mclauncher.PathDecodes;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 7/10/13
 * Time: 4:42 PM
 */
public class LinuxPathDecode implements IPathDecode{
    private String _homeDir;
    public LinuxPathDecode(String homeDir){
        _homeDir = homeDir;
    }

    public String Apply(String value){

        return value.replaceAll("##AUTO#DIR##(/)*", _homeDir.concat("/.minecraft/"));
    }
}
