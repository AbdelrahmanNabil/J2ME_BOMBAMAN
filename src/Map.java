import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;

public class Map extends LayerManager {
	boolean cangonext=false;
	Image mBmpMap;
	Image sto;
	Random rand = new Random();
	Random rand1 = new Random();
	Image mBmpTerrain;
	Bomber bomber;
	Bomb[] bomb;
	Ghost[] ghost;
	Exploed expl;
	Wall wall;
	static int Mod = 5;
	MyNumbers numbers;
	boolean check = true;
	boolean up = true;
	boolean left = true;
	boolean right = true;
	boolean dowb = true;
	String text = "Score : ";
	String text2 = Maps.all_fun[Maps.levelnum];
	String text3 = "spirits : ";
	public boolean isGameOver = false;
	boolean iswin = false;
	static int[][] matrixMap;
	static int[][] number;
	int width, height;
	int B_RIGHT = 240, B_TOP = Maps.board, B_LEFT = 0, B_BOTTOM = 231;
	public static int iECell = Maps.icell;

	public Map(int iScene) {
		try {
			sto = Image.createImage("/score.png");
			Maps.bombs = Maps.levelnum + 1;
			rand.setSeed(12345);
			rand1.setSeed(437834);
			number = Maps.all_num[Maps.levelnum];
			width = Maps.w;
			height = Maps.h;
			mBmpMap = Image.createImage("/map.png");
			mBmpTerrain = Image.createImage("/terrain.png");
			bomber = new Bomber(new Point(Maps.playerx * iECell, Maps.playery
					* iECell + Maps.board));
			bomber.setPosition(bomber.bomber_pos.x, bomber.bomber_pos.y);
			ghost = new Ghost[10];
			for (int i = 0; i < Maps.numofghost[Maps.levelnum]; i++) {
				ghost[i] = new Ghost(new Point(
						Maps.all_ghost[Maps.levelnum][i][0] * iECell,
						Maps.all_ghost[Maps.levelnum][i][1] * iECell
								+ Maps.board));
				ghost[i].setPosition(ghost[i].pos.x, ghost[i].pos.y);
				this.append(ghost[i]);
			}
			bomb = new Bomb[3];
			bomb[0] = new Bomb();
			bomb[1] = new Bomb();
			bomb[2] = new Bomb();
			wall = new Wall(width, height);
			numbers = new MyNumbers(width, height);
			expl = new Exploed(width, height);
			expl.setPosition(0, Maps.board);
			wall.setPosition(0, Maps.board);
			numbers.setPosition(0, Maps.board);
			matrixMap = Maps.all_map[Maps.levelnum];
			this.append(bomber);

			this.append(expl);
			this.append(wall);
			this.append(bomb[0]);
			this.append(bomb[1]);
			this.append(bomb[2]);
			this.append(numbers);
			for (int i = 0; i < Maps.ghost_move.length; i++) {
				Maps.ghost_move[i] = ((rand.nextInt() % 7) + 7) % 7;
			}
		} catch (Exception ex) {
			System.out.print("Could not find map bg file. This is why: " + ex);
		}
	}

	public void Advance() {
		int width = Main.mycanvas.getWidth();
		int height = Main.mycanvas.getHeight();

		int x = bomber.bomber_pos.x - width / 2;
		int y = bomber.bomber_pos.y - height / 2 - 31;

		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (bomber.bomber_pos.x + width / 2 > Maps.w * iECell)
			x = Maps.w * iECell - width;

		if (y + height - Maps.board > Maps.h * iECell)
			y = Maps.h * iECell - height + Maps.board;
		setViewWindow(x, y, mBmpMap.getWidth(), mBmpMap.getHeight());
		bomber.Animate();
		for (int i = 0; i < Maps.numofghost[Maps.levelnum]; i++) {
			ghost[i].Animate();
			GhostMove(i);
		}
		for (int i = 0; i < 3; i++) {
			if (bomb[i].isActive || bomb[i].isexp) {
				bomb[i].Animate(numbers, matrixMap, expl, bomber, ghost);
			}
		}
		if (check) {
			boolean res = false;
			for (int i = 0; i < Maps.numofghost[Maps.levelnum]; i++) {
				res = res
						|| (isGameOver == false && bomber.collidesWith(
								ghost[i], true));
			}
			if (res && !bomber.isExplore) {
				bomber.explore();
			}
			if (bomber.isDead || res) {
				if (Maps.sprits != 0) {
					Maps.sprits--;
					check = false;
					bomber.isDead = false;
				} else {
					isGameOver = true;
				}
			}
		} else {
			check = true;
			for (int i = 0; i < Maps.numofghost[Maps.levelnum]; i++)
				check = check && !bomber.collidesWith(ghost[i], true);

		}
	}

	public void Quite() {
		bomber.StopAnimated();
	}

	public void GhostMove(int i) {
		rand.setSeed(((rand1.nextInt() % 91234) + 91234) % 91234);
		int cas;
		// for(int i=0;i<Maps.numofghost[Maps.levelnum];i++){
		cas = Maps.ghost_move[i];
		switch (cas) {
		case 0:
			if (!gup(ghost[i])) {
				Maps.ghost_move[i] = ((rand.nextInt() % Mod) + Mod) % Mod;
			}
			break;
		case 1:

			if (!gdown(ghost[i])) {
				Maps.ghost_move[i] = ((rand.nextInt() % Mod) + Mod) % Mod;
			}
			break;
		case 2:
			if (!gleft(ghost[i])) {
				Maps.ghost_move[i] = ((rand.nextInt() % Mod) + Mod) % Mod;
			}
			break;
		case 3:
			if (!gright(ghost[i])) {
				Maps.ghost_move[i] = ((rand.nextInt() % Mod) + Mod) % Mod;
			}
			break;
		default:
			Maps.ghost_move[i] = ((rand.nextInt() % Mod) + Mod) % Mod;
			break;
		}
		ghost[i].Move();
		// }
	}

	public boolean Left() {
		if (bomber.isExplore) {
			return false;
		}
		// for(int i=0;i<3;i++){
		// if(bomber.collidesWith(bomb[i], false)){
		// return false;
		// }}
		int i = (bomber.bomber_pos.x - Bomber.moveSpeed) / iECell;
		int j = (bomber.bomber_pos.y - Maps.board) / iECell;
		int im = (bomber.bomber_pos.x - Bomber.moveSpeed) % iECell;
		int jm = (bomber.bomber_pos.y - Maps.board) % iECell;
		boolean can = true;
		try {
			if (bomber.bomber_pos.x > B_LEFT) {
				if (matrixMap[j][i] != 0)
					can = false;
				if (jm != 0) {
					if (matrixMap[j + 1][i] != 0)
						can = false;
					if (im != 0 && matrixMap[j + 1][i + 1] != 0)
						can = false;
				}
				if (im != 0 && matrixMap[j][i] != 0)
					can = false;
			} else {
				can = false;
			}
		} catch (Exception e) {
			can = false;
		}
		if (can) {
			check(0);
			bomber.move_left();
			// bomber.Animate();
		}// System.out.println(can);
		return can;
	}

	public boolean Right() {
		if (bomber.isExplore) {
			return false;
		}
		// for(int i=0;i<3;i++){
		// if(bomber.collidesWith(bomb[i], false)){
		// return false;
		// }}
		int i = (bomber.bomber_pos.x + Bomber.moveSpeed) / iECell;
		int j = (bomber.bomber_pos.y - Maps.board) / iECell;
		int im = (bomber.bomber_pos.x + Bomber.moveSpeed) % iECell;
		int jm = (bomber.bomber_pos.y - Maps.board) % iECell;
		boolean can = true;
		try {
			if (i < B_RIGHT) {
				if (matrixMap[j][i] != 0)
					can = false;
				if (jm != 0) {
					if (matrixMap[j + 1][i] != 0)
						can = false;
					if (im != 0 && matrixMap[j + 1][i + 1] != 0)
						can = false;
				}
				if (im != 0 && matrixMap[j][i + 1] != 0)
					can = false;
			} else {
				can = false;
			}
		} catch (Exception e) {
			can = false;
		}
		if (can) {
			check(1);
			bomber.move_right();
			// bomber.Animate();
		}
		return can;

	}

	public boolean Up() {
		if (bomber.isExplore) {
			return false;
		}
		// for(int i=0;i<3;i++){
		// if(bomber.collidesWith(bomb[i], false)){
		// return false;
		// }}
		int i = (bomber.bomber_pos.x) / iECell;
		int j = (bomber.bomber_pos.y - Maps.board - Bomber.moveSpeed) / iECell;
		int im = (bomber.bomber_pos.x) % iECell;
		int jm = (bomber.bomber_pos.y - Maps.board - Bomber.moveSpeed) % iECell;
		// System.out.println(bomber.yPos);
		// System.out.println(i+"   "+j+"     "+ im +"       "+jm);
		boolean can = true;
		try {
			if (bomber.bomber_pos.y > B_TOP) {
				if (matrixMap[j][i] != 0) {
					can = false;
				}
				if (im != 0) {
					if (matrixMap[j][i + 1] != 0)
						can = false;
					if (jm != 0 && matrixMap[j + 1][i + 1] != 0)
						can = false;
				}
				if (jm != 0 && matrixMap[j][i] != 0)
					can = false;
			} else {
				can = false;
			}
		} catch (Exception e) {
			can = false;
		}
		if (can) {
			check(2);
			bomber.MoveUp();
			// bomber.Animate();
		}
		return can;
	}

	public boolean Down() {
		if (bomber.isExplore) {
			return false;
		}
		// for(int i=0;i<3;i++){
		// if(bomber.collidesWith(bomb[i], false)){
		// return false;
		// }}
		int i = (bomber.bomber_pos.x) / iECell;
		int j = (bomber.bomber_pos.y - Maps.board + Bomber.moveSpeed) / iECell;
		int im = (bomber.bomber_pos.x) % iECell;
		int jm = (bomber.bomber_pos.y - Maps.board + Bomber.moveSpeed) % iECell;
		// System.out.println(i+"   "+j+"     "+ im +"       "+jm);
		boolean can = true;
		try {
			if (j < B_BOTTOM) {
				if (matrixMap[j][i] != 0) {
					can = false;
				}
				if (im != 0) {
					if (matrixMap[j][i + 1] != 0)
						can = false;
					if (jm != 0 && matrixMap[j + 1][i + 1] != 0)
						can = false;
				}
				if (jm != 0 && matrixMap[j + 1][i] != 0)
					can = false;
			} else {
				can = false;
			}
		} catch (Exception e) {
			can = false;
		}
		if (can) {
			check(3);
			bomber.MoveDown();
			// bomber.Animate();
		}
		return can;

	}

	public void PlaceBomb() throws Exception {
		if (Maps.bombs > 0) {
			int i = 0;
			while (i < 3) {
				if (!bomb[i].isActive) {
					break;
				}
				i++;
			}
			int x_pos1 = Math.abs(((bomber.bomber_pos.x / iECell) * iECell)
					- bomber.bomber_pos.x);
			int y_pos1 = Math.abs(((bomber.bomber_pos.y / iECell) * iECell)
					- bomber.bomber_pos.y);
			int x_pos2 = Math
					.abs((((bomber.bomber_pos.x + iECell) / iECell) * iECell)
							- bomber.bomber_pos.x);
			int y_pos2 = Math
					.abs((((bomber.bomber_pos.y + iECell) / iECell) * iECell)
							- bomber.bomber_pos.y);
			int bomb_x = 0;
			int bomb_y = 0;
			if (x_pos1 < x_pos2)
				bomb_x = (bomber.bomber_pos.x / iECell) * iECell;
			else
				bomb_x = ((bomber.bomber_pos.x + iECell) / iECell) * iECell;
			if (y_pos1 < y_pos2)
				bomb_y = (bomber.bomber_pos.y / iECell) * iECell;
			else
				bomb_y = ((bomber.bomber_pos.y + iECell) / iECell) * iECell;

			bomb[i].setPosition(bomb_x, bomb_y);
			bomb[i].bx = bomb_x;
			bomb[i].by = bomb_y - Maps.board;
			bomb[i].Active();
			Maps.bombs--;

		}
	}

	public void DrawTerrain() {
		for (int j = 0; j < width; j++){
			for (int i = 0; i < height; i++) {
				// Point p;
				
					wall.setCell(j, i, matrixMap[i][j]);
				
			}}
		
		if(cangonext){
			wall.setCell(6, 9, 4);
		}
	}

	public void Draw(Graphics g) {
		g.drawImage(mBmpMap, 0, 0, Graphics.TOP | Graphics.LEFT);
		for (int i = 0; i < Maps.numofghost[Maps.levelnum]; i++) {
			if (ghost[i].isdead) {
				remove(ghost[i]);
				ghost[i].pos.x = -50;
				ghost[i].pos.y = -50;
			}
		}

		DrawTerrain();
	}

	public void DrawScore(Graphics g) {
		g.drawImage(sto, 0, 0, Graphics.TOP | Graphics.LEFT);
		g.drawString(text + Maps.score, 0, 0, Graphics.TOP | Graphics.LEFT);
		g.drawString(text2, 0, 20, Graphics.TOP | Graphics.LEFT);
		g.drawString(text3 + Maps.sprits, 190, 0, Graphics.TOP | Graphics.RIGHT);
	}

	public boolean gdown(Ghost g) {
		int i = (g.pos.x) / iECell;
		int j = (g.pos.y - Maps.board + 1) / iECell;
		int im = (g.pos.x) % iECell;
		int jm = (g.pos.y - Maps.board + 1) % iECell;
		boolean can = true;
		try {
			if (j < B_BOTTOM) {
				if (matrixMap[j][i] != 0) {
					can = false;
				}
				if (im != 0) {
					if (matrixMap[j][i + 1] != 0)
						can = false;
					if (jm != 0 && matrixMap[j + 1][i + 1] != 0)
						can = false;
				}
				if (jm != 0 && matrixMap[j + 1][i] != 0)
					can = false;
			} else {
				can = false;
			}
		} catch (Exception e) {
			can = false;
		}
		if (can) {
			g.pos.y++;
			for (int k = 0; k < 3; k++) {
				if (g.collidesWith(bomb[k], false)) {
					can = false;
					break;
				}
			}
			if (!can) {
				g.pos.y -= 2;
			}
		}
		return can;
	}

	public boolean gleft(Ghost g) {
		int i = (g.pos.x - 1) / iECell;
		int j = (g.pos.y - Maps.board) / iECell;
		int im = (g.pos.x - 1) % iECell;
		int jm = (g.pos.y - Maps.board) % iECell;
		boolean can = true;
		try {
			if (g.pos.x > B_LEFT) {
				if (matrixMap[j][i] != 0)
					can = false;
				if (jm != 0) {
					if (matrixMap[j + 1][i] != 0)
						can = false;
					if (im != 0 && matrixMap[j + 1][i + 1] != 0)
						can = false;
				}
				if (im != 0 && matrixMap[j][i] != 0)
					can = false;
			} else {
				can = false;
			}
		} catch (Exception e) {
			can = false;
		}
		if (can) {
			g.pos.x--;
			for (int k = 0; k < 3; k++) {
				if (g.collidesWith(bomb[k], false)) {
					can = false;
					break;
				}
			}
			if (!can) {
				g.pos.x += 2;
			}
		}
		return can;
	}

	public boolean gright(Ghost g) {

		int i = (g.pos.x + 1) / iECell;
		int j = (g.pos.y - Maps.board) / iECell;
		int im = (g.pos.x + 1) % iECell;
		int jm = (g.pos.y - Maps.board) % iECell;
		boolean can = true;
		try {
			if (i < B_RIGHT) {
				if (matrixMap[j][i] != 0)
					can = false;
				if (jm != 0) {
					if (matrixMap[j + 1][i] != 0)
						can = false;
					if (im != 0 && matrixMap[j + 1][i + 1] != 0)
						can = false;
				}
				if (im != 0 && matrixMap[j][i + 1] != 0)
					can = false;
			} else {
				can = false;
			}
		} catch (Exception e) {
			can = false;
		}
		if (can) {
			g.pos.x++;
			for (int k = 0; k < 3; k++) {
				if (g.collidesWith(bomb[k], false)) {
					can = false;
					break;
				}
			}
			if (!can) {
				g.pos.x -= 2;
				;
			}
		}
		return can;
	}

	public boolean gup(Ghost g) {

		int i = (g.pos.x) / iECell;
		int j = (g.pos.y - Maps.board - 1) / iECell;
		int im = (g.pos.x) % iECell;
		int jm = (g.pos.y - Maps.board - 1) % iECell;
		// System.out.println(bomber.yPos);
		// System.out.println(i+"   "+j+"     "+ im +"       "+jm);
		boolean can = true;
		try {
			if (g.pos.y > B_TOP) {
				if (matrixMap[j][i] != 0) {
					can = false;
				}
				if (im != 0) {
					if (matrixMap[j][i + 1] != 0)
						can = false;
					if (jm != 0 && matrixMap[j + 1][i + 1] != 0)
						can = false;
				}
				if (jm != 0 && matrixMap[j][i] != 0)
					can = false;
			} else {
				can = false;
			}
		} catch (Exception e) {
			can = false;
		}
		if (can) {
			g.pos.y--;
			for (int k = 0; k < 3; k++) {
				if (g.collidesWith(bomb[k], false)) {
					can = false;
					break;
				}
			}
			if (!can) {
				g.pos.y += 2;
			}
		}

		return can;
	}

	public void check(int direction) {
		int i = (bomber.bomber_pos.x) / iECell;
		int j = (bomber.bomber_pos.y - Maps.board) / iECell;
		if( ((bomber.bomber_pos.x) % iECell!=0)&&direction==1){
			i++;
		}
		if( ((bomber.bomber_pos.y) % iECell!=0)&&direction==3){
			j++;
		}
		if(cangonext){
			if(wall.getCell(i, j)==4){
				if (Maps.levelnum == 2) {
					iswin = true;
					return;
				}
				Maps.levelnum++;
				MyGameCanvas.map = new Map(1);
				return;
		
			}
		}

		if (bomber.collidesWith(numbers, true)) {
			if (numbers.getCell(i, j) != Maps.solution[Maps.levelnum]) {
				bomber.isDead = true;
				bomber.explore();
			} else {
				matrixMap[9][6]=0;
				cangonext=true;
			}
			numbers.setCell(i, j, 0);
			matrixMap[j][i] = 0;
		}

	}

}

class Point {
	public int x;
	public int y;

	public Point(int _x, int _y) {
		x = _x;
		y = _y;
	}

	public Point() {

	}
}
