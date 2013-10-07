package mclauncher;

public class ENVHelper{

    //Detect OS
    public static OSs OS(String value){
        value = value.toLowerCase();
        if(value.contains("win"))
            return OSs.WIN;
        else if(value.contains("linux"))
            return OSs.LINUX;
        else if(value.contains("mac"))
            return OSs.MAC;
        else return OSs.UNKNOWN;
    }
}
