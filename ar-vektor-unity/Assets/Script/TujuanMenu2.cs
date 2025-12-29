using UnityEngine;

public class TujuanMenu2 : MonoBehaviour
{
    public void Back()
    {
        SceneHistoryManager.Instance.LoadScene("TujuanMenu_1");
    }
}
