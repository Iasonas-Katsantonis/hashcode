class Pizza {
    final int index;
    final int slices;

    Pizza(int index, int slices) {
        this.index = index;
        this.slices = slices;
    }

    @Override
    public String toString() {
        return String.valueOf(slices);
    }
}
