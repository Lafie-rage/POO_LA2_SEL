package data.dao;

import data.dao.jpadao.DaoFactoryJpa;
import data.dao.jsondao.DaoFactoryJson;

public abstract class DaoFactory {

    public static DaoFactory getDaoFactory(PersistenceType type) {
        if (type == PersistenceType.JPA) {
            return new DaoFactoryJpa();
        } else {
            return new DaoFactoryJson();
        }
    }

    public abstract DaoCategory getDaoCategory();

    public abstract DaoSkill getDaoSkill();
}
