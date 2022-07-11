package lv.alija.springcourseCrud.DAO;

import lv.alija.springcourseCrud.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
//Ispolzuem etot klass toljko esli nazvanija v klasse i v tablice ne sovpadajut
//public class PersonMapper implements RowMapper {
//
//    @Override
//    public Person mapRow(ResultSet resultSet, int i) throws SQLException{
//        Person person = new Person();
//
//        person.setId(resultSet.getInt("id"));
//        person.setEmail(resultSet.getString("email"));
//        person.setName(resultSet.getString("name"));
//        person.setAge(resultSet.getInt("age"));
//
//        return person;
//
//
//    }
//}
