package utilities;

public class TestUtil {

    public static String removeBrackets(String getText){

        getText = getText.replaceAll("\\[", "").replaceAll("\\]", "");
        return getText;
    }
}
