package com.ef;

import com.ef.entities.Config;
import com.ef.entities.IpRestrictionEntity;
import com.ef.entities.RequestEntity;
import com.ef.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Parser implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Parser.class);
    private static final String delimiter = "**************************************************************************";

    @Autowired private ConfigService configService;
    @Autowired private FileSerivce fileSerivce;
    @Autowired private RequestService requestService;
    @Autowired private IpRestrictionService ipRestrictionService;
    @Autowired private ValidationService validationService;

    public static void main(String[] args) {
        SpringApplication.run(Parser.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            Config config = configService.getInstance(args);
            validationService.validate(config);

            logger.info(delimiter);
            logger.info("Populate log to database...");

            fileSerivce.read(config.getAccesslog(), (records) -> {
                logger.info(delimiter);
                logger.info("Populate " + records.size() + " logs to database...");

                List<RequestEntity> batch = records.stream().map(requestService::parse).collect(Collectors.toList());
                requestService.saveAll(batch);
            });

            logger.info(delimiter);
            logger.info("Find exceeded");

            List<String> exceededIPs = requestService.getExceeded(
                    config.getStartDate(),
                    config.getThreshold(),
                    config.getDuration());

            exceededIPs.stream().forEach(exceededIp -> {

                String msg = "Was exceeded "+ config.getDuration() + " limit ";

                logger.info(delimiter);
                logger.info(exceededIp + " " + msg);

                IpRestrictionEntity restriction = new IpRestrictionEntity();
                restriction.setIp(exceededIp);
                restriction.setDescription(msg);

                ipRestrictionService.save(restriction);
            });

            System.exit(0);
        } catch (FileNotFoundException | ValidationException error) {
            logger.info(delimiter);
            logger.error(error.getMessage());
            System.exit(-1);
        }
    }
}
