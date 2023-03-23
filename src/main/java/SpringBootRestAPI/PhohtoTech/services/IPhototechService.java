package SpringBootRestAPI.PhohtoTech.services;

import SpringBootRestAPI.PhohtoTech.models.Phototech;

import java.util.List;
import java.util.Optional;

public interface IPhototechService {
    List<Phototech> getAllPhototechs();
    Optional<Phototech> getPhototechByID(long id);
    Phototech createPhototech(Phototech phototech);
    Phototech updatePhototech(Phototech phototech);
    void deletePhototech(long id);
}

