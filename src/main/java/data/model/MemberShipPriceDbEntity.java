package data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_tarif", schema = "bdd_sel_from_teacher", catalog = "")
public class MemberShipPriceDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TARIF")
    private int id;
    @Basic
    @Column(name = "ANNEE")
    private int year;
    @Basic
    @Column(name = "MONTANT")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberShipPriceDbEntity that = (MemberShipPriceDbEntity) o;
        return id == that.id && year == that.year && price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, price);
    }

    @Override
    public String toString() {
        return "MemberShipPriceDbEntity{" +
                "id=" + id +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
