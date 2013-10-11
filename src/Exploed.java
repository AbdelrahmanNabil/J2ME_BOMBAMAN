import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

public class Exploed extends TiledLayer {
	int r;
	int c;
	  InputStream in ;
      Player player ;

	public Exploed(int columns, int rows) throws IOException {
		super(columns, rows, Image.createImage("/exp.png"), Maps.icell,
				Maps.icell);
		r = rows;
		c = columns;
		try{
		  in = getClass().getResourceAsStream("/boom.wav");
	       player = Manager.createPlayer(in, "audio/x-wav");
		  } catch (Exception e) {
	           
		    }
		// // TODO Auto-generated constructor stub
	}

	public void expl(int[][] w, int x, int y) {
	try {
		      
		      player.start();
		    } catch (Exception e) {
		           
		    }
		int i = y / Maps.icell;
		int j = x / Maps.icell;
		setCell(j, i, 1);
		if (i - 1 >= 0 && w[i - 1][j] != 1)
			setCell(j, i - 1, 2);
		if (j + 1 < Maps.w && w[i][j + 1] != 1)
			setCell(j + 1, i, 3);
		if (i + 1 < Maps.h && w[i + 1][j] != 1)
			setCell(j, i + 1, 4);
		if (j - 1 >= 0 && w[i][j - 1] != 1)
			setCell(j - 1, i, 5);
	}

	public void removeexp(MyNumbers n, int[][] w, int x, int y) {
		int i = y / Maps.icell;
		int j = x / Maps.icell;
		System.out.println(i);
		System.out.println(j);
		setCell(j, i, 0);
		if (i - 1 >= 0) {
			setCell(j, i - 1, 0);
			if (w[i - 1][j] == 2) {
				w[i - 1][j] = 0;
				if (Maps.all_num[Maps.levelnum][i - 1][j] != 0) {
					n.setCell(j, i - 1, Maps.all_num[Maps.levelnum][i - 1][j]);
				}

			}
		}
		if (j + 1 < Maps.w) {
			setCell(j + 1, i, 0);
			if (w[i][j + 1] == 2) {
				w[i][j + 1] = 0;
				if (Maps.all_num[Maps.levelnum][i][j + 1] != 0) {
					n.setCell(j + 1, i, Maps.all_num[Maps.levelnum][i][j + 1]);
				}

			}
		}
		if (i + 1 < Maps.h) {
			setCell(j, i + 1, 0);
			if (w[i + 1][j] == 2) {
				w[i + 1][j] = 0;
				if (Maps.all_num[Maps.levelnum][i + 1][j] != 0) {
					n.setCell(j, i + 1, Maps.all_num[Maps.levelnum][i + 1][j]);
				}
			}
		}
		if (j - 1 >= 0) {
			setCell(j - 1, i, 0);
			if (w[i][j - 1] == 2) {
				w[i][j - 1] = 0;
				if (Maps.all_num[Maps.levelnum][i][j - 1] != 0) {
					n.setCell(j - 1, i, Maps.all_num[Maps.levelnum][i][j - 1]);
				}
			}
		}
	}

	public void check(Bomber b, int x, int y) {
		int i = y / Maps.icell;
		int j = x / Maps.icell;
		int k = (b.bomber_pos.y - Maps.board) / Maps.icell;
		int l = (b.bomber_pos.x) / Maps.icell;
		if ((i == k && j == l) || (i + 1 == k && j == l)
				|| (i - 1 == k && j == l) || (i == k && j + 1 == l)
				|| (i == k && j - 1 == l)) {
			System.out.println("|||||||||||||||||||||||||||");
			b.isDead = true;
			b.explore();
		}
	}

	public void check(Ghost g, int x, int y) {
		int i = y / Maps.icell;
		int j = x / Maps.icell;
		int k = (g.pos.y - Maps.board) / Maps.icell;
		int l = (g.pos.x) / Maps.icell;
		if (((i == k && j == l) || (i + 1 == k && j == l)
				|| (i - 1 == k && j == l) || (i == k && j + 1 == l) || (i == k && j - 1 == l))
				&& !g.isdead && !g.isexp) {
			g.exp();
			Maps.score += 100;
		}
	}

}
