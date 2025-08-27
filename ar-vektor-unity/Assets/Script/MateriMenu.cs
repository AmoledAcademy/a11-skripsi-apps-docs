using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MateriMenu : MonoBehaviour
{

    public void KomponenR2()
    {
        SceneHistoryManager.Instance.LoadScene("VektorR2Materi");
    }

    public void KomponenR3()
    {
        SceneHistoryManager.Instance.LoadScene("VektorR3Materi");
    }

    public void Penjumlahan()
    {
        SceneHistoryManager.Instance.LoadScene("VektorPenjumlahanMateri");
    }

    public void Pengurangan()
    {
        SceneHistoryManager.Instance.LoadScene("VektorPenguranganMateri");
    }

    public void Perkalian()
    {
        SceneHistoryManager.Instance.LoadScene("VektorPerkalianMateri");
    }

    public void Back()
    {
        SceneHistoryManager.Instance.LoadScene("MainMenu");
    }
}
