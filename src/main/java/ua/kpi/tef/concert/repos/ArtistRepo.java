package ua.kpi.tef.concert.repos;

import org.springframework.data.repository.CrudRepository;
import ua.kpi.tef.concert.domain.Artist;

import java.util.List;

public interface ArtistRepo extends CrudRepository<Artist, Integer> {
    List<Artist> findByType(String type);
    Artist findById(int id);
}
