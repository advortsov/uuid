package ru.advortsov.dsg.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.UUID;

@Component
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

    @Override
    public String nextId() {
        String hex = UUID.randomUUID().toString().replace("-", "");
        return new BigInteger(hex, 16).toString();
    }

}