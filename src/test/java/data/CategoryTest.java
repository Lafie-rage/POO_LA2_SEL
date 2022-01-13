package data;

import com.sun.istack.NotNull;
import common.Nothing;
import data.model.CategoryDbEntity;
import domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    private final CategoryRepository repository = CategoryRepository.getInstance();

    @Test
    public void testRetrieve() {
        testRetrieveExistentCategory();
        testRetrieveNonexistentCategory();
    }

    @Test
    public void testInsertCategory() {
        CategoryDbEntity insertedCategory = testInsertNonexistentCategory();
        testInsertExistentCategory(insertedCategory);
    }

    @Test
    public void testUpdateCategory() {
        CategoryDbEntity updatedCategory = testUpdateExistentCategory();
        testUpdateNonExistentCategory(updatedCategory);
    }

    @Test
    public void testRemoveCategory() {
        CategoryDbEntity removedCategory = testRemoveExistentCategory();
        testRemoveNonExistentCategory(removedCategory);
    }

    private void testRetrieveExistentCategory() {
        Result<CategoryDbEntity> result = repository.getById(13);

        // Item exists in DB
        // It should be a success Result
        assertTrue(result.isSuccess());
        assertFalse(result.isEmpty());
        assertFalse(result.isError());
        assertNotNull(result.getValue());
        assertNull(result.getException());

        CategoryDbEntity category = result.getValue();

        // Testing item values
        assertNotEquals(null, category);
        assertEquals("BRICOLAGE", category.getName());
        assertEquals(13, category.getId());
    }

    private void testRetrieveNonexistentCategory() {
        Result<CategoryDbEntity> result = repository.getById(1);

        // Item doesn't exist in DB
        // It should be an empty Result
        assertFalse(result.isSuccess());
        assertTrue(result.isEmpty());
        assertFalse(result.isError());
        assertNull(result.getValue());
        assertNull(result.getException());

    }

    @NotNull
    private CategoryDbEntity testInsertNonexistentCategory() {
        // Defining a new Category
        final CategoryDbEntity category = new CategoryDbEntity();
        category.setName("ART");

        Result<CategoryDbEntity> result =  repository.insert(category);

        // Verifying return
        // Should be a success Result
        assertTrue(result.isSuccess());
        assertFalse(result.isEmpty());
        assertFalse(result.isError());
        assertNotNull(result.getValue());
        assertNull(result.getException());

        CategoryDbEntity insertedCategory = result.getValue();

        // Verifying return
        assertNotEquals(-1, insertedCategory.getId());
        assertEquals(category.getName(), insertedCategory.getName());
        return insertedCategory;
    }

    private void testInsertExistentCategory(@NotNull CategoryDbEntity category) {
        Result<CategoryDbEntity> result =  repository.insert(category);

        // Verifying return
        // Should be an empty Result
        assertFalse(result.isSuccess());
        assertTrue(result.isEmpty());
        assertFalse(result.isError());
        assertNull(result.getValue());
        assertNull(result.getException());
    }

    private CategoryDbEntity testUpdateExistentCategory() {
        CategoryDbEntity category = new CategoryDbEntity();
        category.setName("ART");

        Result<CategoryDbEntity> searchResult = repository.getByName(category.getName());

        if(searchResult.isEmpty()) {
            searchResult = repository.insert(category);
        }

        assertNotNull(searchResult.getValue());

        category = searchResult.getValue();
        category.setName("Art");

        Result<CategoryDbEntity> insertResult = repository.update(category);

        // Verifying result
        // Should be a success result
        assertTrue(insertResult.isSuccess());
        assertFalse(insertResult.isEmpty());
        assertFalse(insertResult.isError());
        assertNotNull(insertResult.getValue());
        assertNull(insertResult.getException());

        return insertResult.getValue();
    }

    private void testUpdateNonExistentCategory(CategoryDbEntity category) {
        Result<Nothing> removeResult = repository.remove(category);

        // Should be a success
        assertTrue(removeResult.isSuccess());
        assertFalse(removeResult.isEmpty());
        assertFalse(removeResult.isError());
        assertNotNull(removeResult.getValue());
        assertNull(removeResult.getException());
        assertInstanceOf(Nothing.class, removeResult.getValue());

        Result<CategoryDbEntity> insertResult = repository.update(category);

        // Verifying result
        // Should be an empty result
        assertFalse(insertResult.isSuccess());
        assertTrue(insertResult.isEmpty());
        assertFalse(insertResult.isError());
        assertNull(insertResult.getValue());
        assertNull(insertResult.getException());
    }

    private CategoryDbEntity testRemoveExistentCategory() {
        CategoryDbEntity category = new CategoryDbEntity();
        category.setName("ART");

        Result<CategoryDbEntity> searchResult = repository.getByName(category.getName());

        if(searchResult.isEmpty()) {
            searchResult = repository.insert(category);
        }

        assertNotNull(searchResult.getValue());

        category = searchResult.getValue();
        category.setName("Art");

        Result<Nothing> removeResult = repository.remove(category);

        // Verifying result
        // Should be a success result
        assertTrue(removeResult.isSuccess());
        assertFalse(removeResult.isEmpty());
        assertFalse(removeResult.isError());
        assertNotNull(removeResult.getValue());
        assertNull(removeResult.getException());
        assertInstanceOf(Nothing.class, removeResult.getValue());

        return category;
    }

    private void testRemoveNonExistentCategory(CategoryDbEntity category) {
        Result<Nothing> removeResult = repository.remove(category);

        // Verifying result
        // Should be an empty result
        assertFalse(removeResult.isSuccess());
        assertTrue(removeResult.isEmpty());
        assertFalse(removeResult.isError());
        assertNull(removeResult.getValue());
        assertNull(removeResult.getException());
    }
}
