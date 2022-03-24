package data.dao.jsondao;

import data.dao.DaoCategory;
import data.dao.DaoFactory;
import data.dao.PersistenceType;
import data.model.CategoryDbEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonCategoryDaoTest {
    DaoFactory factory = DaoFactory.getDaoFactory(PersistenceType.JSON);
    DaoCategory dao = factory.getDaoCategory();

    @Test
    void unitTestCrud() {
        CategoryDbEntity category = new CategoryDbEntity();
        category.setName("Cours d'informatique");
        assertTrue(dao.create(category));
        category.setName("Cours de Geek");
        assertTrue(dao.update(category));

        CategoryDbEntity foundCategory = dao.find(CategoryDbEntity.class, category.getId());

        assertNotNull(foundCategory);

        assertEquals(foundCategory.getId(), category.getId());
        assertEquals(foundCategory.getName(), category.getName());

        System.out.println(dao.findAll(CategoryDbEntity.class).size());

        DaoCategory dao2 = factory.getDaoCategory();
        System.out.println(dao.findAll(CategoryDbEntity.class).size());
        System.out.println(dao2.findAll(CategoryDbEntity.class).size());

        assertTrue(dao.delete(category));

        assertTrue(dao.deleteAll(CategoryDbEntity.class));
    }
}
