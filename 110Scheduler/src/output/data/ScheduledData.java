package output.data;

import java.util.ArrayList;
import java.util.Random;

import input.data.SchedulingData;
import input.team.StaffMember;
import timing.Day;
import timing.Hour;

/**
 * This class takes in a SchedulingData object and transforms it into a ScheduledData object.
 * @author Benjamin
 * 
 */
public class ScheduledData {
  
  private SchedulingData _data;
  
  private ArrayList<StaffMember> _staff;
  
  private ArrayList<Day> _schedule;
  
  public ScheduledData(SchedulingData data){
    _data = data;
    
    _staff = _data.getStaff();
    
    _schedule = _data.getTimes();
    
    scheduleData();
    
  }
  
 private void scheduleData(){
   Random random = new Random();
   
   int randomID = 0;
   
   int attempt = 0;
   
   for (int i = 0; i < _schedule.size(); i++){
     for (int j = 0; j < _schedule.get(i).getHours().size(); j++){
       
       attempt = 0;
       
       while(_schedule.get(i).getHour(j).getStaffWorking() < _schedule.get(i).getHour(j).getDesiredStaff()){
         
       if (_staff.size() == 0) break;
       
       randomID = random.nextInt(_staff.size());
       
       if (!_data.getStaff().get(randomID).isAvailable(_schedule.get(i), _schedule.get(i).getHour(j))){
         attempt++;
         
         
         if (attempt > 20){
           System.out.println("Hour on " + _schedule.get(i).getName() + ": " + _schedule.get(i).getHour(j).getStart() + 
               " cannot be filled");
           
           break;
         }
         
         continue;
       }
    
       //check to see if staff member is already assigned to this hour
       if (_schedule.get(i).getHour(j).isRegistered(_data.getStaff().get(randomID))){
         attempt++;
         
         if (attempt > 20) {
           System.out.println("Hour on " + _schedule.get(i).getName() + ": " + _schedule.get(i).getHour(j).getStart() + 
               " cannot be filled");
           
           break;
         }
         
         continue;
       }
       
       _schedule.get(i).getHour(j).addSM(_data.getStaff().get(randomID));
       attempt = 0;
       
       if (_data.getStaff().get(randomID).isFull()){
         _staff.remove(randomID);
       }
       
       }
     }
   }
   
   for (Day day : _schedule){
     System.out.println(day);
   }
   
 }
 
  
  
}
