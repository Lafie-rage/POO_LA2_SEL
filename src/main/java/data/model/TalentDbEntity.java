package data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_talent", schema = "bdd_sel_from_teacher", catalog = "")
public final class TalentDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TALENT")
    private int id;
    @Basic
    @Column(name = "ID_MEMBRE")
    private int idMember;
    @Basic
    @Column(name = "ID_COMPETENCE")
    private int idSkill;
    @Basic
    @Column(name = "DetailCompetence")
    private String detailCompetence;

    public int getId() {
        return id;
    }

    public void setId(int idTalent) {
        this.id = idTalent;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMembre) {
        this.idMember = idMembre;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idCompetence) {
        this.idSkill = idCompetence;
    }

    public String getDetailCompetence() {
        return detailCompetence;
    }

    public void setDetailCompetence(String detailCompetence) {
        this.detailCompetence = detailCompetence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TalentDbEntity that = (TalentDbEntity) o;
        return id == that.id && idMember == that.idMember && idSkill == that.idSkill && Objects.equals(detailCompetence, that.detailCompetence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMember, idSkill, detailCompetence);
    }

    @Override
    public String toString() {
        return "TalentDbEntity{" +
                "id=" + id +
                ", idMember=" + idMember +
                ", idSkill=" + idSkill +
                ", detailCompetence='" + detailCompetence + '\'' +
                '}';
    }
}
