package helperDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static base.BaseTest.faker;

public class subjects {
    public List<Map<String,Object>> generateFakeSubjects(int count){

        List<Map<String,Object>> subjects=new ArrayList<>();
        for (int i = 0; i <count ; i++) {
            Map<String,Object> subject=new HashMap<>();
            //name, code, type, is_active, created_at, updated_at
            subject.put("name",faker.book().title());
            subject.put("code",faker.code().isbn10());
            subject.put("type",faker.options().option("General","Special","Elective"));
            subject.put("is_active",faker.bool().bool());
            subject.put("created_at",new Timestamp(System.currentTimeMillis()));
            subject.put("updated_at",new Timestamp(System.currentTimeMillis()));

            subjects.add(subject);

        }
        return subjects;
    }
    public  void insertSubjects(Connection connection, List<Map<String,Object>>subjects){
        String query="INSERT INTO subjects (name, code, type, is_active, created_at, updated_at) Values (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            for (Map<String,Object> subject : subjects){
                preparedStatement.setString(1, (String) subject.get("name"));
                preparedStatement.setString(2, (String) subject.get("code"));
                preparedStatement.setString(3, (String) subject.get("type"));
                preparedStatement.setBoolean(4, (Boolean) subject.get("is_active"));
                preparedStatement.setTimestamp(5, (Timestamp) subject.get("created_at"));
                preparedStatement.setTimestamp(6, (Timestamp) subject.get("updated_at"));
                preparedStatement.addBatch();

            }
            CommonData.bulkResult=preparedStatement.executeBatch();
            System.out.println(subjects.size()+"Subjects successfully inserted into the database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
