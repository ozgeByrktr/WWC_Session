package stepdefinitions;

import helperDB.CommonData;
import io.cucumber.java.en.Given;
import manage.Manage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static helperDB.CommonData.*;
import static helperDB.JDBC_Structure_Methods.*;
import static org.junit.Assert.*;

public class DB_Stepdefinitions extends Manage {
    // DB
    CommonData data = new CommonData();

    @Given("Database connection established")
    public void database_connection_established() {
        createConnection();
    }

    @Given("Database connection is closed")
    public void database_connection_is_closed() {
        closeConnection();
    }

    @Given("User_chats table query prepared.")
    public void user_chats_table_query_prepared() throws SQLException {

    }

    @Given("Query results {int} are validated.")
    public void query_results_are_validated(int expected) throws SQLException {

    }

    @Given("Query is prepared where class_id and section_id are equal in the class_sections table")
    public void query_is_prepared_where_class_id_and_section_id_are_equal_in_the_class_sections_table() throws SQLException {
        query=getUS02_class_sections();
        resultSet=getStatement().executeQuery(query);
    }

    @Given("Query results lists are validated.")
    public void query_results_lists_are_validated() throws SQLException {
        sectionId=new ArrayList<>();
        while (resultSet.next()) {
            sectionId.add(resultSet.getInt("id"));
        }
        if(!sectionId.isEmpty()){
            for (int i = 0; i < sectionId.size() ; i++) {
                assertEquals(sectionId.get(i),expectedSectionId.get(i));
                System.out.println(sectionId.get(i) + " ");
            }
        }else{
            assertFalse("Result set is Empty", resultSet.next());
        }
    }

    @Given("Query is prepared  in the students table")
    public void query_is_prepared_in_the_students_table() throws SQLException {

    }

    @Given("Query result {string} are validated.")
    public void query_result_are_validated(String expectedMail) throws SQLException {

    }

    @Given("Query is prepared  in the students table with admission numbers")
    public void query_is_prepared_in_the_students_table_with_admission_numbers() throws SQLException {

    }

    @Given("Query results lists the firstname and lastname are validated.")
    public void query_results_lists_the_firstname_and_lastname_are_validated() throws SQLException {

    }

    @Given("Query is prepared  in the students table whose lastname starts with {string}")
    public void query_is_prepared_in_the_students_table_whose_lastname_starts_with(String string) throws SQLException {

    }

    @Given("Query results lists the mother_name and mother_occupation.")
    public void query_results_lists_the_mother_name_and_mother_occupation() throws SQLException {

    }

    @Given("Query is prepared  in the students table according to the father_occupation value")
    public void query_is_prepared_in_the_students_table_according_to_the_father_occupation_value() throws SQLException {

    }

    @Given("Query results lists the roll_no information are validated")
    public void query_results_lists_the_roll_no_information_are_validated() throws SQLException {


    }

    @Given("Query is prepared  in the users table role=parent")
    public void query_is_prepared_in_the_users_table_role_parent() throws SQLException {

    }

    @Given("List query results roll information")
    public void list_query_results_roll_information() throws SQLException {

    }

    @Given("Insert a record into the topic table")
    public void insert_a_record_into_the_topic_table() throws SQLException {

    }

    @Given("retrieve the inserted record's ID")
    public void retrieve_the_inserted_record_s_id() throws SQLException {
    }


    @Given("update the record's name using the retrieved ID")
    public void update_the_record_s_name_using_the_retrieved_id() throws SQLException {

    }

    @Given("Verify the data information Result is obtained.")
    public void verify_the_data_information_result_is_obtained() {

    }

    @Given("Insert a new record into the transport_route table")
    public void insert_a_new_record_into_the_transport_route_table() throws SQLException {


    }

    @Given("Insert a new record into the visitors_book table")
    public void insert_a_new_record_into_the_visitors_book_table() throws SQLException {

    }

    @Given("Delete the record from the visitors_book table using the retrieved ID")
    public void delete_the_record_from_the_visitors_book_table_using_the_retrieved_id() throws SQLException {

    }

    @Given("Update fine_amount to '200.00' for records where month is 'October'")
    public void update_fine_amount_to_200_for_records_where_month_is_october() throws SQLException {

        // Parametreleri ayarla

    }

    @Given("Query the first 5 staff members by work_exp in ascending order")
    public void query_the_first_5_staff_members_by_work_exp_in_ascending_order() throws SQLException {

    }

    @Given("Verify the staff members are listed correctly")
    public void verify_the_staff_members_are_listed_correctly() {

    }


    @Given("query prepared for email addresses where firstname contains 'al'")
    public void query_prepared_for_email_addresses_where_firstname_contains_al() throws SQLException {


    }

    @Given("should be see the list of email addresses with 'al' in the firstname")
    public void should_be_see_the_list_of_email_addresses_with_al_in_the_firstname() throws SQLException {


    }
}

