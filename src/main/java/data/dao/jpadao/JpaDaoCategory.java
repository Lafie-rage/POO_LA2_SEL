package data.dao.jpadao;

import java.util.List;
import data.dao.CategoryDao;
import data.dao.DaoCategory;
import data.model.CategoryDbEntity;
import data.model.SkillDbEntity;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaDaoCategory extends JpaDao<CategoryDbEntity> implements DaoCategory {

    private final static JpaDaoCategory instance = new JpaDaoCategory();

    private JpaDaoCategory() {
    }

    /**
     * Return a thread safe singleton of this class.
     *
     * @return A thread safe singleton.
     */
    public static JpaDaoCategory getInstance() {
        return instance;
    }

    @Override
    public int countSkills(CategoryDbEntity category) {
        Transaction transaction = getSession().getTransaction();
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<SkillDbEntity> criteriaQuery = builder.createQuery(SkillDbEntity.class);
        Root<SkillDbEntity> rootEntry = criteriaQuery.from(SkillDbEntity.class);

        Predicate whereIdCategoryEqual =  builder.equal(rootEntry.get("category"), category);
        criteriaQuery.select(rootEntry).where(whereIdCategoryEqual);

        Query<SkillDbEntity> query = getSession().createQuery(criteriaQuery);

        try {
            transaction.begin();

             List<SkillDbEntity> resultList = query.getResultList();

             if(resultList != null) {
                 return resultList.size();
             }
        } catch(Exception e) {
            return 0;
        }
        return 0;
    }
}
