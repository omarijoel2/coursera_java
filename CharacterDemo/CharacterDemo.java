
/**
 * Illustrate Character methods
 * 
 * @author Duke Software Team 
 * @version (a version number or a date)
 */
public class CharacterDemo {
    public void digitTest() {
        String test = "ABCabc0123456789';#!";
        for(int k=0; k < test.length(); k++){
            char ch = test.charAt(k);
            if (Character.isDigit(ch)){
               System.out.println(ch+" is a digit"); 
            }
            if (Character.isAlphabetic(ch)){
                System.out.println(ch+" is alphabetic");
            }
            if (ch == '#'){
                System.out.println(ch +" #hashtag");
            }
        }
    }
    public String reverse(String s){
    String ret=""; //initialize an empty string to hold the reversed string
    //initialize the index to zero
    //compare if the string to be reversed is greate thatn  k
    for (int k=0; k<s.length(); k +=1){
    //prepend the character at k to ret    
    ret = s.charAt(k) + ret;
    }
    
    return ret;
    
    }
    public void testreverse(){
    System.out.println(reverse("computer"));
    }
    public void conversionTest(){
        String test = "ABCDEFabcdef123!#";
        for(int k=0; k < test.length(); k++){
            char ch = test.charAt(k);
            char uch = Character.toUpperCase(ch);
            char lch = Character.toLowerCase(ch);
            System.out.println(ch+" "+uch+" "+lch);
        }
    }
}
