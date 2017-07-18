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
			// ������ȡ�ļ����ֽ���
			FileInputStream fis = new FileInputStream(src);
			BufferedInputStream bis = new BufferedInputStream(fis);
			// ��ȡʱ����ǰ���18λ��
			// ��ȡͼƬ��18~21�Ŀ��
			bis.skip(18);
			byte[] b = new byte[4];
			bis.read(b);
			// ��ȡͼƬ�ĸ߶�22~25
			byte[] b2 = new byte[4];
			bis.read(b2);

			// �õ�ͼƬ�ĸ߶ȺͿ��
			int width = byte2Int(b);
			int heigth = byte2Int(b2);
			// ʹ�����鱣���ͼƬ�ĸ߶ȺͿ��
			int[][] date = new int[heigth][width];

			int skipnum = 0;
			if (width * 3 / 4 != 0) {
				skipnum = 4 - width * 3 % 4;
			}
			// ��ȡλͼ�е����ݣ�λͼ������ʱ��54λ��ʼ�ģ��ڶ�ȡ����ǰҪ����ǰ�������
			bis.skip(28);
			for (int i = 0; i < date.length; i++) {
				for (int j = 0; j < date[i].length; j++) {
					// bmp��ͼƬ��window������3��byteΪһ������
					int blue = bis.read();
					int green = bis.read();
					int red = bis.read();
					if(red==-1 || green==-1 || blue==-1)
					{
						continue;
					}
					// ����һ��Color���󣬽�rgb��Ϊ������������
					Color c = new Color((int)red, (int)green, (int)blue);
					// Color c = new Color(blue,green,red);
					// ���õ������ر��浽date������
					date[i][j] = c.getRGB();
				}
				// �����0�ĸ�����Ϊ0������Ҫ������Щ���ϵ�0
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