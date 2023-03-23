package SpringBootRestAPI.PhohtoTech.Repo;

import SpringBootRestAPI.PhohtoTech.models.Phototech;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PhototechRepository implements IPhototechRepository{
    @Override
    public <S extends Phototech> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Phototech> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Phototech> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Phototech> findAll() {
        return null;
    }

    @Override
    public Iterable<Phototech> findAllById(Iterable<Long> longs) {
        return null;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Phototech entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Phototech> entities) {

    }

    @Override
    public void deleteAll() {

    }

}
