using UnityEngine;

public class ScanMainMenu : MonoBehaviour
{
    public void Back()
    {
        SceneHistoryManager.Instance.LoadScene("MainMenu");
    }

    public void Next()
    {
        SceneHistoryManager.Instance.LoadScene("ScanMenu_2");
    }

    public void KomponenVektorR2()
    {
        SceneHistoryManager.Instance.LoadScene("Scan01");
    }

    public void KomponenVektorR3()
    {
        SceneHistoryManager.Instance.LoadScene("Scan02");
    }

    public void PertambahanVektorR2()
    {
        SceneHistoryManager.Instance.LoadScene("Scan03");
    }

    public void PertambahanVektorR3()
    {
        SceneHistoryManager.Instance.LoadScene("Scan04");
    }
}
