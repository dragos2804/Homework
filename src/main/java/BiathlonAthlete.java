public class BiathlonAthlete {
    @CSVField(index = 0)
    int athleteNumber;
    @CSVField(index = 1)
    String athleteName;
    @CSVField(index = 2)
    String countryCode;
    @CSVField(index = 3)
    String skiTimeResult;
    @CSVField(index = 4)
    String firstShooting;
    @CSVField(index = 5)
    String secondShooting;
    @CSVField(index = 6)
    String thirdShooting;

    public BiathlonAthlete(int athleteNumber, String athleteName, String countryCode, String skiTimeResult,
                           String firstShooting, String secondShooting, String thirdShooting) {
        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.firstShooting = firstShooting;
        this.secondShooting = secondShooting;
        this.thirdShooting = thirdShooting;
    }
    public int getAthleteNumber() {
        return athleteNumber;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getSkiTimeResult() {
        return skiTimeResult;
    }

    public String getFirstShooting() {
        return firstShooting;
    }

    public String getSecondShooting() {
        return secondShooting;
    }
    public String getThirdShooting() {
        return thirdShooting;
    }
    public int calculateTotalTime() {
        int timeInSeconds = timeStringToSeconds(skiTimeResult);
        timeInSeconds += calculatePenalty(firstShooting) + calculatePenalty(secondShooting)
                + calculatePenalty(thirdShooting);
        return timeInSeconds;
    }

    private int timeStringToSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }

    private int calculatePenalty(String shootingResult) {
        int penalty = 0;
        for (char shot : shootingResult.toCharArray()) {
            if (shot == ShotResult.MISS.getSymbol()) {
                penalty += 10;
            }
        }
        return penalty;
    }
}

