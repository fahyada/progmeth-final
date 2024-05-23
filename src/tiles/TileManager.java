package tiles;

import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import panes.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileManager {
    private Tile[] tile;

    private int mapTileNum[][] = {{7, 8, 8, 8, 22, 8, 8, 8, 8, 22, 8, 8, 8, 8, 22, 15}, {7, 4, 5, 5, 28, 5, 5, 5, 5, 28, 5, 5, 5, 5, 28, 15},
            {29, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15}, {5, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15},
            {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15}, {17, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15},
            {7, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15}, {7, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15},
            {7, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15}, {7, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15},
            {7, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15}, {19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21}};

    public TileManager() {
        tile = new Tile[30];
        getTileImage();
    }

    public void getTileImage() {
            Image tileSheet = new Image(ClassLoader.getSystemResource("Tile/TileSheet.png").toString());
            PixelReader reader = tileSheet.getPixelReader();
            int i = 0;
            try {
                for (int row = 0; row < 6; row++) {
                    int col;
                    for (col = 0; col < 6; col++) {
                        if (i == 30) {
                            break;
                        }
                        tile[i] = new Tile();
                        tile[i].setImage(new WritableImage(reader, col*16, row*16, 16, 16));
                        i++;
                    }
                }

                tile[7].setCollision(true);
                tile[8].setCollision(true);
                tile[22].setCollision(true);
                tile[29].setCollision(true);
                tile[17].setCollision(true);
                tile[19].setCollision(true);
                tile[20].setCollision(true);
                tile[21].setCollision(true);
                tile[15].setCollision(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    public void draw(GraphicsContext gc) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < GamePanel.maxScreenCol && row < GamePanel.maxScreenRow) {
            int tileNum = mapTileNum[row][col];
            gc.drawImage(tile[tileNum].getImage(), x, y, GamePanel.tileSize, GamePanel.tileSize);
            col++;
            x += GamePanel.tileSize;
            if (col == GamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += GamePanel.tileSize;
            }
        }
    }

    public Tile[] getTile() {
        return tile;
    }

    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public void setMapTileNum(int[][] mapTileNum) {
        this.mapTileNum = mapTileNum;
    }
}