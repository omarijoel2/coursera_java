
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        
        int currIndex = dna.indexOf(stopCodon, startIndex +3);
        while (currIndex !=1){
            int diff= currIndex-startIndex;
            if(diff %3==0){
                return currIndex;
            }else{
                currIndex =dna.indexOf(stopCodon,currIndex +1);
            }
        }
        return -1;
    }
    public void testFindStopCodon(){
    
    String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        
        int dex = findStopCodon(dna, 0,"TAA");
        //System.out.println(dex);
        if (dex !=9)System.out.println("error on 9");

        dex = findStopCodon(dna, 9,"TAA");
        if (dex !=21)System.out.println("error on 21");

        dex = findStopCodon(dna, 1,"TAA");
        if (dex !=26)System.out.println("error on 26");

        dex = findStopCodon(dna, 0,"TAG");
        if (dex !=26)System.out.println("error on 26");
        System.out.println("tests finished");

    }
    
    public String findGene(String dna) {
        //find the first occurrence of "ATG" call its index startIndex
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1) { //if startIndex is -1, then your answer is the empty string
            return "";
        }
        //findstopcodon (dnaStr, startIndex, "TAA") and call the result taaIndex
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        //findStopCodon(dnaStr, startIndex, "TAG") and call the resul tagIndex
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        //findStopCodon(dnaStr, startIndex, "TGA") and call the resul tgaIndex
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //you express AND with &&
        //you can express OR with ||
        int minIndex = 0;
        if(taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }

        if(minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }

        if(minIndex == -1) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }
    
   
    

    public void testFindGene() {
        String one = "AATGCTAACTAGCTGACTAAT";
        String two = "xxxATGxxxyyyxxTAGxTAAxxx";
        String three = "xyyATGxxxyyyuuuTGAxxxTAGxxx";
        String four = "xyyATGxxxyyxxxyuuuTGAxxxTAGxxx";

        System.out.println("Gene is: " + one + " " + findGene(one));
        System.out.println("Gene is: " + two + " " + findGene(two));
        System.out.println("Gene is: " + three + " " + findGene(three));
        System.out.println("Gene is: " + four + " " + findGene(four));
    }
    //set startindex to 0
    //repeat the following steps
         //find the next gene after the startIndex
         //if no gene was found, leave this loop
         //print that gene out
         //set startindex to just past the end of the gene
    

    public StorageResource getAllGenes(String dna) {
        //create an empty StorageResource, call it geneList
        StorageResource geneList= new StorageResource();
        //set startIndex to 0
        
        int startIndex =0;
        //repeat the following steps
        //find the next gene after startIndex
        
        while (true) {
            String Currentgene = findGene(dna, startIndex);
            if (Currentgene.isEmpty()) {
                break; //leave the current loop jump immediately past the } for it
            } 
            //Add that gene to geneList
            geneList.add(gene);
            //set startIndex to just past the end 
            startIndex= dna.indexOf(Currentgene, startIndex);
                        Currentgene.length();
        }
        return geneList;
    } 

    public static void main() {
        Part1 test = new Part1();
        test.testFindGene();
    }
}
