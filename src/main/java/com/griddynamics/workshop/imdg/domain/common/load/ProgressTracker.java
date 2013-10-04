package com.griddynamics.workshop.imdg.domain.common.load;

class ProgressTracker {

    private final long totalCount;
    private final long startTime;

    private long lastUpdateTime;
    private long remainingCount;
    private long completedCount;
    private double completedPercentage;
    private double averageSpeed;
    private long remainingTime;

    private long completedCountRecords;
    private double averageSpeedRecords;

    public ProgressTracker(long totalCount) {
        this.totalCount = totalCount;
        this.startTime = System.currentTimeMillis();
        this.lastUpdateTime = this.startTime;
    }

    public void update(long completedCount, long completedCountRecords) {
        long difference = completedCount - this.completedCount;
        long differenceRecords = completedCountRecords - this.completedCountRecords;

        this.completedCountRecords = completedCountRecords;
        this.completedCount = completedCount;
        this.remainingCount = totalCount - completedCount;
        this.completedPercentage = (double) completedCount / totalCount * 100;

        long currentTime = System.currentTimeMillis();
        averageSpeed = ((double) difference) / ((double) (currentTime - lastUpdateTime)) * 1000;
        averageSpeedRecords = ((double) differenceRecords) / ((double) (currentTime - lastUpdateTime)) * 1000;
        if (Math.abs(averageSpeed) > 1e-6) {
            remainingTime = (long) (remainingCount / averageSpeed);
        } else {
            remainingTime = Integer.MAX_VALUE;
        }
        lastUpdateTime = currentTime;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public long getCompletedCount() {
        return completedCount;
    }

    public double getCompletedPercentage() {
        return completedPercentage;
    }

    public long getRemainingCount() {
        return remainingTime;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getCompletedCountRecords() {
        return completedCountRecords;
    }

    public double getAverageSpeedRecords() {
        return averageSpeedRecords;
    }
}
