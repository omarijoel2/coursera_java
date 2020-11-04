
/**
 * Write a description of ColdestWeather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/***
 *
 * @author matnod
 */
public class ColdestWeather {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        //For each row in (currentRow) in CSV file
        for (CSVRecord currentRow : parser) { 
            //If this is the first row, then this is coldest
            coldestSoFar = checkColdest(currentRow, coldestSoFar);
        }
        //Record with the coldest temperature    
        return coldestSoFar;
    }

    public CSVRecord checkColdest(CSVRecord currentRow, CSVRecord coldestSoFar) {
        if (coldestSoFar == null) {
            coldestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            //Check if currentTemp < coldestTemp
            if (currentTemp < coldestTemp && currentTemp > -9000) {
                //Update coldesSoFar to currentRow
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }

    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String ColdestTempFile = "";
        DirectoryResource dr = new DirectoryResource();
        //Iterate over all the files
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            //call coldestHourInFile to get the file with lowest temp
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                //Check if currentTemp < coldestTemp
                if (currentTemp < coldestTemp && currentTemp > -9000) {
                    //Update coldesSoFar to currentRow
                    coldestSoFar = currentRow;
                    ColdestTempFile = file.getAbsolutePath();
                }
            }

        }

        return ColdestTempFile;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println(
                "Coldest temperature was: " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
    }

    public void testFileWithColdestTemperature() {
        String ColdestTempFile = fileWithColdestTemperature();
        //Path needs to absolute but only the filename is printed.
        System.out.println(
                "Coldest day was in the file " + ColdestTempFile.substring(ColdestTempFile.lastIndexOf('/') + 1));
        FileResource fr = new FileResource(ColdestTempFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        FileResource fr2 = new FileResource(ColdestTempFile);
        CSVParser parser2 = fr2.getCSVParser();
        for (CSVRecord row : parser2) {
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
    }

    public CSVRecord checkLowestHumidity(CSVRecord currentRow, CSVRecord lowestSoFar) {
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        } else {
            if (!currentRow.get("Humidity").contains("N/A")) {
                int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
                if (currentHumidity < lowestHumidity) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }

    public String lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        String lowestHumFile = "";
        DirectoryResource dr = new DirectoryResource();
        //Iterate over all the files
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            //call lowestHumidityInFile to get the file with lowest Humidity
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                if (!currentRow.get("Humidity").contains("N/A")) {
                    int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                    int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
                    if (currentHumidity < lowestHumidity) {
                        lowestSoFar = currentRow;
                        lowestHumFile = file.getAbsolutePath();
                    }
                }
            }

        }

        return lowestHumFile;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        //For each row in (currentRow) in CSV file
        for (CSVRecord currentRow : parser) {
            //If this is the first row, then this is coldest
            lowestSoFar = checkLowestHumidity(currentRow, lowestSoFar);
        }
        //Record with the coldest temperature    
        return lowestSoFar;
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemp = 0;
        int numDays = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            averageTemp += currentTemp;
            numDays += 1;
        }
        return averageTemp / numDays;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int humidity) {
        double averageTemp = 0;
        int numDays = 0;
        for (CSVRecord currentRow : parser) {
            if (currentRow.get("Humidity") != "N/A") {
                int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                if (currentHumidity >= humidity) {
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                    averageTemp += currentTemp;
                    numDays += 1;
                }
            }
        }
        if (numDays == 0) {
            System.out.println("No temperatures with that humidity");
        }
        return averageTemp / numDays;
    }

    public void testaverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int humidity = 80;
        System.out.println(
                "Average Temp when high Humidity is " + averageTemperatureWithHighHumidityInFile(parser, humidity));
    }

    public void testaverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }

    public void testLowestHumidityInManyFiles() {
        String lowestHumidityFile = lowestHumidityInManyFiles();
        FileResource fr = new FileResource(lowestHumidityFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public static void main(String[] args) {
        ColdestWeather cw = new ColdestWeather();
        //cw.testColdestHourInFile();
        //cw.testFileWithColdestTemperature();
        //cw.testLowestHumidityInFile();
        cw.testLowestHumidityInManyFiles();
        //cw.testaverageTemperatureInFile();
        //cw.testaverageTemperatureWithHighHumidityInFile();
    }
}
