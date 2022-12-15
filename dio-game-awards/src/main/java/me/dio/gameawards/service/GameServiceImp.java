package me.dio.gameawards.service;

import me.dio.gameawards.domain.models.Game;
import me.dio.gameawards.domain.models.GameRepository;
import me.dio.gameawards.service.exception.BusinessException;
import me.dio.gameawards.service.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImp implements GameService{

    @Autowired
    private GameRepository repository;

    @Override
    public List<Game> findAll() {
        List<Game> games = repository.findAll();
        return games;
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> game = repository.findById(id);
        return game.orElseThrow( () -> new NoContentException());
    }

    @Override
    public void insert(Game game) {
        repository.save(game);
    }

    @Override
    public void update(Long id, Game game) {
        Game gameDb = findById(id);
        if (gameDb.getId().equals(game.getId())) {
            repository.save(game);
        } else {
            throw new BusinessException("Os Ids para alteração são divergentes.");
        }
    }

    @Override
    public void delete(Long id) {
        Game gameDb = findById(id);
        repository.delete(gameDb);
    }
}
