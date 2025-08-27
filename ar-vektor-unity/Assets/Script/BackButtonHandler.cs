using UnityEngine;

public class BackHandler : MonoBehaviour
{
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneHistoryManager.Instance.GoBack();
        }
    }
}
