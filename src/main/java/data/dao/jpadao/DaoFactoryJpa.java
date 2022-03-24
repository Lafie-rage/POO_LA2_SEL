package data.dao.jpadao;

import data.dao.DaoCategory;
import data.dao.DaoFactory;
import data.dao.DaoSkill;

public class DaoFactoryJpa extends DaoFactory {
    public DaoCategory getDaoCategory() {
        return JpaDaoCategory.getInstance();
    }

    public DaoSkill getDaoSkill() {
        return JpaDaoSkill.getInstance();
    }
}
