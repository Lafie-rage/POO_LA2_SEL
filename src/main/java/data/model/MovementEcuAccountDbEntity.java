package data.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_mouvement_compte_ecu", schema = "bdd_sel_from_teacher", catalog = "")
public class MovementEcuAccountDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_MOUVEMENT_COMPTE_ECU")
    private int id;
    @Basic
    @Column(name = "ID_MEMBRE")
    private int memberId;
    @Basic
    @Column(name = "MONTANT_MOUVEMENT")
    private BigDecimal amount;
    @Basic
    @Column(name = "TYPE_MOUVEMENT")
    private String type;
    @Basic
    @Column(name = "DATE_HEURE_MOUVEMENT_COMPTE_ECU")
    private Timestamp dateTime;
    @Basic
    @Column(name = "NATURE_MOUVEMENT")
    private String nature;
    @Basic
    @Column(name = "ID_REFERENCE_MOUVEMENT")
    private Integer referenceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementEcuAccountDbEntity that = (MovementEcuAccountDbEntity) o;
        return id == that.id && memberId == that.memberId && Objects.equals(amount, that.amount) && Objects.equals(type, that.type) && Objects.equals(dateTime, that.dateTime) && Objects.equals(nature, that.nature) && Objects.equals(referenceId, that.referenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, amount, type, dateTime, nature, referenceId);
    }
}
