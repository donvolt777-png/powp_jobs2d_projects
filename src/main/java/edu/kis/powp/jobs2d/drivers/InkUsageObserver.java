package edu.kis.powp.jobs2d.drivers;

public interface InkUsageObserver {
    void updateInkLevel(double currentLevel, double maxLevel);
}
