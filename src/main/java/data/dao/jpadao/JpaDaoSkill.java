package data.dao.jpadao;

import data.dao.DaoSkill;
import data.model.SkillDbEntity;

public class JpaDaoSkill extends JpaDao<SkillDbEntity> implements DaoSkill {
    private final static JpaDaoSkill instance = new JpaDaoSkill();

    private JpaDaoSkill() {
    }

    /**
     * Return a thread safe singleton of this class.
     *
     * @return A thread safe singleton.
     */
    public static JpaDaoSkill getInstance() {
        return instance;
    }
}
