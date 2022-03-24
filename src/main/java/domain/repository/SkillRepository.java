package domain.repository;

import common.Nothing;
import data.Result;
import data.dao.CategoryDao;
import data.dao.SkillDao;
import data.model.CategoryDbEntity;
import data.model.SkillDbEntity;

public interface SkillRepository {

    /**
     * Provide a safe thread singleton of the repository.
     *
     * @return The unique instance of the Repository.
     */
    static SkillRepository getInstance() {
        return SkillRepositoryImpl.INSTANCE;
    }

    Result<SkillDbEntity> getById(int id);

    Result<SkillDbEntity> getByName(String name);

    Result<SkillDbEntity> insert(SkillDbEntity skill);

    Result<SkillDbEntity> update(SkillDbEntity skill);

    Result<Nothing> removeById(int id);

    Result<Nothing> remove(SkillDbEntity category);
}

class SkillRepositoryImpl implements SkillRepository {
    private final SkillDao dao = SkillDao.getInstance();
    public final static SkillRepository INSTANCE = new SkillRepositoryImpl();

    private SkillRepositoryImpl() {
    }

    @Override
    public Result<SkillDbEntity> getById(int id) {
        return dao.getById(id);
    }

    @Override
    public Result<SkillDbEntity> getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public Result<SkillDbEntity> insert(SkillDbEntity skill) {
        Result<SkillDbEntity> searchSkillBeforeInsertResult = dao.getByName(skill.getName());
        if (searchSkillBeforeInsertResult.isEmpty()) {
            Result<Integer> insertResult = dao.insert(skill);
            if (insertResult.isSuccess()) {
                return dao.getById(insertResult.getValue());
            } else {
                if (insertResult.isEmpty()) {
                    return Result.empty();
                }
                return Result.error(insertResult.getException());
            }
        }
        if (searchSkillBeforeInsertResult.isSuccess()) {
            return Result.empty();
        }
        return searchSkillBeforeInsertResult;
    }

    @Override
    public Result<SkillDbEntity> update(SkillDbEntity skill) {
        Result<Nothing> updateResult = dao.update(skill);
        if (updateResult.isSuccess()) {
            // Return the updated element if it exists
            return dao.getById(skill.getId());
        } else if (updateResult.isEmpty()) {
            // Return an empty result if it has done nothing
            return Result.empty();
        }
        return Result.error(updateResult.getException());
    }

    @Override
    public Result<Nothing> removeById(int id) {
        // Retrieving the skill to remove
        Result<SkillDbEntity> searchResult = dao.getById(id);
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
    public Result<Nothing> remove(SkillDbEntity category) {
        // Retrieving the skill to remove
        Result<SkillDbEntity> searchResult = dao.getById(category.getId());
        if (searchResult.isSuccess()) {
            // Removing it if it exists
            return dao.remove(category);
        } else if (searchResult.isEmpty()) {
            return Result.empty();
        }
        return Result.error(searchResult.getException());
    }
}

