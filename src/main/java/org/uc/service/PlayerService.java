package org.uc.service;

import org.uc.entity.Player;
import org.uc.entity.PlayerUpdateDto;
import org.uc.entity.Team;
import org.uc.exception.InvalidPlayerIdException;
import org.uc.exception.InvalidTeamIdException;
import org.uc.exception.PlayerNotFoundException;
import org.uc.exception.TeamNotFoundException;

import java.util.List;

public interface PlayerService {
    void clearList();

    List<Player> getPlayerList();

    List<Player> getPlayerListByTeam(Team team) throws InvalidTeamIdException, TeamNotFoundException;
    List<Player> getWaitList();

    void addToList(Player player);
    void removeFromList(Player player);
    Player getById(Long id) throws InvalidPlayerIdException, PlayerNotFoundException;
    Player updatePlayer(PlayerUpdateDto dto) throws InvalidPlayerIdException, PlayerNotFoundException;
}
