package git.sunku.engine.scenes;

import git.sunku.engine.toolbox.Cache;

public class SceneManager {

    private final Cache<String, Scene> m_Scenes;
    private String m_CurrentScene;

    public SceneManager() {
        m_Scenes = Cache.newInstance(4);
        m_CurrentScene = "";
    }

    public void update(double deltaTime) {
        if(!m_CurrentScene.equals(""))
            m_Scenes.get(m_CurrentScene).update(deltaTime);
    }

    public void render() {
        if(!m_CurrentScene.equals(""))
            m_Scenes.get(m_CurrentScene).render();
    }

    public void addScene(Scene newScene) {
        m_Scenes.putIfAbsent(newScene.getName(), newScene);
    }

    public Scene getScene(String sceneName) {
        return m_Scenes.get(sceneName);
    }

    public void setScene(String sceneName) {
        if(sceneName.equals(m_CurrentScene)) return;

        getScene(sceneName).prepare();
        m_CurrentScene = sceneName;
    }

}
