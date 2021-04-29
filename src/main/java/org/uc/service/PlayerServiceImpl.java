package org.uc.service;

import org.uc.entity.Player;
import org.uc.entity.PlayerUpdateDto;
import org.uc.entity.Team;
import org.uc.exception.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PlayerServiceImpl implements PlayerService {
	private static final int MAX_CAPACITY = 27;
	private static final int INITIAL_CAPACITY = 18;

	@PersistenceContext
	private EntityManager em;

	@EJB
	private TeamService teamService;


	@Override
	public void clearList() {
		Query deleteFromPlayer = em.createNamedQuery("Player.clearAll");
		deleteFromPlayer.executeUpdate();
	}

	@Override
	public List<Player> getPlayerList() {
		List<Player> playerList =  em.createNamedQuery("Player.findAll", Player.class)
				.getResultList();

		if (playerList.size() < MAX_CAPACITY) {
			return playerList.stream()
					.limit(INITIAL_CAPACITY)
					.sorted()
					.collect(Collectors.toList());
		} else {
			return playerList.stream()
					.limit(MAX_CAPACITY)
					.sorted()
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<Player> getPlayerListByTeam(Team team) throws InvalidTeamIdException, TeamNotFoundException {
		Team dbTeam =  teamService.getById(team.getId());
		return em.createNamedQuery("Player.findAllByTeam", Player.class)
				.setParameter("teamId", dbTeam.getId())
				.getResultList();
	}

	@Override
	public Player getById(Long id) throws InvalidPlayerIdException, PlayerNotFoundException {
		if (id == null || id <= 0) {
			throw new InvalidPlayerIdException(id);
		}

		Player player = em.find(Player.class, id);

		if (player == null) {
			throw new PlayerNotFoundException(id);
		}

		return player;
	}

	@Override
	public List<Player> getWaitList() {
		List<Player> playerList =  em.createNamedQuery("Player.findAll", Player.class)
				.getResultList();
		if (playerList.size() > INITIAL_CAPACITY && playerList.size() < MAX_CAPACITY) {
			return playerList
					.stream()
					.sorted(Comparator.reverseOrder())
					.limit(8)
					.collect(Collectors.toList());
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public Player updatePlayer(PlayerUpdateDto dto)	throws InvalidPlayerIdException, PlayerNotFoundException {
		Player playerToUpdate = getById(dto.getId());

		if (dto.getUserName() != null) {
			playerToUpdate.setUserName(dto.getUserName());
		}
		if (dto.getFirstName() != null) {
			playerToUpdate.setFirstName(dto.getFirstName());
		}
		return em.merge(playerToUpdate);
	}

	@Override
	public void addToList(Player player) throws UsernameExistsException {
		try {
			em.persist(player);
		}
		catch (Exception e) {
			//this is unable to catch the exception for some reason, so it'll be
			//thrown and caught in PlayerResource
			throw new UsernameExistsException(player.getUserName());
		}

	}


	@Override
	public void removeFromList(Player player) {
		//Player playerWithId = em.find(Player.class, com.kovunov.player.getId());
		Player correspondingPlayer = em
				.createNamedQuery("Player.getByUserName", Player.class)
				.setParameter("userName", player.getUserName())
				.getSingleResult();
		em.remove(correspondingPlayer);
	}

}
