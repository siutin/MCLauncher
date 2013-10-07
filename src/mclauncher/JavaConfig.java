package mclauncher;


// This class contains the the java's command options

public class JavaConfig {

   public JavaConfig(){

   }

   private String java;

    public String getXms() {
        return Xms;
    }

    public void setXms(String xms) {
        Xms = xms;
    }

    public String getXmx() {
        return Xmx;
    }

    public void setXmx(String xmx) {
        Xmx = xmx;
    }

    public String getJava_library_path() {
        return java_library_path;
    }

    public void setJava_library_path(String java_library_path) {
        this.java_library_path = java_library_path;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getJava(){
        return java;
    }
    public void setJava(String java){
        this.java = java;
    }

    private String Xms;
    private String Xmx;
    private String java_library_path;
    private String classpath;

}

