package mclauncher.PathDecodes;

import mclauncher.ENVHelper;
import mclauncher.OSs;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 7/10/13
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class PathDecodeFactory {

    public static IPathDecode create(String osName, String homeDir){
        OSs os = ENVHelper.OS(osName);

        if(os == OSs.MAC){
            return new MacPathDecode(homeDir);
        }else if (os == OSs.LINUX)
            return new LinuxPathDecode(homeDir);
        return null;
    }
}
