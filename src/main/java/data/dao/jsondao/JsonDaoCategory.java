package data.dao.jsondao;

import data.dao.DaoCategory;
import data.model.CategoryDbEntity;

import java.util.List;
import java.util.stream.Collectors;

public class JsonDaoCategory extends JsonDao<CategoryDbEntity> implements DaoCategory {

    private final static JsonDaoCategory instance = new JsonDaoCategory();

    private JsonDaoCategory() {
        super(CategoryDbEntity.class);
    }

    /**
     * Return a thread safe singleton of this class.
     *
     * @return A thread safe singleton.
     */
    public static JsonDaoCategory getInstance() {
        return instance;
    }


    @Override
    public int countSkills(CategoryDbEntity category) {
        return 0; // Actually impossible
    }

    @Override
    public CategoryDbEntity find(Class<CategoryDbEntity> c, int id) {
        List<CategoryDbEntity> data = super.findAll(c);
        data = data.stream().filter(category -> category.getId() == id).collect(Collectors.toList());

        if (!data.isEmpty()) {
            return data.get(0);
        }
        return null;
    }

    @Override
    public boolean update(CategoryDbEntity category) {
        List<CategoryDbEntity> categories = findAll(CategoryDbEntity.class);
        categories = categories.stream().filter(item -> item.getName().equals(item.getName()) || item.getId() == item.getId()).collect(Collectors.toList());
        if (!categories.isEmpty()) { // If not empty, we update it
            CategoryDbEntity oldCategory = categories.get(0);
            // Because Java determines the result of the whole logical expression depending on the value of some of them
            // create will only be called if delete worked.
            return delete(oldCategory) && create(category);
        } else { // Otherwise, we create it
            return create(category);
        }
    }
}
