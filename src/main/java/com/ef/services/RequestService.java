package com.ef.services;

import com.ef.entities.Config;
import com.ef.entities.RequestEntity;
import com.ef.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired private RequestRepository requestRepository;

    public RequestEntity save(RequestEntity request) {
        requestRepository.save(request);
        return request;
    }

    public List<RequestEntity> saveAll(List<RequestEntity> request) {
        requestRepository.saveAll(request);
        return request;
    }

    public List<String> getExceeded(String startDate, Integer threshold, String duration) {
        Integer hours = duration.equalsIgnoreCase(Config.HOURLY_DURATION) ? 1 : 24;

        return requestRepository.getExceeded(startDate, threshold, hours);
    }

    public RequestEntity parse(String requestStr) {
        RequestEntity request = new RequestEntity();

        String[] parts = requestStr.split("\\|");

        request.setDate(parts[0]);
        request.setIp(parts[1]);
        request.setMethod(parts[2]);
        request.setCode(Integer.valueOf(parts[3]));
        request.setHeader(parts[4]);

        return request;
    }
}
