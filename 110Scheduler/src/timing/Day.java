package timing;

import java.util.ArrayList;


public class Day {
  
  public enum Days{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
  }

  private String _name;
  
  private ArrayList<Hour> _hours;
  
  public Day(String name){
    _name = name;
    _hours = new ArrayList<Hour>();
  }
  
  public void addHour(Hour hour){
    _hours.add(hour);
  }
  
  public String getName(){
    return _name;
  }
  
  public Hour getHour(int index){
    return _hours.get(index);
  }
  
  public ArrayList<Hour> getHours(){
    return _hours;
  }
  
  public String toString(){
    String s = _name.toString() + "\n";
    
    for (int i = 0; i < _hours.size(); i++){
      s += _hours.get(i).toString() + "\n";
    }
    
    return s;
  }
  
}
