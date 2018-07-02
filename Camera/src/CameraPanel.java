import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class CameraPanel extends JPanel implements Runnable , ActionListener {

	
	
	BufferedImage image;
	VideoCapture capture;
	JButton screensort;
	
	CameraPanel()
	{
		screensort = new JButton("screensort");
		screensort.addActionListener(this);
		add(screensort);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.loadLibrary("opencv_java341");
		capture= new VideoCapture(0);
		Mat webcam_image = new Mat();
		if(capture.isOpened())
		{
			
			while(true)
			{
				capture.read(webcam_image);
				if(!webcam_image.empty())
				{
					matToBufferedImage(webcam_image);
					repaint();
				}
			}
		}
	}

	
	
    public void	paintComponent(Graphics g)
  
    {
	super.paintComponent(g);
	if(this.image==null)return;
	g.drawImage(image, 10, 40, image.getWidth(), image.getHeight(), null);
   }




public void matToBufferedImage( Mat matBGR)
{
	int width = matBGR.width(),height=matBGR.height(),channels=matBGR.channels();
	byte[] source = new byte[width*height*channels];
	matBGR.get(0,0,source);
	image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
	final byte[] target =( (DataBufferByte) image.getRaster().getDataBuffer()).getData();
	System.arraycopy(source, 0, target, 0, source.length);
	
}


public void switchCamera(int x)
{
	capture=new VideoCapture(x);


}
}
