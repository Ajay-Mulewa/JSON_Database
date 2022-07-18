interface Flying {
    int getHeight();

    default int getHeightInKm() {
        return getHeight() / 1000;
    }
}