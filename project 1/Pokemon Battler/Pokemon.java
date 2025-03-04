public class Pokemon {
    // Global variables
    private int hp;
    private int attack;
    private int defense;
    private int speed;

    // Getters
    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    // Setters
    public void setHp(int userHp) {
        hp = Math.max(userHp, 0); // Ensure HP is never negative
    }

    public void setAttack(int userAttack) {
        this.attack = userAttack;
    }

    public void setDefense(int userDefense) {
        defense = userDefense;
    }

    public void setSpeed(int userSpeed) {
        speed = userSpeed;
    }
}
