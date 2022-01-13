package data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_competence", schema = "bdd_sel_from_teacher")
public final class SkillDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_COMPETENCE")
    private int idSkill;
    @Basic
    @Column(name = "COMPETENCE")
    private String name;
    @Basic
    @Column(name = "ID_CATEGORIE")
    private int idCategory;

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idCompetence) {
        this.idSkill = idCompetence;
    }

    public String getName() {
        return name;
    }

    public void setName(String competence) {
        this.name = competence;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDbEntity that = (SkillDbEntity) o;
        return idSkill == that.idSkill && idCategory == that.idCategory && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSkill, name, idCategory);
    }
}
