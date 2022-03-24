package data;

import com.sun.istack.NotNull;
import common.Nothing;
import data.model.SkillDbEntity;
import domain.repository.SkillRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SkillTest {
    private final SkillRepository repository = SkillRepository.getInstance();

    @Test
    public void testRetrieveSkill() {
        testRetrieveExistentSkill();
        testRetrieveNonexistentSkill();
    }

    @Test
    public void testInsertSkill() {
        SkillDbEntity insertedSkill = testInsertNonexistentSkill();
        testInsertExistentSkill(insertedSkill);
    }

    @Test
    public void testUpdateSkill() {
        SkillDbEntity updatedSkill = testUpdateExistentSkill();
        testUpdateNonExistentSkill(updatedSkill);
    }

    @Test
    public void testRemoveSkill() {
        SkillDbEntity removedSkill = testRemoveExistentSkill();
        testRemoveNonExistentSkill(removedSkill);
    }

    private void testRetrieveExistentSkill() {
        Result<SkillDbEntity> result = repository.getById(13);

        // Item exists in DB
        // It should be a success Result
        assertTrue(result.isSuccess());
        assertFalse(result.isEmpty());
        assertFalse(result.isError());
        assertNotNull(result.getValue());
        assertNull(result.getException());

        SkillDbEntity skill = result.getValue();

        // Testing item values
        assertNotEquals(null, skill);
        assertEquals("Cours de jardinage", skill.getName());
        assertEquals(13, skill.getId());
    }

    private void testRetrieveNonexistentSkill() {
        Result<SkillDbEntity> result = repository.getById(0);

        // Item doesn't exist in DB
        // It should be an empty Result
        assertFalse(result.isSuccess());
        assertTrue(result.isEmpty());
        assertFalse(result.isError());
        assertNull(result.getValue());
        assertNull(result.getException());

    }

    @NotNull
    private SkillDbEntity testInsertNonexistentSkill() {
        // Defining a new Skill
        final SkillDbEntity skill = new SkillDbEntity();
        skill.setName("ART");

        Result<SkillDbEntity> result =  repository.insert(skill);

        // Verifying return
        // Should be a success Result
        assertTrue(result.isSuccess());
        assertFalse(result.isEmpty());
        assertFalse(result.isError());
        assertNotNull(result.getValue());
        assertNull(result.getException());

        SkillDbEntity insertedSkill = result.getValue();

        // Verifying return
        assertNotEquals(-1, insertedSkill.getId());
        assertEquals(skill.getName(), insertedSkill.getName());
        return insertedSkill;
    }

    private void testInsertExistentSkill(@NotNull SkillDbEntity skill) {
        Result<SkillDbEntity> result =  repository.insert(skill);

        // Verifying return
        // Should be an empty Result
        assertFalse(result.isSuccess());
        assertTrue(result.isEmpty());
        assertFalse(result.isError());
        assertNull(result.getValue());
        assertNull(result.getException());
    }

    private SkillDbEntity testUpdateExistentSkill() {
        SkillDbEntity skill = new SkillDbEntity();
        skill.setName("COURS DE TAI CHI");

        Result<SkillDbEntity> searchResult = repository.getByName(skill.getName());

        if(searchResult.isEmpty()) {
            searchResult = repository.insert(skill);
        }

        assertNotNull(searchResult.getValue());

        skill = searchResult.getValue();
        skill.setName("Art");

        Result<SkillDbEntity> insertResult = repository.update(skill);

        // Verifying result
        // Should be a success result
        assertTrue(insertResult.isSuccess());
        assertFalse(insertResult.isEmpty());
        assertFalse(insertResult.isError());
        assertNotNull(insertResult.getValue());
        assertNull(insertResult.getException());

        return insertResult.getValue();
    }

    private void testUpdateNonExistentSkill(SkillDbEntity skill) {
        Result<Nothing> removeResult = repository.remove(skill);

        // Should be a success
        assertTrue(removeResult.isSuccess());
        assertFalse(removeResult.isEmpty());
        assertFalse(removeResult.isError());
        assertNotNull(removeResult.getValue());
        assertNull(removeResult.getException());
        assertInstanceOf(Nothing.class, removeResult.getValue());

        Result<SkillDbEntity> insertResult = repository.update(skill);

        // Verifying result
        // Should be an empty result
        assertFalse(insertResult.isSuccess());
        assertTrue(insertResult.isEmpty());
        assertFalse(insertResult.isError());
        assertNull(insertResult.getValue());
        assertNull(insertResult.getException());
    }

    private SkillDbEntity testRemoveExistentSkill() {
        SkillDbEntity skill = new SkillDbEntity();
        skill.setName("ART");

        Result<SkillDbEntity> searchResult = repository.getByName(skill.getName());

        if(searchResult.isEmpty()) {
            searchResult = repository.insert(skill);
        }

        assertNotNull(searchResult.getValue());

        skill = searchResult.getValue();
        skill.setName("Art");

        Result<Nothing> removeResult = repository.remove(skill);

        // Verifying result
        // Should be a success result
        assertTrue(removeResult.isSuccess());
        assertFalse(removeResult.isEmpty());
        assertFalse(removeResult.isError());
        assertNotNull(removeResult.getValue());
        assertNull(removeResult.getException());
        assertInstanceOf(Nothing.class, removeResult.getValue());

        return skill;
    }

    private void testRemoveNonExistentSkill(SkillDbEntity skill) {
        Result<Nothing> removeResult = repository.remove(skill);

        // Verifying result
        // Should be an empty result
        assertFalse(removeResult.isSuccess());
        assertTrue(removeResult.isEmpty());
        assertFalse(removeResult.isError());
        assertNull(removeResult.getValue());
        assertNull(removeResult.getException());
    }
}
