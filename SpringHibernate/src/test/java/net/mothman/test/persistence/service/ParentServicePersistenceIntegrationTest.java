package net.mothman.test.persistence.service;

import net.mothman.persistence.model.Child;
import net.mothman.persistence.model.Parent;
import net.mothman.persistence.service.ChildService;
import net.mothman.persistence.service.ParentService;
import net.mothman.spring.PersistenceConfig;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ParentServicePersistenceIntegrationTest {

    @Autowired
    private ParentService service;

    @Autowired
    private ChildService childService;

    @Autowired
    private SessionFactory sessionFactory;

    // tests

    @Test
    public final void whenContextIsBootstrapped_thenNoExceptions() {
        //
    }

    @Test
    public final void whenOneToOneEntitiesAreCreated_thenNoExceptions() {
        final Child childEntity = new Child();
        childService.insert(childEntity);

        final Parent parentEntity = new Parent(childEntity);
        service.insert(parentEntity);

        System.out.println("Child = " + childService.get(childEntity.getId()));
        System.out.println("Child - parent = " + childService.get(childEntity.getId()).getParent());

        System.out.println("Parent = " + service.get(parentEntity.getId()));
        System.out.println("Parent - child = " + service.get(parentEntity.getId()).getChild());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public final void whenChildIsDeletedWhileParentStillHasForeignKeyToIt_thenDataException() {
        final Child childEntity = new Child();
        childService.insert(childEntity);

        final Parent parentEntity = new Parent(childEntity);
        service.insert(parentEntity);

        childService.delete(childEntity);
    }

//    @Test
    public final void whenChildIsDeletedAfterTheParent_thenNoExceptions() {
        final Child childEntity = new Child();
        childService.insert(childEntity);

        final Parent parentEntity = new Parent(childEntity);
        service.insert(parentEntity);

        service.delete(parentEntity);
        childService.delete(childEntity);
    }

}
