package com.black_dog20.servertabinfo.utility;

import java.util.List;

import com.black_dog20.servertabinfo.client.objects.IRenderable;
import com.black_dog20.servertabinfo.client.objects.Player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class RenderHelper {
	
	public static int RenderList(List<String> list, Minecraft mc, int startTop, int width) {

		if (list != null && !list.isEmpty())
		{
			int maxWidth = findMaxWidthString(list, mc);
			
			GuiScreen.drawRect(width / 2 - maxWidth / 2 - 1, startTop - 1, width / 2 + maxWidth / 2 + 1, startTop + list.size() * CompatibilityHelper.getFontRenderHeight(mc), Integer.MIN_VALUE);

			for (String string : list)
			{

				GuiScreen.drawRect(width / 2 - maxWidth / 2, startTop, width / 2 + maxWidth / 2, startTop+8, 553648127);
				CompatibilityHelper.glListHelper();
				int i2 = CompatibilityHelper.getStringWidth(mc,string);
				CompatibilityHelper.drawStringWithShadow(mc, string, (float) (width / 2 - i2 / 2), (float) startTop, -1);
				startTop += CompatibilityHelper.getFontRenderHeight(mc);
			}
		}
		return startTop;
	}
	
	public static int RenderListAtStartPoint(List<String> list, Minecraft mc, int x, int y) {
		if (list != null && !list.isEmpty())
		{
			int maxWidth = findMaxWidthString(list, mc);
			
			GuiScreen.drawRect(x , y - 1, x+maxWidth+3, y + list.size() * CompatibilityHelper.getFontRenderHeight(mc), Integer.MIN_VALUE);
			
			for (String string : list)
			{
				GuiScreen.drawRect(x+1, y, x+maxWidth+2, y+8, 553648127);
				CompatibilityHelper.glListHelper();
				CompatibilityHelper.drawStringWithShadow(mc, string, (float) x+2, (float) y, -1);
				y += CompatibilityHelper.getFontRenderHeight(mc);
			}
		}
		return y;
	}
	
	public static int RenderObjectList(List<IRenderable> list, Minecraft mc, int y, int width) {

		if (list != null && !list.isEmpty())
		{
			int maxWidth = findMaxWidth(list, mc);
			int[] maxWidthArray = findMaxWidthArray(list, mc);
			if(maxWidthArray == null)
				return y;

			GuiScreen.drawRect(width / 2 - maxWidth / 2 - 1, y - 1, width / 2 + maxWidth / 2 + 1, y + list.size() * CompatibilityHelper.getFontRenderHeight(mc), Integer.MIN_VALUE);

			for (IRenderable o : list)
			{
				GuiScreen.drawRect(width / 2 - maxWidth / 2, y, width / 2 + maxWidth / 2, y+8, 553648127);
				CompatibilityHelper.glListHelper();
				o.render((width / 2 - maxWidth / 2), y, maxWidthArray);
				y += CompatibilityHelper.getFontRenderHeight(mc);
			}
		}
		return y;
	}
	
	public static int RenderObjectListAtStartPoint(List<IRenderable> list, Minecraft mc, int x, int y) {
		if (list != null && !list.isEmpty())
		{
			int maxWidth = findMaxWidth(list, mc);
			int[] maxWidthArray = findMaxWidthArray(list, mc);
			if(maxWidthArray == null)
				return y;
			
			GuiScreen.drawRect(x , y - 1, x+maxWidth+3, y + list.size() * CompatibilityHelper.getFontRenderHeight(mc), Integer.MIN_VALUE);
			
			for (IRenderable o : list)
			{
				GuiScreen.drawRect(x+1, y, x+maxWidth+2, y+8, 553648127);
				CompatibilityHelper.glListHelper();
				o.render(x, y, maxWidthArray);
				y += CompatibilityHelper.getFontRenderHeight(mc);
			}
		}
		return y;
	}
	
	public static int findMaxWidthString(List<String> list, Minecraft mc) {
		int maxWidth = 0;
		for(String s : list) {
			int i = CompatibilityHelper.getStringWidth(mc, s);
			maxWidth = Math.max(maxWidth, i);
		}
		return maxWidth;
	}
	
	public static int findMaxWidth(List<IRenderable> list, Minecraft mc) {
		int maxWidth = 0;
		for(IRenderable s : list) {
			int i = s.getWidth();
			maxWidth = Math.max(maxWidth, i);
		}
		return maxWidth;
	}
	
	public static int[] findMaxWidthArray(List<IRenderable> list, Minecraft mc) {
		int[] maxWidth = null;
		for(IRenderable s : list) {
			if(maxWidth == null)
				maxWidth = s.getWidthArray();
			int[] i = s.getWidthArray();
			for(int n = 0; n < maxWidth.length; n++) {
				maxWidth[n] = Math.max(maxWidth[n], i[n]);
			}
		}
		return maxWidth;
	}
	
	public static List<IRenderable> getPage(int page, int itemPerPage, List<IRenderable> input){
		int last = page*itemPerPage;
		if(last > input.size())
			last = input.size();
		return input.subList((page-1)*itemPerPage, last);
	}
}
