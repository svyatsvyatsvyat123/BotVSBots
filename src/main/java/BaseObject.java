public class BaseObject {
    int health;
    int resources;

    public BaseObject() {
        health = 0;
        resources = 0;
    }

    public BaseObject(int h, int r) {
        health = h;
        resources = r;
    }

    public int kill() {
        health = 0;
        int temp = resources;
        resources = 0;
        return temp;
    }

    public Boolean isEmpty() {
        return health != 0;
    }

    public void print() {
        System.out.println(health);
    }

    public int damage(int dmg) {
        if (health != 0 && health - dmg <= 0) {
            return kill();
        }
        health -= dmg;
        return 0;
    }
}
