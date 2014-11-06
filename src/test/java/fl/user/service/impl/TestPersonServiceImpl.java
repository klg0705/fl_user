package fl.user.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fl.user.domain.Person;
import fl.user.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class TestPersonServiceImpl {

    @Resource(name = "personServiceImpl")
    private PersonService personService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCRUD() {

        personService.deleteAll();
        assertEquals(0, personService.count());

        Person person = new Person();
        person.setAccount("klg");
        person.setPassword("klg");
        person.setDateCreated(new Date());
        person.setEmail("klg0705@gmail.com");
        person.setIpCreated("127.0.0.1");
        person.setName("蝈蝈");
        person.setSex("男");

        personService.create(person);

        assertEquals(1, personService.count());

        Person person2 = new Person();
        person2.setAccount("admin");
        person2.setPassword("admin");
        personService.create(person2);

        assertEquals(2, personService.count());

        Person p = personService.get(person.getId());
        assertEquals("klg", p.getAccount());
        assertEquals("9718a3e3c33c6ab854775890d1b26947", p.getPassword());
        assertEquals("蝈蝈", p.getName());

        List<Person> list = personService.getAll();
        assertEquals(2, list.size());

        // test findPersonByAccount
        Person found = personService.findPersonByAccount("klg");
        assertEquals("klg", found.getAccount());

        found = personService.findPersonByAccount("kknd");
        assertNull(found);

        // test getPerson
        found = personService.getPerson("klg", "klg");
        assertEquals("klg", found.getAccount());

        found = personService.getPerson("kknd", "kknd");
        assertNull(found);

        found = personService.getPerson("klg", "kknd");
        assertNull(found);

        p.setName("Gordon");
        personService.update(p);
        assertEquals("klg", p.getAccount());
        assertEquals("9718a3e3c33c6ab854775890d1b26947", p.getPassword());
        assertEquals("Gordon", p.getName());

        personService.delete(p);
        assertEquals(1, personService.count());

        personService.deleteById(person2.getId());
        assertEquals(0, personService.count());

    }

}
