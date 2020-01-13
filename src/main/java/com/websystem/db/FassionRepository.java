package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.FassionEntity;

@Repository
public interface FassionRepository extends JpaRepository<FassionEntity, String> {

}
