package timing;

import java.util.ArrayList;

import input.team.StaffMember;

public class Hour {
  
  //Start of the hour
  protected int _start; 
  
  //End of the hour
  protected int _end;
  
  private int _desiredStaff;
  
  private ArrayList<StaffMember> _staff;
  
  private int _staffWorking = 0;
  
  
  public Hour(int start, int end){
    _start = start; 
    _end = end;
    
    _desiredStaff = 4;
    
    _staff = new ArrayList<StaffMember>();
  }
  
  public int getStaffWorking(){
    return _staffWorking;
  }
  
  public int getDesiredStaff(){
    return _desiredStaff;
  }

  public int getStart() {
    return _start;
  }

  public int getEnd() {
    return _end;
  }
  
  public boolean isRegistered(StaffMember SM){
    for (StaffMember staffMember : _staff){
      if (SM == staffMember) return true;
    }
    
    return false;
  }
  
  public void addSM(StaffMember SM){
    _staff.add(SM);
    
    SM.addHours(1);
    
    _staffWorking++;
  }
  
  public String toString(){
    String s = _start + " - " + _end + ": ";
    
    for (int i = 0; i < _staff.size(); i++){
      s += _staff.get(i).getName() + ", ";
    }
    s += "\n";
    return s;
  }
  
}
