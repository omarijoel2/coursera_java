
/**
 * Write a description of MaximumTemperature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class MaximumTemperature {
    public CSVRecord hottestHourInFile(CSVParser parser){
    //start with largestsofar as nothing
    CSVRecord largestSoFar=null;
    //for each row(currentrow) in csv file
    for (CSVRecord currentRow: parser){
        //if largestsofar is nothing
       largestSoFar= getLargestOfTwo(currentRow, largestSoFar);
    }
   
    //return the largestsofar as the answer
   
     
   return largestSoFar; 
}
public CSVRecord coldestHourInFile(CSVParser parser){
//start with coldestSoFar as nothing
    CSVRecord coldestSoFar =null;
    //for each row(current row ) in csv file
    for (CSVRecord currentRow: parser){
    //if coldestsoFar is nothing
    coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
    
    }


return coldestSoFar;
}
 public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar){
     if (coldestSoFar==null){
        //update largestSoFar to be currentRow
        coldestSoFar=currentRow;
    }else{
       //otherwise
       double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
       //check if currentRow's temperature > largestsofar's
       double coldestTemp=Double.parseDouble(coldestSoFar.get("TemperatureF"));
       if (currentTemp<coldestTemp){
       //if so update largestsofar to currentRow}
       coldestSoFar= currentRow;
    }
    

}
return coldestSoFar;
}

public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar){
     if (largestSoFar==null){
        //update largestSoFar to be currentRow
        largestSoFar=currentRow;
    }else{
       //otherwise
       double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
       //check if currentRow's temperature > largestsofar's
       double largestTemp=Double.parseDouble(largestSoFar.get("TemperatureF"));
       if (currentTemp>largestTemp){
       //if so update largestsofar to currentRow}
       largestSoFar= currentRow;
    }
    

}
return largestSoFar;
}
 public void testHottestInDay(){
    FileResource fr=new FileResource();
    CSVRecord largest= hottestHourInFile(fr.getCSVParser());
    System.out.println("Hottest Temperature was" + largest.get("TemperatureF")+
    "at" + largest.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays(){
    //iterate over files
    CSVRecord largestSoFar=null;
    DirectoryResource dr = new DirectoryResource();
    
    for (File f: dr.selectedFiles()){
        FileResource fr= new FileResource(f);
    
    //use method to get largest in file
    CSVRecord currentRow= hottestHourInFile(fr.getCSVParser());
   
    // the largestSoFar is the answer
    largestSoFar= getLargestOfTwo(currentRow, largestSoFar);
    }
    return largestSoFar;
}
 public static CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord resultRecord = null;

        for (CSVRecord record : parser) {

            if (record.get("Humidity").equals("N/A")) {
                continue;
            }

            if (resultRecord == null) {
                resultRecord = record;
            } else {

                double temperatureF = Double.parseDouble(record.get("Humidity"));
                double coldestTemp = Double.parseDouble(resultRecord.get("Humidity"));
                resultRecord = (temperatureF < coldestTemp) ? record : resultRecord;

            }

        }

        return resultRecord;
    }
public void testColdestHourInFile(){
                //
        //get the file
        FileResource file = new FileResource();
        
        //get the CSV table
        CSVParser parse = file.getCSVParser();
        
        
        CSVRecord coldestR = coldestHourInFile(parse);
        
        System.out.println("The coldest hour is: " + coldestR.get("TimeEDT") + ", " + coldestR.get("TemperatureF"));
        
    }
    //Part 3: This method should return a string that is the name of the file from selected files that has the coldest temperature. 
    //You should also write a void method named testFileWithColdestTemperature() to test this method. 
    //Note that after determining the filename, you could call the method coldestHourInFile to determine the coldest temperature on that day.
    public void fileWithColdestTemperature (){
        
        
        //select the CSV file
        DirectoryResource dr = new DirectoryResource();

        CSVRecord codestR = null;
        File codestF = null;
        
        for( File f : dr.selectedFiles()){
            
            
        //  System.out.println("The codest temperature in file: " + f.getName());
            
            FileResource fr = new FileResource(f);
            CSVParser parse = fr.getCSVParser();
            
            CSVRecord currRecord = coldestHourInFile(parse);
            
            if(codestR == null || Double.parseDouble( currRecord.get("TemperatureF") ) < Double.parseDouble( codestR.get("TemperatureF") )){
                
                codestR = currRecord;
                codestF = f;
            }
            
        }//end for loop;
        
        
        //Printout the name of the File contains the codes temperature, and the temperature.
        System.out.println("\n The codest hour is in File: " + codestF.getName() + ". \nThe temperature is: " + codestR.get("TemperatureF"));
        
        
        //Printout all the temperatures in that file:
        System.out.println("\nAll the Temperatures on the coldest day were: ");
        
        FileResource fr = new FileResource(codestF);
        CSVParser parse = fr.getCSVParser();
        
        for(CSVRecord R : parse){
            
            System.out.println(codestF.getName() + " " + R.get("TimeEST") + "  " + R.get("TemperatureF") );
        }
        
        //String file_name = f;
        //get the CSV data table
        //
        
        
}//end fileWithColdestTemperature() method;
    
    
    public static double averageTemperatureInFile(CSVParser parser) {

        double averageTemp = 0.0;
        double sum = 0;
        int counter = 0;
        for (CSVRecord record : parser) {


            double recordTemp = Double.parseDouble(record.get("TemperatureF"));

            if (recordTemp == -9999) {
                continue;
            } else {
                sum += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }


        }

        averageTemp = sum / counter;

        return averageTemp;
    }
    public void testaverageTemperatureInFile(){
        FileResource fr =new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg= averageTemperatureInFile(parser);
        System.out.println("average temp is"+avg);
    
    
    }
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, double level) {

        double averageTemp;
        double sum = 0;
        int counter = 0;

        for (CSVRecord record : parser) {

            double humidity = Double.parseDouble(record.get("Humidity"));

            if (humidity >= level) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }

        }

        averageTemp = sum / counter;

        return averageTemp;

    }

public void testHottestInManyDays(){
    CSVRecord largest=hottestInManyDays();
    System.out.println("Hottest DAY was" + largest.get("TemperatureF")+
    "at" + largest.get("DateUTC"));
    }
}

    





