/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.util;

import java.awt.Color;

import net.sf.jasperreports.charts.JRCategoryAxisFormat;
import net.sf.jasperreports.charts.JRTimeAxisFormat;
import net.sf.jasperreports.charts.JRValueAxisFormat;
import net.sf.jasperreports.charts.JRXAxisFormat;
import net.sf.jasperreports.charts.JRYAxisFormat;
import net.sf.jasperreports.engine.JRAlignment;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRCommonGraphicElement;
import net.sf.jasperreports.engine.JRCommonImage;
import net.sf.jasperreports.engine.JRCommonRectangle;
import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRGraphicElement;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRReportFont;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRStyleContainer;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.JRTextField;
import net.sf.jasperreports.engine.base.JRBoxPen;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class JRStyleResolver
{

	private static final Integer INTEGER_ZERO = new Integer(0);

	/**
	 *
	 */
	private static JRFont getBaseFont(JRFont font)
	{
		JRReportFont reportFont = font.getReportFont();
		if (reportFont != null)
			return reportFont;
		JRDefaultStyleProvider defaultStyleProvider = font.getDefaultStyleProvider();
		if (defaultStyleProvider != null)
			return defaultStyleProvider.getDefaultFont();
		return null;
	}
	
	/**
	 *
	 */
	private static JRStyle getBaseStyle(JRStyleContainer styleContainer)
	{
		if (styleContainer != null)
		{
			JRStyle style = styleContainer.getStyle();
			if (style != null)
				return style;
			JRDefaultStyleProvider defaultStyleProvider = styleContainer.getDefaultStyleProvider();
			if (defaultStyleProvider != null)
				return defaultStyleProvider.getDefaultStyle();
		}
		return null;
	}


	/**
	 *
	 */
	public static byte getMode(JRCommonElement element, byte defaultMode)
	{
		Byte ownMode = element.getOwnMode();
		if (ownMode != null) 
			return ownMode.byteValue();
		JRStyle style = getBaseStyle(element);
		if (style != null)
		{
			Byte mode = style.getMode();
			if (mode != null)
			{
				return mode.byteValue();
			}
		}
		return defaultMode;
	}

	/**
	 *
	 */
	public static Byte getMode(JRStyle style)
	{
		Byte ownMode = style.getOwnMode();
		if (ownMode != null)
			return ownMode;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getMode();
		return null;
	}

	/**
	 *
	 */
	public static Color getForecolor(JRCommonElement element)
	{
		Color ownForecolor = element.getOwnForecolor();
		if (ownForecolor != null) 
			return ownForecolor;
		JRStyle style = getBaseStyle(element);
		if (style != null)
		{
			Color forecolor = style.getForecolor();
			if (forecolor != null)
			{
				return forecolor;
			}
		}
		return Color.black;
	}

	/**
	 *
	 */
	public static Color getForecolor(JRChartPlot plot)
	{
		JRChart chart = plot.getChart();
		if (chart != null)
			return getForecolor(chart);
		return Color.black;
	}

	/**
	 *
	 */
	public static Color getForecolor(JRStyle style)
	{
		Color ownForecolor = style.getOwnForecolor();
		if (ownForecolor != null)
			return ownForecolor;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getForecolor();
		return null;
	}

	/**
	 *
	 */
	public static Color getBackcolor(JRCommonElement element)
	{
		Color ownBackcolor = element.getOwnBackcolor();
		if (ownBackcolor != null) 
			return ownBackcolor;
		JRStyle style = getBaseStyle(element);
		if (style != null)
		{
			Color backcolor = style.getBackcolor();
			if (backcolor != null)
			{
				return backcolor;
			}
		}
		return Color.white;
	}

	/**
	 *
	 */
	public static Color getBackcolor(JRChartPlot plot)
	{
		Color ownBackcolor = plot.getOwnBackcolor();
		if (ownBackcolor != null) 
			return ownBackcolor;
		JRChart chart = plot.getChart();
		if (chart != null)
			return getBackcolor(chart);
		return Color.white;
	}

	/**
	 *
	 */
	public static Color getBackcolor(JRStyle style)
	{
		Color ownBackcolor = style.getOwnBackcolor();
		if (ownBackcolor != null)
			return ownBackcolor;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getBackcolor();
		return null;
	}

	/**
	 *
	 */
	public static Float getLineWidth(JRPen pen, Float defaultLineWidth)
	{
		Float ownLineWidth = pen.getOwnLineWidth();
		if (ownLineWidth != null)
			return ownLineWidth;
		JRStyle baseStyle = getBaseStyle(pen.getStyleContainer());
		if (baseStyle != null)
		{
			Float lineWidth = baseStyle.getLinePen().getLineWidth();
			if (lineWidth != null)
			{
				return lineWidth;
			}
		}
		return defaultLineWidth;
	}

	/**
	 *
	 */
	public static Float getLineWidth(JRBoxPen boxPen, Float defaultLineWidth)
	{
		Float ownLineWidth = boxPen.getOwnLineWidth();
		if (ownLineWidth != null)
			return ownLineWidth;
		Float penLineWidth = boxPen.getBox().getPen().getOwnLineWidth();
		if (penLineWidth != null) 
			return penLineWidth;
		JRStyle baseStyle = getBaseStyle(boxPen.getStyleContainer());
		if (baseStyle != null)
		{
			Float lineWidth = boxPen.getPen(baseStyle.getLineBox()).getLineWidth();
			if (lineWidth != null)
			{
				return lineWidth;
			}
		}
		return defaultLineWidth;
	}

	/**
	 *
	 */
	public static Byte getLineStyle(JRPen pen)
	{
		Byte ownLineStyle = pen.getOwnLineStyle();
		if (ownLineStyle != null)
			return ownLineStyle;
		JRStyle baseStyle = getBaseStyle(pen.getStyleContainer());
		if (baseStyle != null)
		{
			Byte lineStyle = baseStyle.getLinePen().getLineStyle();
			if (lineStyle != null)
			{
				return lineStyle;
			}
		}
		return new Byte(JRPen.LINE_STYLE_SOLID);
	}

	/**
	 *
	 */
	public static Byte getLineStyle(JRBoxPen boxPen)
	{
		Byte ownLineStyle = boxPen.getOwnLineStyle();
		if (ownLineStyle != null)
			return ownLineStyle;
		Byte penLineStyle = boxPen.getBox().getPen().getOwnLineStyle();
		if (penLineStyle != null)
			return penLineStyle;
		JRStyle baseStyle = getBaseStyle(boxPen.getStyleContainer());
		if (baseStyle != null)
		{
			Byte lineStyle = boxPen.getPen(baseStyle.getLineBox()).getLineStyle();
			if (lineStyle != null)
			{
				return lineStyle;
			}
		}
		return new Byte(JRPen.LINE_STYLE_SOLID);
	}

	/**
	 *
	 */
	public static Color getLineColor(JRPen pen, Color defaultColor)
	{
		Color ownLineColor = pen.getOwnLineColor();
		if (ownLineColor != null)
			return ownLineColor;
		JRStyle baseStyle = getBaseStyle(pen.getStyleContainer());
		if (baseStyle != null)
		{
			Color lineColor = baseStyle.getLinePen().getLineColor();
			if (lineColor != null)
			{
				return lineColor;
			}
		}
		return defaultColor;
	}

	/**
	 *
	 */
	public static Color getLineColor(JRBoxPen boxPen, Color defaultColor)
	{
		Color ownLineColor = boxPen.getOwnLineColor();
		if (ownLineColor != null)
			return ownLineColor;
		Color penLineColor = boxPen.getBox().getPen().getOwnLineColor();
		if (penLineColor != null)
			return penLineColor;
		JRStyle baseStyle = getBaseStyle(boxPen.getStyleContainer());
		if (baseStyle != null)
		{
			Color lineColor = boxPen.getPen(baseStyle.getLineBox()).getLineColor();
			if (lineColor != null)
			{
				return lineColor;
			}
		}
		return defaultColor;
	}

	/**
	 *
	 */
	public static byte getFill(JRCommonGraphicElement element)
	{
		Byte ownFill = element.getOwnFill();
		if (ownFill != null)
			return ownFill.byteValue();
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null)
		{
			Byte fill = baseStyle.getFill();
			if (fill != null)
			{
				return fill.byteValue();
			}
		}
		return JRGraphicElement.FILL_SOLID;
	}

	/**
	 *
	 */
	public static Byte getFill(JRStyle style)
	{
		Byte ownFill = style.getOwnFill();
		if (ownFill != null)
			return ownFill;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getFill();
		return null;
	}

	/**
	 *
	 */
	public static int getRadius(JRCommonRectangle rectangle)
	{
		Integer ownRadius = rectangle.getOwnRadius();
		if (ownRadius != null)
			return ownRadius.intValue();
		JRStyle baseStyle = getBaseStyle(rectangle);
		if (baseStyle != null)
		{
			Integer radius = baseStyle.getRadius();
			if (radius != null)
			{
				return radius.intValue();
			}
		}
		return 0;
	}

	/**
	 *
	 */
	public static Integer getRadius(JRStyle style)
	{
		Integer ownRadius = style.getOwnRadius();
		if (ownRadius != null)
			return ownRadius;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getRadius();
		return null;
	}

	/**
	 *
	 */
	public static byte getScaleImage(JRCommonImage image)
	{
		Byte ownScaleImage = image.getOwnScaleImage();
		if (ownScaleImage != null)
			return ownScaleImage.byteValue();
		JRStyle baseStyle = getBaseStyle(image);
		if (baseStyle != null)
		{
			Byte scaleImage = baseStyle.getScaleImage();
			if (scaleImage != null)
			{
				return scaleImage.byteValue();
			}
		}
		return JRImage.SCALE_IMAGE_RETAIN_SHAPE;
	}

	/**
	 *
	 */
	public static Byte getScaleImage(JRStyle style)
	{
		Byte ownScaleImage = style.getOwnScaleImage();
		if (ownScaleImage != null)
			return ownScaleImage;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null )
			return baseStyle.getScaleImage();
		return null;
	}

	/**
	 *
	 */
	public static byte getHorizontalAlignment(JRAlignment alignment)
	{
		Byte ownHorizontalAlignment = alignment.getOwnHorizontalAlignment();
		if (ownHorizontalAlignment != null)
			return ownHorizontalAlignment.byteValue();
		JRStyle baseStyle = getBaseStyle(alignment);
		if (baseStyle != null)
		{
			Byte horizontalAlignment = baseStyle.getHorizontalAlignment();
			if (horizontalAlignment != null)
			{
				return horizontalAlignment.byteValue();
			}
		}
		return JRAlignment.HORIZONTAL_ALIGN_LEFT;
	}

	/**
	 *
	 */
	public static Byte getHorizontalAlignment(JRStyle style)
	{
		Byte ownHorizontalAlignment = style.getOwnHorizontalAlignment();
		if (ownHorizontalAlignment != null)
			return ownHorizontalAlignment;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getHorizontalAlignment();
		return null;
	}

	/**
	 *
	 */
	public static byte getVerticalAlignment(JRAlignment alignment)
	{
		Byte ownVerticalAlignment = alignment.getOwnVerticalAlignment();
		if (ownVerticalAlignment != null)
			return ownVerticalAlignment.byteValue();
		JRStyle baseStyle = getBaseStyle(alignment);
		if (baseStyle != null)
		{
			Byte verticalAlignment = baseStyle.getVerticalAlignment();
			if (verticalAlignment != null)
			{
				return verticalAlignment.byteValue();
			}
		}
		return JRAlignment.VERTICAL_ALIGN_TOP;
	}

	/**
	 *
	 */
	public static Byte getVerticalAlignment(JRStyle style)
	{
		Byte ownVerticalAlignment = style.getOwnVerticalAlignment();
		if (ownVerticalAlignment != null)
			return ownVerticalAlignment;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getVerticalAlignment();
		return null;
	}

	/**
	 *
	 */
	public static byte getRotation(JRCommonText element)
	{
		Byte ownRotation = element.getOwnRotation();
		if (ownRotation != null)
			return ownRotation.byteValue();
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null)
		{
			Byte rotation = baseStyle.getRotation();
			if (rotation != null)
			{
				return rotation.byteValue();
			}
		}
		return JRTextElement.ROTATION_NONE;
	}

	/**
	 *
	 */
	public static Byte getRotation(JRStyle style)
	{
		Byte ownRotation = style.getOwnRotation();
		if (ownRotation != null)
			return ownRotation;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getRotation();
		return null;
	}

	/**
	 *
	 */
	public static byte getLineSpacing(JRCommonText element)
	{
		Byte ownLineSpacing = element.getOwnLineSpacing();
		if (ownLineSpacing != null)
			return ownLineSpacing.byteValue();
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null)
		{
			Byte lineSpacing = baseStyle.getLineSpacing();
			if (lineSpacing != null)
			{
				return lineSpacing.byteValue();
			}
		}
		return JRTextElement.LINE_SPACING_SINGLE;
	}

	/**
	 *
	 */
	public static Byte getLineSpacing(JRStyle style)
	{
		Byte ownLineSpacing = style.getOwnLineSpacing();
		if (ownLineSpacing != null)
			return ownLineSpacing;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getLineSpacing();
		return null;
	}

	/**
	 *
	 */
	public static String getMarkup(JRCommonText element)
	{
		String ownMarkup = element.getOwnMarkup();
		if (ownMarkup != null)
			return ownMarkup;
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null)
		{
			String markup = baseStyle.getMarkup();
			if (markup != null)
			{
				return markup;
			}
		}
		return JRCommonText.MARKUP_NONE;
	}

	/**
	 *
	 */
	public static String getMarkup(JRStyle style)
	{
		String ownMarkup = style.getOwnMarkup();
		if (ownMarkup != null)
			return ownMarkup;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getMarkup();
		return JRCommonText.MARKUP_NONE;
	}

	/**
	 *
	 */
	public static String getPattern(JRTextField element)
	{
		String ownPattern = element.getOwnPattern();
		if (ownPattern != null)
			return ownPattern;
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null)
			return baseStyle.getPattern();
		return null;
	}

	/**
	 *
	 */
	public static String getPattern(JRStyle style)
	{
		String ownPattern = style.getOwnPattern();
		if (ownPattern != null)
			return ownPattern;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getPattern();
		return null;
	}

	/**
	 *
	 */
	public static boolean isBlankWhenNull(JRTextField element)
	{
		Boolean ownBlankWhenNull = element.isOwnBlankWhenNull();
		if (ownBlankWhenNull != null)
			return ownBlankWhenNull.booleanValue();
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null)
		{
			Boolean blankWhenNull = baseStyle.isBlankWhenNull();
			if (blankWhenNull != null)
			{
				return blankWhenNull.booleanValue();
			}
		}
		return false;
	}

	/**
	 *
	 */
	public static Boolean isBlankWhenNull(JRStyle style)
	{
		Boolean ownBlankWhenNull = style.isOwnBlankWhenNull();
		if (ownBlankWhenNull != null)
			return ownBlankWhenNull;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.isBlankWhenNull();
		return null;
	}

	/**
	 *
	 */
	public static String getFontName(JRFont font)
	{
		String ownFontName = font.getOwnFontName();
		if (ownFontName != null)
			return ownFontName;
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
		{
			String fontName = baseFont.getFontName();
			if (fontName != null)
			{
				return fontName;
			}
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			String fontName = baseStyle.getFontName();
			if (fontName != null)
			{
				return fontName;
			}
		}
		return JRProperties.getProperty(JRFont.DEFAULT_FONT_NAME);
	}
	
	/**
	 *
	 */
	public static String getFontName(JRStyle style)
	{
		String ownFontName = style.getOwnFontName();
		if (ownFontName != null)
			return ownFontName;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
		{
			String fontName = baseStyle.getFontName();
			if (fontName != null)
			{
				return fontName;
			}
		}
		return JRProperties.getProperty(JRFont.DEFAULT_FONT_NAME);
	}

	/**
	 *
	 */
	public static boolean isBold(JRFont font)
	{
		Boolean ownBold = font.isOwnBold();
		if (ownBold != null)
			return ownBold.booleanValue();
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
			return baseFont.isBold();
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			Boolean bold = baseStyle.isBold();
			if (bold != null)
			{
				return bold.booleanValue();
			}
		}
		return false;
	}
	
	/**
	 *
	 */
	public static Boolean isBold(JRStyle style)
	{
		Boolean ownBold = style.isOwnBold();
		if (ownBold != null)
			return ownBold;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.isBold();
		return null;
	}

	/**
	 *
	 */
	public static boolean isItalic(JRFont font)
	{
		Boolean ownItalic = font.isOwnItalic();
		if (ownItalic != null)
			return ownItalic.booleanValue();
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
			return baseFont.isItalic();
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			Boolean italic = baseStyle.isItalic();
			if (italic != null)
			{
				return italic.booleanValue();
			}
		}
		return false;
	}
	
	/**
	 *
	 */
	public static Boolean isItalic(JRStyle style)
	{
		Boolean ownItalic = style.isOwnItalic();
		if (ownItalic != null)
			return ownItalic;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.isItalic();
		return null;
	}

	/**
	 *
	 */
	public static boolean isUnderline(JRFont font)
	{
		Boolean ownUnderline = font.isOwnUnderline();
		if (ownUnderline != null)
			return ownUnderline.booleanValue();
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
			return baseFont.isUnderline();
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			Boolean underline = baseStyle.isUnderline();
			if (underline != null)
			{
				return underline.booleanValue();
			}
		}
		return false;
	}
	
	/**
	 *
	 */
	public static Boolean isUnderline(JRStyle style)
	{
		Boolean ownUnderline = style.isOwnUnderline();
		if (ownUnderline != null)
			return ownUnderline;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.isUnderline();
		return null;
	}

	/**
	 *
	 */
	public static boolean isStrikeThrough(JRFont font)
	{
		Boolean ownStrikeThrough = font.isOwnStrikeThrough();
		if (ownStrikeThrough != null)
			return ownStrikeThrough.booleanValue();
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
			return baseFont.isStrikeThrough();
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			Boolean strikeThrough = baseStyle.isStrikeThrough();
			if (strikeThrough != null)
			{
				return strikeThrough.booleanValue();
			}
		}
		return false;
	}
	
	/**
	 *
	 */
	public static Boolean isStrikeThrough(JRStyle style)
	{
		if (style.isOwnStrikeThrough() != null)
			return style.isOwnStrikeThrough();
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.isStrikeThrough();
		return null;
	}

	/**
	 *
	 */
	public static int getFontSize(JRFont font)
	{
		Integer ownFontSize = font.getOwnFontSize();
		if (ownFontSize != null)
			return ownFontSize.intValue();
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
			return baseFont.getFontSize();
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			Integer fontSize = baseStyle.getFontSize();
			if (fontSize != null)
			{
				return fontSize.intValue();
			}
		}
		return JRProperties.getIntegerProperty(JRFont.DEFAULT_FONT_SIZE);
	}
	
	/**
	 *
	 */
	public static Integer getFontSize(JRStyle style)
	{
		Integer ownFontSize = style.getOwnFontSize();
		if (ownFontSize != null)
			return ownFontSize;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.getFontSize();
		return null;
	}

	/**
	 *
	 */
	public static String getPdfFontName(JRFont font)
	{
		String ownPdfFontName = font.getOwnPdfFontName();
		if (ownPdfFontName != null)
			return ownPdfFontName;
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
		{
			String pdfFontName = baseFont.getPdfFontName();
			if (pdfFontName != null)
			{
				return pdfFontName;
			}
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			String pdfFontName = baseStyle.getPdfFontName();
			if (pdfFontName != null)
			{
				return pdfFontName;
			}
		}
		return JRProperties.getProperty(JRFont.DEFAULT_PDF_FONT_NAME);
	}
	
	/**
	 *
	 */
	public static String getPdfFontName(JRStyle style)
	{
		String ownPdfFontName = style.getOwnPdfFontName();
		if (ownPdfFontName != null)
			return ownPdfFontName;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
		{
			String pdfFontName = baseStyle.getPdfFontName();
			if (pdfFontName != null)
			{
				return pdfFontName;
			}
		}
		return JRProperties.getProperty(JRFont.DEFAULT_PDF_FONT_NAME);
	}

	/**
	 *
	 */
	public static String getPdfEncoding(JRFont font)
	{
		String ownPdfEncoding = font.getOwnPdfEncoding();
		if (ownPdfEncoding != null)
			return ownPdfEncoding;
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
		{
			String pdfEncoding = baseFont.getPdfEncoding();
			if (pdfEncoding != null)
			{
				return pdfEncoding;
			}
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			String pdfEncoding = baseStyle.getPdfEncoding();
			if (pdfEncoding != null)
			{
				return pdfEncoding;
			}
		}
		return JRProperties.getProperty(JRFont.DEFAULT_PDF_ENCODING);
	}
	
	/**
	 *
	 */
	public static String getPdfEncoding(JRStyle style)
	{
		String ownPdfEncoding = style.getOwnPdfEncoding();
		if (ownPdfEncoding != null)
			return ownPdfEncoding;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
		{
			String pdfEncoding = baseStyle.getPdfEncoding();
			if (pdfEncoding != null)
			{
				return pdfEncoding;
			}
		}
		return JRProperties.getProperty(JRFont.DEFAULT_PDF_ENCODING);
	}

	/**
	 *
	 */
	public static boolean isPdfEmbedded(JRFont font)
	{
		Boolean ownPdfEmbedded = font.isOwnPdfEmbedded();
		if (ownPdfEmbedded != null)
			return ownPdfEmbedded.booleanValue();
		JRFont baseFont = getBaseFont(font);
		if (baseFont != null)
			return baseFont.isPdfEmbedded();
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null)
		{
			Boolean pdfEmbedded = baseStyle.isPdfEmbedded();
			if (pdfEmbedded != null)
			{
				return pdfEmbedded.booleanValue();
			}
		}
		return JRProperties.getBooleanProperty(JRFont.DEFAULT_PDF_EMBEDDED);
	}
	
	/**
	 *
	 */
	public static Boolean isPdfEmbedded(JRStyle style)
	{
		Boolean ownPdfEmbedded = style.isOwnPdfEmbedded();
		if (ownPdfEmbedded != null)
			return ownPdfEmbedded;
		JRStyle baseStyle = getBaseStyle(style);
		if (baseStyle != null)
			return baseStyle.isPdfEmbedded();
		return null;
	}

	/**
	 *
	 */
	public static Integer getPadding(JRLineBox box)
	{
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null)
			return ownPadding;
		JRStyle baseStyle = getBaseStyle(box);
		if (baseStyle != null)
		{
			Integer padding = baseStyle.getLineBox().getPadding();
			if (padding != null)
			{
				return padding;
			}
		}
		return INTEGER_ZERO;
	}

	/**
	 *
	 */
	public static Integer getTopPadding(JRLineBox box)
	{
		Integer ownTopPadding = box.getOwnTopPadding();
		if (ownTopPadding != null)
			return ownTopPadding;
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null)
			return ownPadding;
		JRStyle style = getBaseStyle(box);
		if (style != null)
		{
			Integer topPadding = style.getLineBox().getTopPadding();
			if (topPadding != null)
			{
				return topPadding;
			}
		}
		return INTEGER_ZERO;
	}

	/**
	 *
	 */
	public static Integer getLeftPadding(JRLineBox box)
	{
		Integer ownLeftPadding = box.getOwnLeftPadding();
		if (ownLeftPadding != null)
			return ownLeftPadding;
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null)
			return ownPadding;
		JRStyle style = getBaseStyle(box);
		if (style != null)
		{
			Integer leftPadding = style.getLineBox().getLeftPadding();
			if (leftPadding != null)
			{
				return leftPadding;
			}
		}
		return INTEGER_ZERO;
	}

	/**
	 *
	 */
	public static Integer getBottomPadding(JRLineBox box)
	{
		Integer ownBottomPadding = box.getOwnBottomPadding();
		if (ownBottomPadding != null)
			return ownBottomPadding;
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null)
			return ownPadding;
		JRStyle style = getBaseStyle(box);
		if (style != null)
		{
			Integer bottomPadding = style.getLineBox().getBottomPadding();
			if (bottomPadding != null)
			{
				return bottomPadding;
			}
		}
		return INTEGER_ZERO;
	}

	/**
	 *
	 */
	public static Integer getRightPadding(JRLineBox box)
	{
		Integer ownRightPadding = box.getOwnRightPadding();
		if (ownRightPadding != null)
			return ownRightPadding;
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null)
			return ownPadding;
		JRStyle style = getBaseStyle(box);
		if (style != null)
		{
			Integer rightPadding = style.getLineBox().getRightPadding();
			if (rightPadding != null)
			{
				return rightPadding;
			}
		}
		return INTEGER_ZERO;
	}


	/**
	 * Merges two styles, by appending the properties of the source style to the ones of the destination style.
	 */
	public static void appendStyle(JRStyle destStyle, JRStyle srcStyle)
	{
		if (srcStyle.getOwnMode() != null)
			destStyle.setMode(srcStyle.getOwnMode());
		if (srcStyle.getOwnForecolor() != null)
			destStyle.setForecolor(srcStyle.getOwnForecolor());
		if (srcStyle.getOwnBackcolor() != null)
			destStyle.setBackcolor(srcStyle.getOwnBackcolor());

		appendPen(destStyle.getLinePen(), srcStyle.getLinePen());
		
		if (srcStyle.getOwnFill() != null)
			destStyle.setFill(srcStyle.getOwnFill());

		if (srcStyle.getOwnRadius() != null)
			destStyle.setRadius(srcStyle.getOwnRadius());

		if (srcStyle.getOwnScaleImage() != null)
			destStyle.setScaleImage(srcStyle.getOwnScaleImage());
		if (srcStyle.getOwnHorizontalAlignment() != null)
			destStyle.setHorizontalAlignment(srcStyle.getOwnHorizontalAlignment());
		if (srcStyle.getOwnVerticalAlignment() != null)
			destStyle.setVerticalAlignment(srcStyle.getOwnVerticalAlignment());

		appendBox(destStyle.getLineBox(), srcStyle.getLineBox());

		if (srcStyle.getOwnRotation() != null)
			destStyle.setRotation(srcStyle.getOwnRotation());
		if (srcStyle.getOwnLineSpacing() != null)
			destStyle.setLineSpacing(srcStyle.getOwnLineSpacing());
		if (srcStyle.getOwnMarkup() != null)
			destStyle.setMarkup(srcStyle.getOwnMarkup());

		if (srcStyle.getOwnPattern() != null)
			destStyle.setPattern(srcStyle.getOwnPattern());

		if (srcStyle.getOwnFontName() != null)
			destStyle.setFontName(srcStyle.getOwnFontName());
		if (srcStyle.isOwnBold() != null)
			destStyle.setBold(srcStyle.isOwnBold());
		if (srcStyle.isOwnItalic() != null)
			destStyle.setItalic(srcStyle.isOwnItalic());
		if (srcStyle.isOwnUnderline() != null)
			destStyle.setUnderline(srcStyle.isOwnUnderline());
		if (srcStyle.isOwnStrikeThrough() != null)
			destStyle.setStrikeThrough(srcStyle.isOwnStrikeThrough());
		if (srcStyle.getOwnFontSize() != null)
			destStyle.setFontSize(srcStyle.getOwnFontSize());
		if (srcStyle.getOwnPdfFontName() != null)
			destStyle.setPdfFontName(srcStyle.getOwnPdfFontName());
		if (srcStyle.getOwnPdfEncoding() != null)
			destStyle.setPdfEncoding(srcStyle.getOwnPdfEncoding());
		if (srcStyle.isOwnPdfEmbedded() != null)
			destStyle.setPdfEmbedded(srcStyle.isOwnPdfEmbedded());
	}

	/**
	 * Merges two pens, by appending the properties of the source pen to the ones of the destination pen.
	 */
	private static void appendPen(JRPen destPen, JRPen srcPen)
	{
		if (srcPen.getOwnLineWidth() != null)
			destPen.setLineWidth(srcPen.getOwnLineWidth());
		if (srcPen.getOwnLineStyle() != null)
			destPen.setLineStyle(srcPen.getOwnLineStyle());
		if (srcPen.getOwnLineColor() != null)
			destPen.setLineColor(srcPen.getOwnLineColor());
	}

	/**
	 * Merges two boxes, by appending the properties of the source box to the ones of the destination box.
	 */
	private static void appendBox(JRLineBox destBox, JRLineBox srcBox)
	{
		appendPen(destBox.getPen(), srcBox.getPen());
		appendPen(destBox.getTopPen(), srcBox.getTopPen());
		appendPen(destBox.getLeftPen(), srcBox.getLeftPen());
		appendPen(destBox.getBottomPen(), srcBox.getBottomPen());
		appendPen(destBox.getRightPen(), srcBox.getRightPen());

		if (srcBox.getOwnPadding() != null)
			destBox.setPadding(srcBox.getOwnPadding());
		if (srcBox.getOwnTopPadding() != null)
			destBox.setTopPadding(srcBox.getOwnTopPadding());
		if (srcBox.getOwnLeftPadding() != null)
			destBox.setLeftPadding(srcBox.getOwnLeftPadding());
		if (srcBox.getOwnBottomPadding() != null)
			destBox.setBottomPadding(srcBox.getOwnBottomPadding());
		if (srcBox.getOwnRightPadding() != null)
			destBox.setRightPadding(srcBox.getOwnRightPadding());
	}

	/**
	 *
	 */
	public static Color getTitleColor(JRChart chart)
	{
		Color ownTitleColor = chart.getOwnTitleColor();
		if (ownTitleColor != null) 
			return ownTitleColor;
		return getForecolor(chart);
	}

	/**
	 *
	 */
	public static Color getSubtitleColor(JRChart chart)
	{
		Color ownSubtitleColor = chart.getOwnSubtitleColor();
		if (ownSubtitleColor != null) 
			return ownSubtitleColor;
		return getForecolor(chart);
	}

	/**
	 *
	 */
	public static Color getLegendColor(JRChart chart)
	{
		Color ownLegendColor = chart.getOwnLegendColor();
		if (ownLegendColor != null) 
			return ownLegendColor;
		return getForecolor(chart);
	}

	/**
	 *
	 */
	public static Color getLegendBackgroundColor(JRChart chart)
	{
		Color ownLegendBackgroundColor = chart.getOwnLegendBackgroundColor();
		if (ownLegendBackgroundColor != null) 
			return ownLegendBackgroundColor;
		return getBackcolor(chart);
	}

	/**
	 *
	 */
	public static Color getCategoryAxisLabelColor(JRCategoryAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownCategoryAxisLabelColor = axisFormat.getOwnCategoryAxisLabelColor();
		if (ownCategoryAxisLabelColor != null) 
			return ownCategoryAxisLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getCategoryAxisTickLabelColor(JRCategoryAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownCategoryAxisTickLabelColor = axisFormat.getOwnCategoryAxisTickLabelColor();
		if (ownCategoryAxisTickLabelColor != null) 
			return ownCategoryAxisTickLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getCategoryAxisLineColor(JRCategoryAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownCategoryAxisLineColor = axisFormat.getOwnCategoryAxisLineColor();
		if (ownCategoryAxisLineColor != null) 
			return ownCategoryAxisLineColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getValueAxisLabelColor(JRValueAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownValueAxisLabelColor = axisFormat.getOwnValueAxisLabelColor();
		if (ownValueAxisLabelColor != null) 
			return ownValueAxisLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getValueAxisTickLabelColor(JRValueAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownValueAxisTickLabelColor = axisFormat.getOwnValueAxisTickLabelColor();
		if (ownValueAxisTickLabelColor != null) 
			return ownValueAxisTickLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getValueAxisLineColor(JRValueAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownValueAxisLineColor = axisFormat.getOwnValueAxisLineColor();
		if (ownValueAxisLineColor != null) 
			return ownValueAxisLineColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getXAxisLabelColor(JRXAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownXAxisLabelColor = axisFormat.getOwnXAxisLabelColor();
		if (ownXAxisLabelColor != null) 
			return ownXAxisLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getXAxisTickLabelColor(JRXAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownXAxisTickLabelColor = axisFormat.getOwnXAxisTickLabelColor();
		if (ownXAxisTickLabelColor != null) 
			return ownXAxisTickLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getXAxisLineColor(JRXAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownXAxisLineColor = axisFormat.getOwnXAxisLineColor();
		if (ownXAxisLineColor != null) 
			return ownXAxisLineColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getYAxisLabelColor(JRYAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownYAxisLabelColor = axisFormat.getOwnYAxisLabelColor();
		if (ownYAxisLabelColor != null) 
			return ownYAxisLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getYAxisTickLabelColor(JRYAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownYAxisTickLabelColor = axisFormat.getOwnYAxisTickLabelColor();
		if (ownYAxisTickLabelColor != null) 
			return ownYAxisTickLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getYAxisLineColor(JRYAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownYAxisLineColor = axisFormat.getOwnYAxisLineColor();
		if (ownYAxisLineColor != null) 
			return ownYAxisLineColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getTimeAxisLabelColor(JRTimeAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownTimeAxisLabelColor = axisFormat.getOwnTimeAxisLabelColor();
		if (ownTimeAxisLabelColor != null) 
			return ownTimeAxisLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getTimeAxisTickLabelColor(JRTimeAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownTimeAxisTickLabelColor = axisFormat.getOwnTimeAxisTickLabelColor();
		if (ownTimeAxisTickLabelColor != null) 
			return ownTimeAxisTickLabelColor;
		return getForecolor(plot);
	}

	/**
	 *
	 */
	public static Color getTimeAxisLineColor(JRTimeAxisFormat axisFormat, JRChartPlot plot)
	{
		Color ownTimeAxisLineColor = axisFormat.getOwnTimeAxisLineColor();
		if (ownTimeAxisLineColor != null) 
			return ownTimeAxisLineColor;
		return getForecolor(plot);
	}

}
