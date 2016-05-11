package input.team;

/**
 * This class represents an LA who has not yet been assigned office hours.
 */
public class LA extends StaffMember {
  
  public LA(String name, Preference preference) {
    super(name, preference);
    _desiredTotalHours = LA_DESIRED_HOURS;
  }
  
  public LA(String name){
    this(name, Preference.NONE);
  }

}
