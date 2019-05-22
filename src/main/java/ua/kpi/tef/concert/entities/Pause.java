package ua.kpi.tef.concert.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Pause {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String type;
    private int duration;

    public Pause() {
    }

    public Pause(String type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pause)) return false;
        Pause pause = (Pause) o;
        return Objects.equals(id, pause.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration + id.toString());
    }
}
