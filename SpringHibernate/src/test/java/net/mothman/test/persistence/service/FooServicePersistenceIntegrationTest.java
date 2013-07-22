package net.mothman.test.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import net.mothman.persistence.model.Foo;
import net.mothman.persistence.service.FooService;
import net.mothman.test.PersistenceConfig;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class FooServicePersistenceIntegrationTest {

    @Autowired
    private FooService service;

    // tests

    @Test
    public final void whenContextIsBootstrapped_thenNoExceptions() {
        //
    }

    @Test
    public final void whenEntityIsCreated_thenNoExceptions() {
        service.insert(new Foo(randomAlphabetic(6)));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public final void whenInvalidEntityIsCreated_thenDataException() {
        service.insert(new Foo());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public final void whenEntityWithLongNameIsCreated_thenDataException() {
        service.insert(new Foo(randomAlphabetic(2048)));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    @Ignore("Right now, persist has saveOrUpdate semantics, so this will no longer fail")
    public final void whenSameEntityIsCreatedTwice_thenDataException() {
        final Foo entity = new Foo(randomAlphabetic(8));
        service.insert(entity);
        service.insert(entity);
    }

    @Test
    @Ignore
    public final void temp_whenInvalidEntityIsCreated_thenDataException() {
        service.insert(new Foo(randomAlphabetic(2048)));
    }

}
