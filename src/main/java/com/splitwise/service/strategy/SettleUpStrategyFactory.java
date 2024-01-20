package com.splitwise.service.strategy;

public class SettleUpStrategyFactory {
    public static SettleUpStrategy getSettleUpStrategy(SettleUpStrategies strategyName){
        return new HeapBasedSettleUpStrategy();
    }
}