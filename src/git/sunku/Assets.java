package git.sunku;

import git.sunku.engine.lib.Ogg;
import git.sunku.engine.toolbox.Cache;
import git.sunku.engine.toolbox.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Assets {

    private static Cache<String, BufferedImage> m_ImageCache = null;
    private static Cache<String, Font> m_FontCache = null;
    private static Cache<String, Ogg> m_OggCache = null;

    public static void init() {
        m_ImageCache = Cache.newInstance(14);
        m_FontCache = Cache.newInstance(3);
        m_OggCache = Cache.newInstance(10);

        m_ImageCache.put("stick", Loader.loadImage("stick.png"));
        m_ImageCache.put("slime", Loader.loadImage("slime.png"));
        m_ImageCache.put("tiles", Loader.loadImage("tiles.png"));

        m_FontCache.put("vcr", Loader.loadTTF("vcr.ttf"));

        m_OggCache.put("the_plan", new Ogg(getOGG("the_plan"), 0.85f));
    }

    private static URL getOGG(String ogg) {
        return Assets.class.getResource(String.format("/audio/%s.ogg", ogg));
    }

    public static void putImage(String name, BufferedImage image) { m_ImageCache.putIfAbsent(name, image); }

    public static BufferedImage getImage(String imageName) { return m_ImageCache.get(imageName); }

    public static Font getFont(String fontName, float size) { return getFont(fontName, Font.PLAIN, size); }
    public static Font getFont(String fontName, int style, float size) { return m_FontCache.get(fontName).deriveFont(style, size); }

    public static boolean inCache(Object obj) {
        if(obj instanceof BufferedImage) {
            final BufferedImage img = (BufferedImage) obj;
            return m_ImageCache.containsKey(img);
        }else if(obj instanceof Font) {
            final Font fnt = (Font) obj;
            return m_FontCache.containsKey(fnt);
        } else if(obj instanceof Ogg) {
            final Ogg ogg = (Ogg) obj;
            return m_OggCache.containsKey(ogg);
        }

        return false;
    }

    public static Ogg getOgg(String oggName) { return m_OggCache.get(oggName); }

}
