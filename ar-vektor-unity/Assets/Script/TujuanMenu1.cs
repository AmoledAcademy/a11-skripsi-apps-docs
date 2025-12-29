using UnityEngine;

public class TujuanMenu1 : MonoBehaviour
{
    public void Back()
    {
        SceneHistoryManager.Instance.LoadScene("MainMenu");
    }

    public void Next()
    {
        SceneHistoryManager.Instance.LoadScene("TujuanMenu_2");
    }
}
