package ua.kpi.tef.concert.repos;

import org.springframework.data.repository.CrudRepository;
import ua.kpi.tef.concert.entities.Pause;

public interface PauseRepo extends CrudRepository<Pause, Integer> {
    Pause findById(int id);
}
