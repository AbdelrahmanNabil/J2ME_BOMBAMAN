import java.io.IOException;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

public class MyNumbers extends TiledLayer{

	public MyNumbers(int columns, int rows) throws IOException {
		super(columns, rows, Image.createImage("/number.png"), Maps.icell,Maps.icell);
		// TODO Auto-generated constructor stub
	}
}