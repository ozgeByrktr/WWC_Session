package helperDB;


import lombok.Getter;

import java.util.*;

@Getter
public class CommonData {
    public static List<Integer> sectionId;
    public static List<Integer> expectedSectionId=new ArrayList<Integer>(Arrays.asList(1,5));
    public static int insertedId;
    public static HashMap<String,String> studentLast_FirstName;
    public static HashMap<String,String> expstudentLast_FirstName;
    public static int [] bulkResult;


    public CommonData() {
    expstudentLast_FirstName=new HashMap<>();
    expstudentLast_FirstName.put("Fleming","Nicolas");
    expstudentLast_FirstName.put("Stark","Glen");
    expstudentLast_FirstName.put("Peterson","Simon");
    expstudentLast_FirstName.put("Kohlar","Brian");
    expstudentLast_FirstName.put("Clinton","Laura");
    expstudentLast_FirstName.put("Heart","David");
    expstudentLast_FirstName.put("Roy","Kavya");

    }

    public static HashMap<String, String> getExpstudentLast_FirstName() {
        return expstudentLast_FirstName;
    }
    /**  # lastname	firstname
    Fleming   	Nicolas
    Stark	   Glen
    Peterson	Simon
    Kohlar   	Brian
    Clinton 	Laura
    Heart	 David
    Roy       Kavya

    **/

}
