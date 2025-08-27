//using UnityEngine;
//using UnityEngine.SceneManagement;
//using UnityEngine.XR.ARFoundation;
//using System.Collections;
//using System.Collections.Generic;

//public class ARSessionManager : MonoBehaviour
//{
//    public static ARSessionManager Instance;

//    private ARSession arSession;

//    // Stack untuk menyimpan history nama scene
//    private Stack<string> sceneHistory = new Stack<string>();

//    // Daftar nama scene AR
//    [SerializeField]
//    private List<string> arScenes = new List<string>()
//    {
//        "Scan01",
//        "Scan02",
//        "Scan03",
//        "Scan04",
//        "Scan05",
//        "Scan06",
//        "Scan07",
//        "Scan08",
//        // Tambah semua scene AR di sini
//    };

//    private void Awake()
//    {
//        if (Instance == null)
//        {
//            Instance = this;
//            DontDestroyOnLoad(gameObject);

//            arSession = FindObjectOfType<ARSession>();
//            if (arSession == null)
//            {
//                Debug.LogError("ARSession component not found in scene!");
//            }
//        }
//        else
//        {
//            Destroy(gameObject);
//            return;
//        }
//    }

//    // Reset AR session
//    public void ResetARSession()
//    {
//        if (arSession != null)
//        {
//            arSession.Reset();
//        }
//        else
//        {
//            Debug.LogWarning("ARSession not found!");
//        }
//    }

//    // Aktifkan AR session
//    public void StartARSession()
//    {
//        if (arSession != null)
//        {
//            arSession.enabled = true;
//        }
//    }

//    // Stop AR session
//    public void StopARSession()
//    {
//        if (arSession != null)
//        {
//            arSession.enabled = false;
//        }
//    }

//    // Fungsi load scene baru (bisa AR atau non-AR)
//    public void LoadScene(string sceneName)
//    {
//        string currentScene = SceneManager.GetActiveScene().name;
//        sceneHistory.Push(currentScene);

//        StartCoroutine(LoadSceneCoroutine(sceneName));
//    }

//    private IEnumerator LoadSceneCoroutine(string sceneName)
//    {
//        // Stop AR session sebelum pindah scene supaya lepaskan resource AR
//        StopARSession();

//        // Unload scene aktif secara async
//        AsyncOperation unloadOp = SceneManager.UnloadSceneAsync(SceneManager.GetActiveScene());
//        while (!unloadOp.isDone)
//            yield return null;

//        // Load scene baru secara async
//        AsyncOperation loadOp = SceneManager.LoadSceneAsync(sceneName);
//        while (!loadOp.isDone)
//            yield return null;

//        // Cari ARSession di scene baru
//        arSession = FindObjectOfType<ARSession>();

//        // Jika scene baru adalah scene AR aktifkan ARSession
//        if (arScenes.Contains(sceneName))
//        {
//            if (arSession != null)
//                arSession.enabled = true;
//            else
//                Debug.LogWarning("ARSession component not found in loaded AR scene: " + sceneName);
//        }
//        else
//        {
//            // Scene non-AR, pastikan AR session mati
//            if (arSession != null)
//                arSession.enabled = false;
//        }
//    }

//    // Fungsi fungsi kembali ke scene sebelumnya dengan manajemen ARSession
//    public void GoBack()
//    {
//        if (sceneHistory.Count > 0)
//        {
//            string previousScene = sceneHistory.Pop();
//            StartCoroutine(LoadSceneCoroutine(previousScene));
//        }
//        else
//        {
//            Debug.Log("No previous scene to go back to.");
//        }
//    }
//}
