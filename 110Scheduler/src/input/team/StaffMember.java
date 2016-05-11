package input.team;

import java.util.ArrayList;

import timing.Day;
import timing.Hour;

//SM=Staff Member
public abstract class StaffMember {
  
  private static int idCounter = 0;
  
  protected static final int TA_DESIRED_HOURS = 20;
  
  protected static final int LA_DESIRED_HOURS = 19;
  
  protected final int ID;

  protected String _name;
  
  protected int _desiredTotalHours;
  
  protected Preference _preference;
  
  protected ArrayList<Day> _availableTimes;
  
  protected ArrayList<Day> _assignedTimes;
  
  private int _hours; //hours assigned
  
  public StaffMember(String name, Preference preference){
    _name = name;
    
    _preference = preference;
    
    ID = idCounter;
    idCounter++;
    
    _hours = 0;
    
    _availableTimes = new ArrayList<Day>();
    _availableTimes.add(new Day("Monday"));
    _availableTimes.add(new Day("Tuesday"));
    _availableTimes.add(new Day("Wednesday"));
    _availableTimes.add(new Day("Thursday"));
    _availableTimes.add(new Day("Friday"));
    
    _assignedTimes = new ArrayList<Day>();
    _assignedTimes.add(new Day("Monday"));
    _assignedTimes.add(new Day("Tuesday"));
    _assignedTimes.add(new Day("Wednesday"));
    _assignedTimes.add(new Day("Thursday"));
    _assignedTimes.add(new Day("Friday"));
    
    _assignedTimes = new ArrayList<Day>();
  }
  
  public StaffMember(String name){
    this(name, Preference.NONE);
  }
  
  public int getID(){
    return ID;
  }
  
  public String getName(){
    return _name;
  }
  
  public void addHours(int hours){
    _hours += hours;
  }
  
  public void addAvailableHours(String day, int lower, int upper){
    
    int index = getDaybyName(day);
    
    for (int i = lower; i != upper; i++){
      
      //special 12 case
      if (i == 12){
        _availableTimes.get(index).addHour(new Hour(12, 1));
        i = 0;
        continue;
      }
      
      _availableTimes.get(index).addHour(new Hour(i, i + 1));
    }
  }

  private int getDaybyName(String day) {
    for (int i = 0; i < _availableTimes.size(); i++){
      if (_availableTimes.get(i).getName().equals(day)) return i;
    }
    return 0;
  }
  
  public boolean isFull(){
    return _hours > _desiredTotalHours;
  }

  public boolean isAvailable(Day day, Hour hour) {
    
    for (Day aDay : _availableTimes){
      for (Hour aHour : aDay.getHours()){
        if (aDay.getName().equals(day.getName()) && aHour.getStart() == hour.getStart()) return true;
      }
    }
    
    return false;
    
  }
}
