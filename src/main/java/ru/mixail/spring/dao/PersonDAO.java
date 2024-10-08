package ru.mixail.spring.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mixail.spring.models.Book;
import ru.mixail.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String fullName) {
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name,year_of_birth) VALUES (?,?)", person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?,year_of_birth=? WHERE id=?", updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Optional<Person> getPersonbyFulLName(String fullName){
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?",new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book Where person_id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }


    /////////////////////////////////
    ////// Тестируем производительность пакетной вставски
    /////////////////////////////////


//    public void testMultipleUpdate() {
//        List<Person> people = create1000People();
//
//        long before = System.currentTimeMillis();
//
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?)", person.getId(), person.getFIO(), person.getAge(),
//                    person.getEmail());
//        }
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time: " + (after - before));
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = create1000People();
//
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?, ?, ?, ?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        ps.setInt(1, people.get(i).getId());
//                        ps.setString(2, people.get(i).getFIO());
//                        ps.setInt(3, people.get(i).getAge());
//                        ps.setString(4, people.get(i).getEmail());
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return people.size();
//                    }
//                });
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time: " + (after - before));
//    }
//
//    private List<Person> create1000People() {
//        List<Person> people = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++)
//            people.add(new Person(i, "Name" + i, 30, "test" + i + "@mail.ru", "Some address"));
//
//        return people;
//    }

}
