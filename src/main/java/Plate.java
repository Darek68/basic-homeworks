public class Plate {
    int foodMax;
    int foorCur;

    public Plate(int foodMax) {
        this.foodMax = foodMax;
        this.foorCur = this.foodMax;
    }

    public void addFood(int foot) {
        this.foorCur += foot;
        if (this.foorCur > this.foodMax) this.foorCur = this.foodMax;
    }

    public boolean reduceFood(int foot) {
        if (foot > this.foorCur) return false;
        this.foorCur -= foot;
        return true;
    }
}

