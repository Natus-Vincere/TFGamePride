package com.gamepride.platform.repository;

import java.util.Collection;
import java.util.Date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.gamepride.platform.model.EventGamer;


@Repository
public interface IEventGamerRepository extends JpaRepository<EventGamer, Long>
{
	@Query("select count(eg.inscriptedAt) from EventGamer eg where eg.inscriptedAt = :inscriptedAt")
	int countByInscriptedAt(@Param("inscriptedAt") Date inscriptedAt);
	
	Collection<EventGamer> findAllByOrderByInscriptedAtDesc();
}
