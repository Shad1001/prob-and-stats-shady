public class Stadium {
    public void battle(Pokemon p1, Pokemon p2) {
        Pokemon first = (p1.getSpeed() >= p2.getSpeed()) ? p1 : p2, second = (first == p1) ? p2 : p1;
        while (p1.getHp() > 0 && p2.getHp() > 0) attack(first, second = (second.getHp() > 0) ? first : second);
        System.out.println((p1.getHp() > 0 ? p1 : p2).getClass().getSimpleName() + " wins!");
    }
    private void attack(Pokemon a, Pokemon b) {
        b.setHp(b.getHp() - Math.max(a.getAttack() - b.getDefense(), 0));
    }
}
