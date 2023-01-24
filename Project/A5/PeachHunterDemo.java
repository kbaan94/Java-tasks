import java.util.ArrayList;
import java.util.List;

public class PeachHunterDemo {
    public static void main(String[] args){
        World world = new World();
        PeachPit pit = new PeachPit(new Position(0,1), "Pit Location:",new ArrayList<Player>(),new ArrayList<Peach>());

        List<Peach> peachList = new ArrayList<Peach>();

        PeachHunter hunter = new PeachHunter(world, "hunter", world.getHome(),peachList, 100,RGB.BLUE);





    }


}
