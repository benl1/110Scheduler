package input.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import input.team.LA;
import input.team.StaffMember;
import input.team.TA;
import output.data.ScheduledData;
import timing.Day;
import timing.Hour;

public class SchedulingData {
  
  private ArrayList<Day> _times;
  
  private ArrayList<StaffMember> _staff;
  
  public SchedulingData(File file){
    try {
      Scanner fileScanner = new Scanner(file);
      
      String names = fileScanner.nextLine();
      
      Scanner scanLine = new Scanner(names); //this scanner will read individual lines 
      scanLine.useDelimiter(",");
      
      //Get first and last hour times
      int firstHour = Integer.parseInt(scanLine.next());
      int lastHour = Integer.parseInt(scanLine.next());
      
      _times = new ArrayList<Day>();
      
      _times.add(new Day("Monday"));
      _times.add(new Day("Tuesday"));
      _times.add(new Day("Wednesday"));
      _times.add(new Day("Thursday"));
      _times.add(new Day("Friday"));
      
      for (Day day : _times){
        for (int i = firstHour; i != lastHour; i++){
         
          //special 12 case
          if (i == 12){
            day.addHour(new Hour(12, 1));
            i = 0;
            continue;
          }
          
          day.addHour(new Hour(i, i + 1));
        }
      }
      
      _staff = new ArrayList<StaffMember>();
      
      while (scanLine.hasNext()){
        String s = scanLine.next();
        
        Scanner nameScanner = new Scanner(s);
        
        String name = nameScanner.next();
        
        String status = nameScanner.next();
        
        if (status.equals("TA")){
          _staff.add(new TA(name));
        }else{
          _staff.add(new LA(name));
        }
      }
      
      int counter = 0;
      while (fileScanner.hasNextLine()){
       
        scanLine = new Scanner(fileScanner.nextLine());
        scanLine.useDelimiter(",");
        
        String day = scanLine.next();
        
        scanLine.next(); //gets rid of empty space in csv
        counter = 0;
        while (scanLine.hasNext()){
          
          String times = scanLine.next();
          Scanner spaceScanner = new Scanner(times);
          
          while(spaceScanner.hasNext()){
         
            String s = spaceScanner.next();
            int dash = s.indexOf('-');
            
            int lower = Integer.parseInt(s.substring(0, dash));
            int upper = Integer.parseInt(s.substring(dash + 1));
            
            _staff.get(counter).addAvailableHours(day, lower, upper); 
          }
          
          counter++;
        }
        
      }
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    ScheduledData scheduled = new ScheduledData(this);
  }

  public ArrayList<Day> getTimes() {
    return _times;
  }

  public ArrayList<StaffMember> getStaff() {
    return _staff;
  }
  
  //Returns null if there is an error
  public StaffMember getSMByID(int ID){
    for (StaffMember SM : _staff){
      if (SM.getID() == ID) return SM;
    }
    return null;
  }
  
  
  public static void main(String[] args){
    File file = new File("C:/Users/Benjamin/Desktop/Schedule.csv");
    SchedulingData data = new SchedulingData(file);
  }
  
}




