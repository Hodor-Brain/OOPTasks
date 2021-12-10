package Task7;

import java.io.Serializable;
import java.util.Objects;

public class Medicine implements Serializable {
    private String name;
    private int id;
    private String pharm;

    public Medicine(String name, int age, String pharm) {
        this.name = name;
        this.id = age;
        this.pharm = pharm;
    }

    public Medicine() {}

    @Override
    public String toString() {
        return "Medicine " + name +
                ", id is " + id +
                ", producer " + pharm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return id == medicine.id &&
                Objects.equals(name, medicine.name) &&
                Objects.equals(pharm, medicine.pharm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, pharm);
    }
}
