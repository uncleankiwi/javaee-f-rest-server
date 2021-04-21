package com.kovunov.service;

import com.kovunov.entity.Player;
import com.kovunov.entity.Request;

import java.util.List;

public interface PlayerService {
	void clearList();

	List<Player> getPlayerList();

	List<Player> getWaitList();

	void addToList(Player player);
	void removeFromList(Player player);
	Player getById(Long id);
	void addPlayerRequest(String userName, Request request);
	List<Request> getAllRequests();
}
