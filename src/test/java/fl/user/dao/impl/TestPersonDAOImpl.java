package fl.user.dao.impl;

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
import org.springframework.transaction.annotation.Transactional;

import fl.user.dao.PersonDAO;
import fl.user.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
@Transactional
public class TestPersonDAOImpl {

    @Resource(name = "personDAOImpl")
    private PersonDAO personDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCRUD() {
        personDAO.deleteAll();
        assertEquals(0, personDAO.count());

        Person person = new Person();
        person.setAccount("klg");
        person.setPassword("9718a3e3c33c6ab854775890d1b26947");
        person.setDateCreated(new Date());
        person.setEmail("klg0705@gmail.com");
        person.setIpCreated("127.0.0.1");
        person.setName("蝈蝈");
        person.setSex("男");

        personDAO.create(person);

        assertEquals(1, personDAO.count());

        Person person2 = new Person();
        person2.setAccount("admin");
        person2.setPassword("21232f297a57a5a743894a0e4a801fc3");
        personDAO.create(person2);

        assertEquals(2, personDAO.count());

        Person p = personDAO.get(person.getId());
        assertEquals("klg", p.getAccount());
        assertEquals("9718a3e3c33c6ab854775890d1b26947", p.getPassword());
        assertEquals("蝈蝈", p.getName());

        List<Person> list = personDAO.getAll();
        assertEquals(2, list.size());

        // test findPersonByAccount
        Person found = personDAO.findPersonByAccount("klg");
        assertEquals("klg", found.getAccount());

        found = personDAO.findPersonByAccount("kknd");
        assertNull(found);

        p.setName("Gordon");
        personDAO.update(p);
        assertEquals("klg", p.getAccount());
        assertEquals("9718a3e3c33c6ab854775890d1b26947", p.getPassword());
        assertEquals("Gordon", p.getName());

        personDAO.delete(p);
        assertEquals(1, personDAO.count());

        personDAO.deleteById(person2.getId());
        assertEquals(0, personDAO.count());

    }

}
