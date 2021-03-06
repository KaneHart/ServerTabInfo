package com.black_dog20.servertabinfo.utility;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.servertabinfo.client.objects.IRenderable;
import com.black_dog20.servertabinfo.utility.ColorObject.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TpsDimension implements IRenderable{

	public String name;
	
	public Double meanTickTime;
	
	public int Id;
	private int responseVersion;
	private int spacing = 3;
	
	public TpsDimension(String name, Double meanTickTime) {
		this.name = name;
		this.meanTickTime = meanTickTime;
	}
	
	
	public TpsDimension(String name, Double meanTickTiem, int id) {
		this.name = name;
		this.meanTickTime = meanTickTiem;
		this.Id = id;
	}
	
	public TpsDimension(String name, Double meanTickTime, double responseVersion) {
		this.name = name;
		this.meanTickTime = meanTickTime;
		this.responseVersion = (int)responseVersion;
	}
	
	
	public TpsDimension(String name, Double meanTickTiem, int id, double responseVersion) {
		this.name = name;
		this.meanTickTime = meanTickTiem;
		this.Id = id;
		this.responseVersion = (int)responseVersion;
	}
	
	public String getDimString(int responseVersion) {
		String[] strings = getStrings(responseVersion);
		return String.format("%s: %s %s (%s %s)", strings[0], strings[1], strings[2], strings[3], strings[4]);
	}
	
	public String getShortDimString(int responseVersion) {
		String[] strings = getStrings(responseVersion);
		return String.format("%s(%s)", strings[0], strings[3]);
	}
	
	@Override
	public int getWidthOfElement(int n) {
		return getWidthArray()[n];
	}
	
	@Override
	public int[] getWidthArray() {
		int[] width = new int[4];
		int tempWidth = 0;
		Minecraft mc = Minecraft.getInstance();
		String[] strings = getStrings(responseVersion);
		tempWidth += CompatibilityHelper.getStringWidth(mc, strings[0]+":");
		tempWidth += spacing;
		width[0] = tempWidth;
		tempWidth = 0;
		
		tempWidth += CompatibilityHelper.getStringWidth(mc, strings[1]);
		tempWidth += spacing;
		width[1] = tempWidth;
		tempWidth = 0;
		
		tempWidth += CompatibilityHelper.getStringWidth(mc, strings[2]);
		tempWidth += 2*spacing;
		width[2] = tempWidth;
		tempWidth = 0;
		
		tempWidth += CompatibilityHelper.getStringWidth(mc, String.format("(%s %s)", strings[3], strings[4]));
		width[3] = tempWidth;
		tempWidth = 0;
		
		return width;
	}
	
	private String[] getStrings(int responseVersion) {
		List<String> strings = new ArrayList<String>();
		ColorObject color = new ColorObject(Color.Green);
		int tps = (int) Math.min(1000.0D / (meanTickTime*1.0E-006D), 20);

		if (tps < 20)
		{
			color = new ColorObject(Color.Yellow);
		}
		if (tps <= 10)
		{
			color = new ColorObject(Color.Red);
		}

		String tpsValue = CompatibilityHelper.text(Integer.toString(tps), color);
		String mean = CompatibilityHelper.translate("gui.servertabinfo.mean");
		String dim = CompatibilityHelper.translate("gui.servertabinfo.dim");
		String ms = CompatibilityHelper.translate("gui.servertabinfo.ms");
		String tpsText = CompatibilityHelper.translate("gui.servertabinfo.tps");
		String nameT = CompatibilityHelper.translate(dim + " " +Integer.toString(Id));
		if(!name.equals("")) {
			nameT = CompatibilityHelper.translate(name);

			if(nameT.equals(name)) {
				String nameC = CompatibilityHelper.translate("servertabinfo.dim." + name);
				if(!nameC.equals("servertabinfo.dim." + name)) {
					nameT = nameC;
				} else {
					nameT = new ResourceLocation(name).getPath();
				}
			}
		}
		
		strings.add(nameT);
		strings.add(mean);
		strings.add(String.format("%.2f%s", (meanTickTime*1.0E-006D), ms));
		strings.add(tpsValue);
		strings.add(tpsText);
		return strings.toArray(new String[0]);
	}
	
	private int calcLeftOverspace(int[] maxWidth, int n) {
		return maxWidth[n]-getWidthOfElement(n);
	}
	
	@Override
	public void render(int x, int y, int[] width) {
		Minecraft mc = Minecraft.getInstance();
		String[] strings = getStrings(responseVersion);
        String s4 = strings[0]+":";
        CompatibilityHelper.drawStringWithShadow(mc, s4, (float)x, (float)y, -1);

        x += CompatibilityHelper.getStringWidth(mc, s4) + (2*spacing)+calcLeftOverspace(width, 0);
        CompatibilityHelper.drawStringWithShadow(mc, strings[1], (float)x, (float)y, -1);
        x += CompatibilityHelper.getStringWidth(mc, strings[1])+spacing+calcLeftOverspace(width, 1);
        
        
        CompatibilityHelper.drawStringWithShadow(mc, strings[2], (float)x, (float)y, -1);
        x += CompatibilityHelper.getStringWidth(mc, strings[2])+spacing+calcLeftOverspace(width, 2);
		
        CompatibilityHelper.drawStringWithShadow(mc, String.format("(%s %s)", strings[3], strings[4]), (float)x, (float)y, -1);
	}
	
}
