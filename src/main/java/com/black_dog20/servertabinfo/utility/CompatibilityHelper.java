package com.black_dog20.servertabinfo.utility;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class CompatibilityHelper {
	
	public static String translate(String text) {
		return new TranslationTextComponent(text).getFormattedText();
	}
	
	public static String translate(String text, ColorObject color) {
		TranslationTextComponent temp = new TranslationTextComponent(text);
		temp.getStyle().setColor(color.getColor());
		return temp.getFormattedText();
	}
	
	public static String text(String text, ColorObject color) {
		StringTextComponent temp = new StringTextComponent(text);
		temp.getStyle().setColor(color.getColor());
		return temp.getFormattedText();
	}
	
	public static String text(String text) {
		StringTextComponent temp = new StringTextComponent(text);
		return temp.getFormattedText();
	}
	
	public static void glPush() {
		RenderSystem.pushMatrix();
	}
	
	public static void glPop() {
		RenderSystem.popMatrix();
	}
	
	public static void glScale(double value) {
		RenderSystem.scaled(value, value, value);
	}
	
	public static void glListHelper() {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.enableAlphaTest();
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	}
	
	public static int getStringWidth(Minecraft mc, String text) {
		return mc.fontRenderer.getStringWidth(text);
	}
	
	public static void drawStringWithShadow(Minecraft mc, String text, float x, float y, int color) {
		mc.fontRenderer.drawStringWithShadow(text, x, y, color);
	}
	
	public static void drawStringWithShadowItalic(Minecraft mc, String text, float x, float y, int color) {
		mc.fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + text, x, y, color);
	}
	
	public static int getFontRenderHeight(Minecraft mc) {
		return mc.fontRenderer.FONT_HEIGHT;
	}
	
	public static void drawCenteredStringWithShadow(Minecraft mc, String text, float x, float y, int color) {
		int i2 = getStringWidth(mc, text);
		CompatibilityHelper.drawStringWithShadow(mc, text, (float) (x - i2 / 2), (float) y, color);
	}
}
