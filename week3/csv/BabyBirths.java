
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class BabyBirths {
    public void printNames(){
    
    FileResource fr= new FileResource();
    for (CSVRecord record: fr.getCSVParser(false)){
        int numBorn= Integer.parseInt(record.get(2));
        if (numBorn <=100){System.out.println("Name "+ record.get(0) + 
        "Gender " + record.get(1) + 
        "Num Born "+ record.get(2));
    }
}
    }
    public void totalBirths(FileResource fr){
        int totalBirths=0;
        for (CSVRecord record: fr.getCSVParser(false)){
        int numBorn= Integer.parseInt(record.get(2));
        totalBirths +=numBorn;
    }
    System.out.println("Total Births = "+ totalBirths);
    }
    public void testTotalBirths(){
    FileResource fr = new FileResource();
    totalBirths(fr);
    }

}
