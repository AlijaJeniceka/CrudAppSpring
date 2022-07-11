package lv.alija.springcourseCrud.DAO;

import lv.alija.springcourseCrud.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int PEOPLE_COUNT;

    public List<Person>index(){
        return jdbcTemplate.query("SELECT * FROM Person",
                //Tak kak stroki tablici sovpadajut s nazvaniem peremennih klassa person mozhem ispoljzovatj
                //uzhe sozdannoj RowMapper.
                new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        //massiv iz znachenij
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                //Lambda
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)",
                person.getName(),
                person.getAge(),
                person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

//    private static final String URL = "jdbc:mySQL://localhost:3306/firstdb";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "juldaweva.91";

//    private static Connection connection;
//
//    static{
//        try{
//            Class.forName("mysql-connector-java");
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }
//        try{connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//    }
//        catch (SQLException throwables){
//            throwables.printStackTrace();
//        }}
//
//    public List<Person>index(){
//        List<Person> people = new ArrayList<>();
//        try{
//            Statement statement = connection.createStatement();
//            String SQL="SELECT *FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while(resultSet.next()){
//                Person person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setEmail(resultSet.getString("email"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//
//            people.add(person);
//
//            }
//    } catch (SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return people;
//    }
//
//    public void save(Person person) {
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public Person show(int id){
//        Person person = null;
//
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");
//            preparedStatement.setInt(1, id);
//           ResultSet resultSet =  preparedStatement.executeQuery();
//           resultSet.next();
//           person = new Person();
//           person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setAge(resultSet.getInt("age"));
//            person.setEmail(resultSet.getString("email"));
//
//        }
//        catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//             return person;
//    }


  //  private List<Person> people;


//    {
//       people = new ArrayList<>();
//       people.add(new Person(++PEOPLE_COUNT, "Tom", 25, "tom@gmail.com"));
//       people.add(new Person(++PEOPLE_COUNT, "Bob", 30, "bob2gmail.com"));
//       people.add(new Person(++PEOPLE_COUNT, "Santa", 23, "santusik@inbox.lv"));
//       people.add(new Person(++PEOPLE_COUNT, "Keil", 45, "keil.gustavson@gmail.com"));
//
//    }

//    public List<Person> index(){
//        return people;
//    }

  //  public Person show(int id){
    //    return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
   // }

//    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
//    }
//    public void update(int id, Person updatedPerson){
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
//    }

//    public void delete(int id){
//        people.removeIf(p -> p.getId() == id);
//    }
}
