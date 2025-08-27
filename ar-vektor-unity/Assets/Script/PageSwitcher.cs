using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement; // untuk ganti scene

public class PageSwitcher : MonoBehaviour
{
    [SerializeField] List<GameObject> pages; // drag semua halaman di sini
    [SerializeField] string mainMenuSceneName = "MateriMenu"; // nama scene menu utama

    int index = 0;

    void Start()
    {
        Show(index);
    }

    public void Next()
    {
        if (index < pages.Count - 1)
        {
            index++;
            Show(index);
        }
        else
        {
            // Kalau sudah di halaman terakhir, kembali ke menu utama
            SceneManager.LoadScene(mainMenuSceneName);
        }
    }

    public void Prev()
    {
        if (index > 0)
        {
            index--;
            Show(index);
        }
        else
        {
            // Kalau sudah di halaman pertama, kembali ke menu utama
            SceneManager.LoadScene(mainMenuSceneName);
        }
    }

    void Show(int i)
    {
        for (int p = 0; p < pages.Count; p++)
            pages[p].SetActive(p == i);
    }
}
