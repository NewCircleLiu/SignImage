import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;

public class BmpReader {

    public static int[][] ReadBMPPic(String src) throws IOException
    {
    	try {
			// 创建读取文件的字节流
			FileInputStream fis = new FileInputStream(src);
			BufferedInputStream bis = new BufferedInputStream(fis);
			// 读取时丢掉前面的18位，
			// 读取图片的18~21的宽度
			bis.skip(18);
			byte[] b = new byte[4];
			bis.read(b);
			// 读取图片的高度22~25
			byte[] b2 = new byte[4];
			bis.read(b2);

			// 得到图片的高度和宽度
			int width = byte2Int(b);
			int heigth = byte2Int(b2);
			// 使用数组保存得图片的高度和宽度
			int[][] date = new int[heigth][width];

			int skipnum = 0;
			if (width * 3 / 4 != 0) {
				skipnum = 4 - width * 3 % 4;
			}
			// 读取位图中的数据，位图中数据时从54位开始的，在读取数据前要丢掉前面的数据
			bis.skip(28);
			for (int i = 0; i < date.length; i++) {
				for (int j = 0; j < date[i].length; j++) {
					// bmp的图片在window里面世3个byte为一个像素
					int blue = bis.read();
					int green = bis.read();
					int red = bis.read();
					if(red==-1 || green==-1 || blue==-1)
					{
						continue;
					}
					// 创建一个Color对象，将rgb作为参数传入其中
					Color c = new Color((int)red, (int)green, (int)blue);
					// Color c = new Color(blue,green,red);
					// 将得到的像素保存到date数组中
					date[i][j] = c.getRGB();
				}
				// 如果补0的个数不为0，则需要跳过这些补上的0
				if (skipnum != 0) {
					bis.skip(skipnum);
				}
			}
			return date;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
    }
    private static int byte2Int(byte[] b) throws IOException {
        // TODO Auto-generated method stub
    	int t1 = b[3] & 0xff;
		int t2 = b[2] & 0xff;
		int t3 = b[1] & 0xff;
		int t4 = b[0] & 0xff;
		int num = t1 << 24 | t2 << 16 | t3 << 8 | t4;
		return num;
    }

}