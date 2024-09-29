
public class Bot extends BaseObject {
    Position pos;
    int dirX = 0;
    int dirY = 0;
    Uid uid;
    Bot(Position pos, int dirX, int dirY, Uid uid){
        this.pos=pos;
        this.dirY=dirY;
        this.dirX =dirX;
        this.uid=uid;
    }
}
