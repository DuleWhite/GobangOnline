package client.frame;

import client.data.Data;
import client.listener.ChessBoardListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessBoardCanvas extends Canvas {
    /**
     * 棋盘画板
     */
    private static final long serialVersionUID = 1L;
    String sourcePath = null;
    private int MAP_WIDTH = 531;
    private int MAP_HEIGHT = 531;
    // 缓存图片
    BufferedImage chessBoardImage = new BufferedImage(MAP_WIDTH, MAP_HEIGHT, 1);
    Graphics2D g2 = chessBoardImage.createGraphics();

    // 构造函数
    public ChessBoardCanvas() {
        this.paintMapImage();
        this.setSize(MAP_WIDTH, MAP_HEIGHT);
        this.addMouseListener(new ChessBoardListener());
    }

    // 重写paint
    public void paint(Graphics g) {
        g.drawImage(chessBoardImage, 0, 0, null);
    }

    public void paintMapImage() {
        this.paintBackground();
        this.paintChess();
    }

    private void paintBackground() {

        // 设置背景图片
        try {
            Image background = null;
            String path = this.getSourcePath() + "/src/client/images/map.png";
            background = ImageIO.read(new File(path));
            g2.drawImage(background, 0, 0, null);
        } catch (IOException e1) {
            this.setBackground(new Color(210, 180, 140));
            g2.setColor(Color.black);
            for (int i = 0; i < 15; i++) {
                g2.drawLine((35 * i + 20), 20, (35 * i + 20), 510);
            }
            for (int i = 0; i < 15; i++) {
                g2.drawLine(20, (35 * i + 20), 510, (35 * i + 20));
            }
            g2.fillRect(122, 122, 7, 7);
            g2.fillRect(402, 122, 7, 7);
            g2.fillRect(122, 402, 7, 7);
            g2.fillRect(402, 402, 7, 7);
            g2.fillRect(262, 262, 7, 7);
            e1.printStackTrace();
        }
    }

    private void paintChess() {
        Image black = null;
        BufferedImage white = null;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (Data.chessBoard[i][j] == Data.BLACK) {
                    try {
                        if (15 * j + i == Data.last) {
                            black = ImageIO.read(new File(this.getSourcePath()
                                    + "/src/client/images/black2.png"));
                        } else {
                            black = ImageIO.read(new File(this.getSourcePath()
                                    + "/src/client/images/black.png"));
                        }
                        g2.drawImage(black, i * 35 + 4, j * 35 + 4, null);
                    } catch (IOException e) {
                        g2.fillOval(i * 35 + 4, j * 35 + 4, 33, 33);
                        e.printStackTrace();
                    }
                } else {
                    if (Data.chessBoard[i][j] == Data.WHITE) {
                        try {
                            if (15 * j + i == Data.last) {
                                white = ImageIO.read(new File(this
                                        .getSourcePath()
                                        + "/src/client/images/white2.png"));
                            } else {
                                white = ImageIO.read(new File(this
                                        .getSourcePath()
                                        + "/src/client/images/white.png"));
                            }
                            g2.drawImage(white, i * 35 + 4, j * 35 + 4, null);
                        } catch (IOException e) {

                            // 手动画
                            g2.setColor(Color.white);
                            g2.fillOval(i * 35 + 4, j * 35 + 4, 33, 33);
                            g2.setColor(Color.black);

                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private String getSourcePath() {
        if (sourcePath == null) {
            sourcePath = new File("").getAbsolutePath();
        }
        return sourcePath;
    }

    public int getMapWidth() {
        return MAP_WIDTH;
    }

    public int getMapHeight() {
        return MAP_HEIGHT;
    }

}
