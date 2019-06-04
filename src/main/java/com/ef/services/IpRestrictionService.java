package com.ef.services;

import com.ef.entities.IpRestrictionEntity;
import com.ef.repositories.IpRestrictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpRestrictionService {
    @Autowired private IpRestrictionRepository ipRestrictionRepository;

    public IpRestrictionEntity save(IpRestrictionEntity ipRestriction) {
        ipRestrictionRepository.save(ipRestriction);
        return ipRestriction;
    }
}
