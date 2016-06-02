package pratoFiorito;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Number extends Block implements Serializable
{
	
	public Number(int value)
	{	
		super();
		setValue(value);
		
	}
	
	@Override
	public void updateExposedIcon() {		
		String[] img = new String[]{"images/numeri/vuoto.jpg",
				"images/numeri/1.jpg",
				"images/numeri/2.jpg",
				"images/numeri/3.jpg",
				"images/numeri/4.jpg",
				"images/numeri/5.jpg",
				"images/numeri/6.jpg",
				"images/numeri/7.jpg",
				"images/numeri/8.jpg"};
		
		setIcon(new ImageIcon(img[this.getValue()]));
	}
	
//	public void setIcon(int num)
//	{
//		switch(num)
//		{	
//			case 0: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/vuoto.jpg"); break;
//			case 1: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/numbers/1.jpg"); break;
//			case 2: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/numbers/2.jpg"); break;
//			case 3: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/numbers/3.jpg"); break;
//			case 4: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/numbers/4.jpg"); break;
//			case 5: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/numbers/5.jpg"); break;
//			case 6: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/numbers/6.jpg"); break;
//			case 7: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/numbers/7.jpg"); break;
//			case 8: icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/numbers/8.jpg"); break;
//		}
//	}
	
	public String getString()
	{
		return getIcon().toString();
	}
	
}
