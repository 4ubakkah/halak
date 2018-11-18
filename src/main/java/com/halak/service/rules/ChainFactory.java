package com.halak.service.rules;

import org.springframework.stereotype.Service;

@Service
public class ChainFactory {

    public KalahGameChain kalahGameChain() {
        return new KalahGameChain();
    }

}
