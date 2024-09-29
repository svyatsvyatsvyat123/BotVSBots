import java.util.Random;

public class Server {
    public final int H = 100, W = 100;
    public BaseObject[][] map;
    public int[][] wires;
    public int[][] resources;

    public final int MaxTeams = 2, MaxBots = 1000;
    public Bot[][] bots;

    public Server() {
        map = new BaseObject[H][W];
        wires = new int[H][W];
        resources = new int[H][W];
        bots = new Bot[MaxTeams][MaxBots];
    }

    public Server(Random rnd) {
        this();
        int bot_cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                int x = rnd.nextInt(0, 10);
                if (x == 0) {
                    if (bot_cnt>=MaxBots)continue;;
                    int dir = rnd.nextInt(0, 4);
                    int dirX = 0;
                    int dirY = 0;
                    if (dir == 0) {
                        dirX = 1;
                        dirY = 0;
                    }
                    if (dir == 1) {
                        dirX = 0;
                        dirY = 1;
                    }
                    if (dir == 2) {
                        dirX = -1;
                        dirY = 0;
                    }
                    if (dir == 3) {
                        dirX = 0;
                        dirY = -1;
                    }
                    bots[0][bot_cnt] = new Bot(new Position(j, i), dirX, dirY, new Uid(bot_cnt, 0));
                    map[i][j] = bots[0][bot_cnt];
                    bot_cnt++;
                }
            }
        }
    }

    public Boolean checkPosition(Position pos) {
        return pos.x >= 0 && pos.x < W && pos.y >= 0 && pos.y < H;
    }

    private BaseObject getObj(Position pos) throws MoveException {
        if (!checkPosition((pos))) throw new MoveException("pos not exists");
        return map[pos.y][pos.x];
    }

    private Bot getBot(Uid id) throws UidException {
        if (id.id < 0 || id.id > MaxBots || id.team < 0 || id.team > MaxTeams)
            throw new UidException("Bot id not found", id);
        return bots[id.team][id.id];
    }

    public void swap(Position x, Position y) throws MoveException {
        BaseObject t = getObj(x);
        map[x.y][x.x] = getObj(y);
        map[y.y][y.x] = t;
    }

    public void move(Uid id, char tp) throws MoveException, UidException {
        Position pos = getBot(id).pos;
        switch (tp) {
            case ('U'):
                pos.y -= 1;
                break;
            case ('D'):
                pos.y += 1;
                break;
            case ('L'):
                pos.x -= 1;
                break;
            case ('R'):
                pos.x += 1;
                break;
            default:
                throw new DirectException(String.format("Direction must be U D L or R you give %c", tp));
        }
        if (getObj(pos).isEmpty()) {
            swap(pos, getBot(id).pos);
        }
    }

    public void rotate(Uid id, char dir) throws MoveException, UidException {
        Bot bot = getBot(id);
        switch (dir) {
            case ('U'):
                bot.dirX = 0;
                bot.dirY = -1;
                break;
            case ('D'):
                bot.dirX = 0;
                bot.dirY = 1;
                break;
            case ('L'):
                bot.dirX = -1;
                bot.dirY = 0;
                break;
            case ('R'):
                bot.dirX = 1;
                bot.dirY = 0;
                break;
            default:
                throw new DirectException(String.format("Direction must be U D L or R you give %c", dir));
        }
    }
}

