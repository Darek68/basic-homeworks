public class Cat {
    String name;
    int appetite;
    boolean satiated = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    @Override
    public String toString() {
        if (this.satiated) {
            return "Кот " + this.name + " сытый!";
        } else {
            return "Кот " + this.name + " голодный!";
        }

    }
}
