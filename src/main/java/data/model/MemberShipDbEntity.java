package data.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "t_cotisations", schema = "bdd_sel_from_teacher", catalog = "")
public class MemberShipDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_COTISATION")
    private int id;
    @Basic
    @Column(name = "DATE_COTISATION")
    private Date date;
    @Basic
    @Column(name = "ID_MEMBRE")
    private int memberId;
    @Basic
    @Column(name = "ID_TARIF")
    private int priceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberShipDbEntity that = (MemberShipDbEntity) o;
        return id == that.id && memberId == that.memberId && priceId == that.priceId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, memberId, priceId);
    }

    @Override
    public String toString() {
        return "MemberShipDbEntity{" +
                "id=" + id +
                ", date=" + date +
                ", memberId=" + memberId +
                ", priceId=" + priceId +
                '}';
    }
}
