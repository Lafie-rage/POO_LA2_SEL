package data.dao.jsondao;

import data.dao.DaoCategory;
import data.dao.DaoFactory;
import data.dao.DaoSkill;

public class DaoFactoryJson extends DaoFactory {
    @Override
    public DaoCategory getDaoCategory() {
        return JsonDaoCategory.getInstance();
    }

    @Override
    public DaoSkill getDaoSkill() {
        return null;
    }
}
