
/**
 * Write a description of ExportCountries here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class ExportCountries {

    public void listExporters(CSVParser parser, String ExportOfInterest){
    
    //for each row in the csv file
    for (CSVRecord record: parser){
    //look at the exports column of that row
    String export= record.get("Exports");
    //check if it contains "exportofinterest"
    if (export.contains(ExportOfInterest)){
    //if so, write down the "country" from that row
    String country= record.get("Country");
    System.out.println(country);
    
        }
}

}
public void countryinfo(CSVParser parser, String Country){
//this method returns a string of information about  the countrry
 int exist = 0;
        for (CSVRecord record: parser ) {
            if (record.get("Country").equals(Country)) {
                System.out.println(record.get("Country") + ": "+ record.get("Exports") + ": " + record.get("Value (dollars)"));
                exist = 1;
            }
            
        }
        if (exist == 0) System.out.println("Not Found!");
        


}
public void listExporterTwoProducts(CSVParser parser, String exportitem1, String exportitem2) {
        for (CSVRecord record: parser ) {
            String str = record.get("Exports");
            if (str.contains(exportitem1) && str.contains(exportitem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void testlistExporterTwoProducts() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporterTwoProducts(parser, "cotton", "flowers");
    }
    public int listExporterProduct(CSVParser parser, String exportitem1) {
        int number = 0;
        for (CSVRecord record: parser ) {
            String str = record.get("Exports");

            if (str.contains(exportitem1)) {
                System.out.println(record.get("Country"));
                number = number + 1;
            }
        }
        return number;
        
    }
    
    public void testlistExporterProduct() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int number = listExporterProduct(parser, "cocoa");
        System.out.println(number);
    }
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser ) {
            String str = record.get("Value (dollars)");
            if (str.length() > amount.length()) {
                System.out.print(record.get("Country")+" ");
                System.out.println(str);
            }
        }
    }
    
    public void testbigExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
public void whoExportsCoffee(){
FileResource fr=new FileResource();
CSVParser parser=fr.getCSVParser();
listExporters(parser, "gold");
}
public void tester(){
    FileResource fr= new FileResource();
    CSVParser parser= fr.getCSVParser();
}
}