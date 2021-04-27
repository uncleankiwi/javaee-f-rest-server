package com.kovunov.service;

import com.kovunov.entity.Player;
import com.kovunov.entity.PlayerUpdateDto;
import com.kovunov.entity.Team;

import java.util.List;

public interface PlayerService {
    void clearList();

    List<Player> getPlayerList();

    List<Player> getPlayerListByTeam(Team team);

    List<Player> getWaitList();

    void addToList(Player player);
    void removeFromList(Player player);
    Player getById(Long id);
    Player updatePlayer(PlayerUpdateDto dto, Player playerToUpdate);
}
