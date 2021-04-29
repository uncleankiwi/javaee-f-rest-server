package org.uc.service;

import org.uc.entity.Player;
import org.uc.entity.PlayerUpdateDto;
import org.uc.entity.Team;
import org.uc.exception.InvalidPlayerIdException;
import org.uc.exception.InvalidTeamIdException;
import org.uc.exception.PlayerNotFoundException;
import org.uc.exception.TeamNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Response;
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
		//no valid team id is indicated
		if (team.getId() == null || team.getId() <= 0) {
			throw new InvalidTeamIdException(team.getId());
		}

		//team doesn't exist
		Team dbTeam =  teamService.getById(team.getId());
		if (dbTeam == null) {
			throw new TeamNotFoundException(team.getId());
		}

		return em.createNamedQuery("Player.findAllByTeam", Player.class)
				.setParameter("teamId", dbTeam.getId())
				.getResultList();
	}

	@Override
	public Player getById(Long id) {
		return em.find(Player.class, id);
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
	public Player updatePlayer(PlayerUpdateDto dto)
			throws InvalidPlayerIdException, PlayerNotFoundException {
		if (dto.getId() == null || dto.getId() == 0) {
			throw new InvalidPlayerIdException(dto.getId());
		}

		Player playerToUpdate = getById(dto.getId());

		if (playerToUpdate == null) {
			throw new PlayerNotFoundException(dto.getId());
		}

		if (dto.getUserName() != null) {
			playerToUpdate.setUserName(dto.getUserName());
		}
		if (dto.getFirstName() != null) {
			playerToUpdate.setFirstName(dto.getFirstName());
		}
		return em.merge(playerToUpdate);
	}

	@Override
	public void addToList(Player player) {
		em.persist(player);
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
