package textReader.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Status" )
public class BookStatus {
	private boolean wrapFlag;
	private String fontColor ;
	private String background ;
	private int fontSize;
	private int fontSpace;
	
	public boolean getWrapFlag(){
		return this.wrapFlag;
	}
	
	public void setWrapFlag(boolean wrapFlag){
		this.wrapFlag = wrapFlag;
	}
	
	public String getFontColor(){
		return this.fontColor;
	}
	
	public void setFontColor(String fontColor){
		this.fontColor = fontColor;
	}
	
	public String getBackground(){
		return this.background;
	}
	
	public void setBackground(String background){
		this.background = background;
	}
	
	public int getFontSize(){
		return this.fontSize;
	}
	
	public void setFontSize(int fontSize){
		this.fontSize = fontSize;
	}
	
	public int getFontSpace(){
		return this.fontSpace;
	}
	
	public void setFontSpace(int fontSpace){
		this.fontSpace = fontSpace;
	}
}
