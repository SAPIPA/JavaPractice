package SpringBootRestAPI.PhohtoTech.Repo;

import SpringBootRestAPI.PhohtoTech.models.Phototech;
import org.springframework.data.repository.CrudRepository;

public interface IPhototechRepository extends CrudRepository<Phototech, Long> {

}