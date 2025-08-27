using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SceneHistoryManager : MonoBehaviour
{
    public static SceneHistoryManager Instance;

    private Stack<string> sceneHistory = new Stack<string>();

    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
            DontDestroyOnLoad(gameObject);
            SceneManager.sceneLoaded += OnSceneLoaded;
        }
        else
        {
            Destroy(gameObject);
        }
    }

    private void OnSceneLoaded(Scene scene, LoadSceneMode mode)
    {
        // Hindari menambahkan scene awal dua kali
        if (sceneHistory.Count == 0 || sceneHistory.Peek() != scene.name)
        {
            sceneHistory.Push(scene.name);
        }
    }

    public void LoadScene(string sceneName)
    {
        SceneManager.LoadScene(sceneName);
    }

    public void GoBack()
    {
        if (sceneHistory.Count > 1)
        {
            // Hapus current scene
            sceneHistory.Pop();

            // Load previous scene
            string previousScene = sceneHistory.Peek();
            SceneManager.LoadScene(previousScene);
        }
        else
        {
            Debug.Log("Tidak ada scene sebelumnya!");
        }
    }
}
