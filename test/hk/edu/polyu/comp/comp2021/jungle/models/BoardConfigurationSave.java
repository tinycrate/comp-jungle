package hk.edu.polyu.comp.comp2021.jungle.models;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Let unit test get game save easily
 */
public final class BoardConfigurationSave {

    /**
     * Get a general game save
     * @param loader ClassLoader of the test class
     * @return a board configuration in game progress
     * @throws UnsupportedEncodingException Exception if system do not support UTF-8
     */
    protected static BoardConfiguration getGeneralSave(ClassLoader loader) throws UnsupportedEncodingException {
        String file = loader.getResource("test.save").getFile();
        file = URLDecoder.decode(file,"utf-8");
        BoardConfiguration config = BoardConfiguration.load(file);
        assert config != null;
        return config;
    }


    /**
     * Get a general game save
     * @param loader ClassLoader of the test class
     * @return a board configuration in game progress
     * @throws UnsupportedEncodingException Exception if system do not support UTF-8
     */
    protected static BoardConfiguration getNoMoveSave(ClassLoader loader) throws UnsupportedEncodingException {
        String file = loader.getResource("nomove.save").getFile();
        file = URLDecoder.decode(file,"utf-8");
        BoardConfiguration config = BoardConfiguration.load(file);
        assert config != null;
        return config;
    }
}
