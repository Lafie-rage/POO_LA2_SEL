package data.model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_competence", schema = "bdd_sel_from_teacher")
public final class SkillDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_COMPETENCE")
    @SerializedName("id")
    private int id;
    @Basic
    @Column(name = "COMPETENCE")
    @SerializedName("name")
    private String name;

    @ManyToOne
    @JoinColumn(name="ID_CATEGORIE")
    private CategoryDbEntity category;

    public int getId() {
        return id;
    }

    public void setId(int idCompetence) {
        this.id = idCompetence;
    }

    public String getName() {
        return name;
    }

    public void setName(String competence) {
        this.name = competence;
    }

    public CategoryDbEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryDbEntity category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDbEntity that = (SkillDbEntity) o;
        return id == that.id && category == that.category && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category);
    }

    @Override
    public String toString() {
        return "SkillDbEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idCategory=" + category +
                '}';
    }
}
