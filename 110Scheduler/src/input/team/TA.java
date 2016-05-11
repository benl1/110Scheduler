package input.team;

/**
 * This class represents an TA who has not yet been assigned office hours.
 */
public class TA extends StaffMember {

  public TA(String name, Preference preference) {
    super(name, preference);
    _desiredTotalHours = TA_DESIRED_HOURS;
  }
  
  public TA(String name){
    this(name, Preference.NONE);
  }

}
