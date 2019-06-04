package com.ef.repositories;

import com.ef.entities.IpRestrictionEntity;
import org.springframework.data.repository.CrudRepository;

public interface IpRestrictionRepository extends CrudRepository<IpRestrictionEntity, Long> {
}
