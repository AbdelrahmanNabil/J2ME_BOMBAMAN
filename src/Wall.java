import java.io.IOException;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

public class Wall extends TiledLayer {
	public Wall(int columns, int rows) throws IOException {
		super(columns, rows, Image.createImage("/terrain.png"), Maps.icell,
				Maps.icell);
		
		// TODO Auto-generated constructor stub
	}

}