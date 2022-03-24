package ref.fk.data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_ex_cascade_fk", schema ="bdd_sel_from_teacher")
public class ForeignKeyDbEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="ID_FK")
    private int id;

    @Basic
    @Column(name="LIB_FK")
    private String lib;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="ID_REF")
    private ReferenceDbEntity ref;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public ReferenceDbEntity getRef() {
        return ref;
    }

    public void setRef(ReferenceDbEntity ref) {
        this.ref = ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForeignKeyDbEntity that = (ForeignKeyDbEntity) o;
        return id == that.id && Objects.equals(lib, that.lib) && ref.equals(that.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lib, ref);
    }

    @Override
    public String toString() {
        return "ForeignKeyDbEntity{" +
                "id=" + id +
                ", lib='" + lib + '\'' +
                ", idRef=" + ref +
                '}';
    }
}
