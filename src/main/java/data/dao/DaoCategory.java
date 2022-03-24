package data.dao;

import data.model.CategoryDbEntity;

public interface DaoCategory extends Dao<CategoryDbEntity> {
    int countSkills(CategoryDbEntity category);
}
