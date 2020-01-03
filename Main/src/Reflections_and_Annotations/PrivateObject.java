package Reflections_and_Annotations;

public class PrivateObject {
    private String privateString = null;

    public PrivateObject(String privateString) {
        this.privateString = privateString;
    }

    private String getPrivateString(){
        return this.privateString;
    }
}

