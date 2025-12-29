using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Scan08 : MonoBehaviour
{
    public void Back()
    {
        SceneHistoryManager.Instance.LoadScene("ScanMenu_2");
    }
}
