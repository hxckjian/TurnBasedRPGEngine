package core.Monsters;

public class Ghost extends Monster {
    private Ghost() {
        super("Ghost", 100, 20);
    }

    public static Ghost creation() {
        return new Ghost();
    }
}

