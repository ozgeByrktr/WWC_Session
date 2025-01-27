package manage;

import lombok.Getter;

@Getter
public class Manage {

    /**
     * US_01
     **/
 private String US02_class_sections="Select id From class_sections WHERE class_id=section_id;";

 private String US09_inserted_transportRoute="Insert INTO transport_route (route_title,no_of_vehicle,note,is_active,created_at,updated_at)\n"+
         "VALUES (?,?,?,?,?,?)";
 private String US10_insertedVisitorsBook="INSERT INTO visitors_book(staff_id, student_session_id, source, purpose, name, email, contact, id_proof, no_of_people, date, in_time, out_time, note, image, meeting_with, created_at) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

 private String US10_deletedVisitorsBook="Delete From visitors_book WHERE id= ?";






}
