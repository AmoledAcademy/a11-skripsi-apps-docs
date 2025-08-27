using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Scan04 : MonoBehaviour
{
    public void Back()
    {
        SceneHistoryManager.Instance.LoadScene("ScanMenu_1");
    }
}
