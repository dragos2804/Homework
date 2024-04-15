public enum ShotResult {
    HIT('x'), MISS('o');

    private char symbol;

    ShotResult(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}

