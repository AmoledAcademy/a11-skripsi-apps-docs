using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Scan06 : MonoBehaviour
{
    public void Back()
    {
        SceneHistoryManager.Instance.LoadScene("ScanMenu_2");
    }
}
