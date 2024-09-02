package ru.mixail.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mixail.spring.dao.PersonDAO;
import ru.mixail.spring.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        //Посмотреть есть ли человек с таким же FIO
        if (personDAO.show(person.getFullName()).isPresent())
            errors.rejectValue("fio","", "This FIO is already taken");

    }
}
