package mclauncher.PathDecodes;

public class MacPathDecode implements IPathDecode{

    private String _homeDir;
    public MacPathDecode(String homeDir){
        _homeDir = homeDir;
    }

    public String Apply(String value){

        return value.replaceAll("##AUTO#DIR##(/)*", _homeDir.concat("/Library/Application Support/minecraft/"));
    }
}
