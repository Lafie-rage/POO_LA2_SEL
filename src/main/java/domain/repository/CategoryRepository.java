package domain.repository;

import common.Nothing;
import data.Result;
import data.dao.CategoryDao;
import data.model.CategoryDbEntity;

public interface CategoryRepository {

    /**
     * Provide a safe thread singleton of the repository.
     *
     * @return The unique instance of the Repository.
     */
    static CategoryRepository getInstance() {
        return CategoryRepositoryImpl.INSTANCE;
    }

    Result<CategoryDbEntity> getById(int id);

    Result<CategoryDbEntity> getByName(String name);

    Result<CategoryDbEntity> insert(CategoryDbEntity category);

    Result<CategoryDbEntity> update(CategoryDbEntity category);

    Result<Nothing> removeById(int id);

    Result<Nothing> remove(CategoryDbEntity category);

}

class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryDao dao = CategoryDao.getInstance();
    public final static CategoryRepository INSTANCE = new CategoryRepositoryImpl();

    private CategoryRepositoryImpl() {
    }

    @Override
    public Result<CategoryDbEntity> getById(int id) {
        return dao.getById(id);
    }

    @Override
    public Result<CategoryDbEntity> getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public Result<CategoryDbEntity> insert(CategoryDbEntity category) {
        Result<CategoryDbEntity> searchCategoryBeforeInsertResult = dao.getByName(category.getName());
        if (searchCategoryBeforeInsertResult.isEmpty()) {
            Result<Integer> insertResult = dao.insert(category);
            if (insertResult.isSuccess()) {
                return dao.getById(insertResult.getValue());
            } else {
                if (insertResult.isEmpty()) {
                    return Result.empty();
                }
                return Result.error(insertResult.getException());
            }
        }
        if (searchCategoryBeforeInsertResult.isSuccess()) {
            return Result.empty();
        }
        return searchCategoryBeforeInsertResult;
    }

    @Override
    public Result<CategoryDbEntity> update(CategoryDbEntity category) {
        Result<Nothing> updateResult = dao.update(category);
        if (updateResult.isSuccess()) {
            // Return the updated element if it exists
            return dao.getById(category.getId());
        } else if (updateResult.isEmpty()) {
            // Return an empty result if it has done nothing
            return Result.empty();
        }
        return Result.error(updateResult.getException());
    }

    @Override
    public Result<Nothing> removeById(int id) {
        // Retrieving the category to remove
        Result<CategoryDbEntity> searchResult = dao.getById(id);
        if (searchResult.isSuccess()) {
            // Removing it if it exists
            // Keeping business logic to the remove by entity function
            return remove(searchResult.getValue());
        } else if (searchResult.isEmpty()) {
            return Result.empty();
        }
        return Result.error(searchResult.getException());
    }

    @Override
    public Result<Nothing> remove(CategoryDbEntity category) {
        // Retrieving the category to remove
        Result<CategoryDbEntity> searchResult = dao.getById(category.getId());
        if (searchResult.isSuccess()) {
            // Removing it if it exists
            return dao.remove(category);
        } else if (searchResult.isEmpty()) {
            return Result.empty();
        }
        return Result.error(searchResult.getException());
    }
}
