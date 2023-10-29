package sk.rudo.object;

import sk.rudo.entity.Entity;
import sk.rudo.gameService.Panel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Object_Life extends Entity {

    public Object_Life(Panel panel) {
        super(panel);

        getImage();

    }
    public void getImage () {
        try {
            lifeImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/heart/full.png")));
            lifeImageHalf = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/heart/half.png")));
            lifeImageBlank = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/heart/blank.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
