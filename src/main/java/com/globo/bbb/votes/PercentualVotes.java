package com.globo.bbb.votes;

public class PercentualVotes {

    private double pc1;
    private double pc2;

    PercentualVotes(long total1, long total2) {
        final long total = total1 + total2;
        if (total > 0) {
            this.pc1 = total1 * 100f / total;
            this.pc2 = total2 * 100f / total;
        } else {
            this.pc1 = this.pc2 = 0;
        }
    }

    public double getPc1() {
        return pc1;
    }

    public double getPc2() {
        return pc2;
    }
}
