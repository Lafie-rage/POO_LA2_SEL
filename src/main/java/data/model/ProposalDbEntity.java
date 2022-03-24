package data.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "t_proposition", schema = "bdd_sel_from_teacher", catalog = "")
public class ProposalDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_PROPOSITION")
    private int id;
    @Basic
    @Column(name = "ID_TALENT")
    private int talentId;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "DATE_DEBUT")
    private Date beginDate;
    @Basic
    @Column(name = "DATE_FIN")
    private Date endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTalentId() {
        return talentId;
    }

    public void setTalentId(int talentId) {
        this.talentId = talentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProposalDbEntity that = (ProposalDbEntity) o;
        return id == that.id && talentId == that.talentId && Objects.equals(description, that.description) && Objects.equals(beginDate, that.beginDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, talentId, description, beginDate, endDate);
    }

    @Override
    public String toString() {
        return "ProposalDbEntity{" +
                "id=" + id +
                ", talentId=" + talentId +
                ", description='" + description + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
