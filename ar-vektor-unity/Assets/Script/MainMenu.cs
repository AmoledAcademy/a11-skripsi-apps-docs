using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour
{
    public void Exit()
    {
        Application.Quit();
        Debug.Log("Game Exited");
    }

    public void ScanMainMenu()
    {
        SceneHistoryManager.Instance.LoadScene("ScanMenu_1");
    }

    public void Materi()
    {
        SceneHistoryManager.Instance.LoadScene("MateriMenu");
    }

    public void Quiz()
    {
        SceneHistoryManager.Instance.LoadScene("QuizMenu");
    }

    public void Tujuan()
    {
        SceneHistoryManager.Instance.LoadScene("TujuanMateri");
    }

    public void About()
    {
        SceneHistoryManager.Instance.LoadScene("About");
    }
}
