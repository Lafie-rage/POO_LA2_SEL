package data.dao.jpadao;

import data.dao.DaoCategory;
import data.dao.DaoSkill;
import data.model.CategoryDbEntity;
import data.model.SkillDbEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JpaDaoSkillTest {
    DaoSkill dao = JpaDaoSkill.getInstance();

    @Test
    void unitTestCrud() {

        DaoCategory daoCategory = JpaDaoCategory.getInstance();

        CategoryDbEntity category = daoCategory.find(CategoryDbEntity.class, 29);

        SkillDbEntity skill = new SkillDbEntity();
        skill.setName("Cours de web");
        skill.setCategory(category);
        assertTrue(dao.create(skill));
        skill.setName("Cours de POO");
        assertTrue(dao.update(skill));

        SkillDbEntity foundSkill = dao.find(SkillDbEntity.class, skill.getId());

        assertNotNull(foundSkill);

        assertEquals(foundSkill.getId(), skill.getId());
        assertEquals(foundSkill.getName(), skill.getName());

        System.out.println(dao.findAll(SkillDbEntity.class).size());

        assertTrue(dao.delete(skill));
    }
}
