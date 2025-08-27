using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ScanSecondMenu : MonoBehaviour
{
    public void Back()
    {
        SceneHistoryManager.Instance.LoadScene("ScanMenu_1");
    }

    public void PenguranganVektorR2()
    {
        SceneHistoryManager.Instance.LoadScene("Scan05");
    }

    public void PenguranganVektorR3()
    {
        SceneHistoryManager.Instance.LoadScene("Scan06");
    }

    public void PerkalianVektorR2()
    {
        SceneHistoryManager.Instance.LoadScene("Scan07");
    }

    public void PerkalianVektorR3()
    {
        SceneHistoryManager.Instance.LoadScene("Scan08");
    }
}