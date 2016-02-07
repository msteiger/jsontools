package org.sweetlemonade.eclipse.json.preference;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.sweetlemonade.eclipse.json.ColorManager;
import org.sweetlemonade.eclipse.json.JsonPlugin;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.TokenType;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonPreferences
{
    private final IPreferenceStore mStore;
    private final ColorManager mManager;

    public JsonPreferences()
    {
        mStore = JsonPlugin.getDefault().getPreferenceStore();
        mManager = JsonPlugin.getColorManager();
    }

    public int getStyle(TokenType type)
    {
        return type.getStyle(mStore);
    }

    public RGB getRgb(TokenType type)
    {
        return type.getColor(mStore);
    }

    public Color getColor(TokenType type)
    {
        return mManager.getColor(getRgb(type));
    }

    public static int extractBoldItalic(int style)
    {
        return style & ~TextAttribute.STRIKETHROUGH & ~TextAttribute.UNDERLINE;
    }

    public static boolean isUnderline(int style)
    {
        return (style & TextAttribute.UNDERLINE) != 0;
    }

    public static boolean isStrike(int style)
    {
        return (style & TextAttribute.STRIKETHROUGH) != 0;
    }

    public static boolean isBold(int style)
    {
        return (style & SWT.BOLD) != 0;
    }

    public static boolean isItalic(int style)
    {
        return (style & SWT.ITALIC) != 0;
    }

    public static int mergeStyles(final boolean bold, final boolean italic, final boolean under, final boolean strike)
    {
        int style = SWT.NORMAL;

        style |= bold ? SWT.BOLD : 0;
        style |= italic ? SWT.ITALIC : 0;
        style |= under ? TextAttribute.UNDERLINE : 0;
        style |= strike ? TextAttribute.STRIKETHROUGH : 0;

        return style;
    }
}
