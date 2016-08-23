package com.stepping.step5.entity.repository;

import com.stepping.step5.entity.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GroupsRepository extends JpaRepository<Group, Integer> {
}
