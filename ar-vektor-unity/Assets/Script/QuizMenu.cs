using UnityEngine;
using UnityEngine.SceneManagement;

public class QuizMenu : MonoBehaviour
{
    public void StartMudah()
    {
        SceneManager.LoadScene("QuizMudah");
    }
    public void StartSedang()
    {
        SceneManager.LoadScene("QuizSedang");
    }
    public void StartSulit()
    {
        SceneManager.LoadScene("QuizSulit");
    }
    public void Back()
    {
        SceneManager.LoadScene("MainMenu");
    }
}

