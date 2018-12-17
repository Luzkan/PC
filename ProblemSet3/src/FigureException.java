class FigureException extends Exception {
    private String message;

    public FigureException(String var1) {
        this.message = var1;
    }

    public String getMsg() {
        return this.message;
    }
}
